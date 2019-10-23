
import com.dei.isassignment.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import xmlClasses.car.CarListXML;
import xmlClasses.car.CarXML;
import xmlClasses.owner.OwnerListXML;
import xmlClasses.owner.OwnerXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DataConversionServer {

    private static final Logger logger = Logger.getLogger(DataConversionServer.class.getName());

    private final int port;
    private final Server server;

    /** Create a RouteGuide server listening on {@code port} using {@code featureFile} database. */
    public DataConversionServer(int port) throws IOException {
        this(ServerBuilder.forPort(port), port, DataConversionUtil.parseCars(), DataConversionUtil.parseOwners());
    }

    /** Create a RouteGuide server using serverBuilder as a base and features as data. */
    public DataConversionServer(ServerBuilder<?> serverBuilder, int port, List<Car> cars, List<Owner> owners) {
        this.port = port;
        server = serverBuilder.addService(new ConversionService(cars, owners))
                .build();
    }

    /** Start serving requests. */
    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                DataConversionServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    /** Stop serving requests and shutdown resources. */
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /** Await termination on the main thread since the grpc library uses daemon threads. */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /** Main method.  This comment makes the linter happy.  */
    public static void main(String[] args) throws Exception {
        DataConversionServer server = new DataConversionServer(8980);
        server.start();
        server.blockUntilShutdown();
    }

    private static class ConversionService extends DataConversionGrpc.DataConversionImplBase {

        private final List<Car> cars;
        private final List<Owner> owners;

        ConversionService(List<Car> cars, List<Owner> owners) {
            this.cars = cars;
            this.owners = owners;
        }

        @Override
        public void listOfCarsPerOwnerProtobuf(Request request, StreamObserver<Response> responseObserver) {
            responseObserver.onNext(Response.newBuilder().addAllCars(processProtobufRequest(request)).build());
            responseObserver.onCompleted();
        }

        @Override
        public void listOfCarsPerOwnerXML(XMLRequest request, StreamObserver<XMLResponse> responseObserver) {
            responseObserver.onNext(XMLResponse.newBuilder().setResponse(processXMLRequest(request)).build());
            responseObserver.onCompleted();
        }

        private List<Car> processProtobufRequest(Request request){
            List<Owner> owners = request.getOwnersList();
            List<Car> cars = new ArrayList<>();

            for(Owner owner : owners){
                cars.addAll(getCarsByOwner(owner.getId()));
            }
            return cars;
        }

        private List<Car> getCarsByOwner(int owner_id){
            List<Car> cars = new ArrayList<>();

            for(Car car : this.cars){
                if(car.getOwnerId() == owner_id){
                    cars.add(car);
                }
            }
            return cars;
        }

        private String processXMLRequest(XMLRequest request){
            List<Owner> owners = deserializeXMLRequest(request.getRequest());
            List<Car> cars = new ArrayList<>();

            for(Owner owner : owners){
                cars.addAll(getCarsByOwner(owner.getId()));
            }

            return serializeXMLResponse(cars);
        }

        private List<Owner> deserializeXMLRequest(String xmlRequest){
            try {
                StringReader reader = new StringReader(xmlRequest);

                JAXBContext jaxbContext = JAXBContext.newInstance(OwnerListXML.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                OwnerListXML ownerListXML = (OwnerListXML) jaxbUnmarshaller.unmarshal(reader);

                List<OwnerXML> owners = ownerListXML.getOwners();
                return convertXMLToObjects(owners);

            } catch (JAXBException e) {
                e.printStackTrace();
                return null;
            }
        }

        private List<Owner> convertXMLToObjects(List<OwnerXML> ownerXMLList){
            List<Owner> owners = new ArrayList<>();
            for (OwnerXML ownerXML : ownerXMLList) {
                owners.add(
                        Owner.newBuilder()
                        .setId(ownerXML.getId())
                        .setName(ownerXML.getName())
                        .setTelephone(ownerXML.getTelephone())
                        .setAddress(ownerXML.getAddress())
                        .build());
            }
            return owners;
        }

        private String serializeXMLResponse(List<Car> cars){
            try {
                StringWriter writer = new StringWriter();

                JAXBContext contextObj = JAXBContext.newInstance(CarListXML.class);
                Marshaller marshallerObj = contextObj.createMarshaller();
                marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                marshallerObj.marshal(convertObjectsToXML(cars), writer);

                return writer.toString();

            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return null;
        }

        private CarListXML convertObjectsToXML(List<Car> cars){
            List<CarXML> carXMLList = new ArrayList<>();

            for (Car car : cars) {
                carXMLList.add(
                        new CarXML(
                                car.getId(),
                                car.getBrand(),
                                car.getModel(),
                                car.getEngine(),
                                car.getSize(),
                                car.getPower(),
                                car.getConsumption(),
                                car.getPlate(),
                                car.getOwnerId()
                        )
                );
            }

            return new CarListXML(carXMLList);
        }
    }
}
