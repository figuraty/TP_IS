import com.dei.isassignment.Car;
import com.dei.isassignment.ConversionServiceGrpc;
import com.dei.isassignment.Request;
import com.dei.isassignment.Response;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.net.URL;

public class ServerService extends ConversionServiceGrpc.ConversionServiceImplBase {

    @Override
    public void listOfCarsPerOwner(Request request, StreamObserver<Response> responseObserver) {
//  populates response object
//        responseObserver.onNext();
//        responseObserver.onCompleted();

//        tenho de retornar
//        Response.newBuilder().addCars(new Car()).build();
    }

    public server(int port, URL featureFile) throws IOException {
        this(ServerBuilder.forPort(port), port, .parseFeatures(featureFile));
    }

}
