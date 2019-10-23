import com.dei.isassignment.*;
import com.dei.isassignment.DataConversionGrpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import xmlClasses.car.CarListXML;
import xmlClasses.car.CarXML;
import xmlClasses.owner.OwnerListXML;
import xmlClasses.owner.OwnerXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Sample client code that makes gRPC calls to the server.
 */
public class DataConversionClient {

    private final ManagedChannel channel;
    private final DataConversionBlockingStub blockingStub;
    private final DataConversionStub asyncStub;

    /** Construct client for accessing DataConversion server at {@code host:port}. */
    public DataConversionClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    /** Construct client for accessing DataConversion server using the existing channel. */
    public DataConversionClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = DataConversionGrpc.newBlockingStub(channel);
        asyncStub = DataConversionGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void listOfCarsPerOwnerProtobuf(List<Owner> owners) {

        Request request = Request.newBuilder().addAllOwners(owners).build();

        Long time1 = System.currentTimeMillis();

        Response response = blockingStub.listOfCarsPerOwnerProtobuf(request);

        Long time2 = System.currentTimeMillis();

        Long diff = time2 - time1;

        System.out.println(diff);
    }

    public void listOfCarsPerOwnerXML(List<Owner> owners) {


        XMLRequest request = XMLRequest.newBuilder().setRequest(serializeXMLRequest(owners)).build();

        Long time1 = System.currentTimeMillis();

        XMLResponse response = blockingStub.listOfCarsPerOwnerXML(request);

        Long time2 = System.currentTimeMillis();

        Long diff = time2 - time1;

        System.out.println("Receive: " + response.getResponse().getBytes().length);

        System.out.println("Time: " + diff);
    }

    private String serializeXMLRequest(List<Owner> owners){
        try {
            StringWriter writer = new StringWriter();

            JAXBContext contextObj = JAXBContext.newInstance(OwnerListXML.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshallerObj.marshal(convertObjectsToXML(owners), writer);

            System.out.println("Send: " + writer.toString().getBytes().length);

            return writer.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private OwnerListXML convertObjectsToXML(List<Owner> owners){
        List<OwnerXML> ownerXMLList = new ArrayList<>();

        for (Owner owner : owners) {
            ownerXMLList.add(
                    new OwnerXML(
                            owner.getId(),
                            owner.getName(),
                            owner.getTelephone(),
                            owner.getAddress()
                    )
            );
        }

        return new OwnerListXML(ownerXMLList);
    }

    private List<Car> deserializeXMLResponse(String xmlResponse){
        try {
            StringReader reader = new StringReader(xmlResponse);

            JAXBContext jaxbContext = JAXBContext.newInstance(CarListXML.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CarListXML carListXML = (CarListXML) jaxbUnmarshaller.unmarshal(reader);

            List<CarXML> cars = carListXML.getCars();
            return convertXMLToObjects(cars);

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Car> convertXMLToObjects(List<CarXML> carXMLList){
        List<Car> cars = new ArrayList<>();
        for (CarXML carXML : carXMLList) {
            cars.add(
                    Car.newBuilder()
                            .setId(carXML.getId())
                            .setBrand(carXML.getBrand())
                            .setModel(carXML.getModel())
                            .setEngine(carXML.getEngine())
                            .setSize(carXML.getSize())
                            .setPower(carXML.getPower())
                            .setConsumption(carXML.getConsumption())
                            .setPlate(carXML.getPlate())
                            .setOwnerId(carXML.getOwnerId())
                            .build());
        }

        return cars;
    }

    /** Issues several different requests and then exits. */
    public static void main(String[] args) throws InterruptedException, IOException {
        List<Owner> owners = DataConversionUtil.parseOwners();

        DataConversionClient client = new DataConversionClient("localhost", 8980);

        for (int i = 0; i < 11; i++) {
            client.listOfCarsPerOwnerXML(owners);
        }
        client.shutdown();
    }
}