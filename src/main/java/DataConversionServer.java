
import com.dei.isassignment.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DataConversionServer {

    private static final Logger logger = Logger.getLogger(DataConversionServer.class.getName());

    private final int port;
    private final Server server;

    public DataConversionServer(int port) throws IOException {
        this(port, DataConversionUtil.getCarsDBFile(), DataConversionUtil.getOwnersDBFile());
    }

    /** Create a RouteGuide server listening on {@code port} using {@code featureFile} database. */
    public DataConversionServer(int port, URL carsDatabaseFile, URL ownersDatabaseFile) throws IOException {
        this(ServerBuilder.forPort(port), port, DataConversionUtil.parseCars(carsDatabaseFile), DataConversionUtil.parseOwners(ownersDatabaseFile));
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

    /**
     * Our implementation of DataConversion service.
     *
     * <p>See route_guide.proto for details of the methods.
     */
    private static class ConversionService extends DataConversionGrpc.DataConversionImplBase {

        private final List<Car> cars;
        private final List<Owner> owners;

        ConversionService(List<Car> cars, List<Owner> owners) {
            this.cars = cars;
            this.owners = owners;
        }

        @Override
        public void listOfCarsPerOwner(Request request, StreamObserver<Response> responseObserver) {
//          falta fazer uma funcao para validar os owners que vêm no request
            responseObserver.onNext(Response.newBuilder().addAllCars(cars).build());
            responseObserver.onCompleted();
        }




    }
}
