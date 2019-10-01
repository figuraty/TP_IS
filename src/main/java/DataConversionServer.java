
import com.dei.isassignment.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
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

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main method.  This comment makes the linter happy.
     */
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
//            List<Owner> owners = request.getOwnersList();
//            List<Car> cars = new ArrayList<>();
//
//            for(Owner owner : owners){
//                cars.addAll(getCarsByOwner(owner.getId()));
//            }
//            return cars;
            return null;
        }
    }
}
