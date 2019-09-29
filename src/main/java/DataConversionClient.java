import com.dei.isassignment.DataConversionGrpc;
import com.dei.isassignment.DataConversionGrpc.*;
import com.dei.isassignment.Owner;
import com.dei.isassignment.Request;
import com.dei.isassignment.Response;
import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sample client code that makes gRPC calls to the server.
 */
public class DataConversionClient {
    private static final Logger logger = Logger.getLogger(DataConversionClient.class.getName());

    private final ManagedChannel channel;
    private final DataConversionBlockingStub blockingStub;
    private final DataConversionStub asyncStub;

    private Random random = new Random();
    private TestHelper testHelper;

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

    /**
     * Blocking unary call example.  Calls getFeature and prints the response.
     */
    public void listOfCarsPerOwner(List<Owner> owners) {


        Request request = Request.newBuilder().addAllOwners(owners).build();

        Response response;

        try {
            response = blockingStub.listOfCarsPerOwner(request);
            if (testHelper != null) {
                testHelper.onMessage(response);
            }
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
            return;
        }

        System.out.println(response.getCarsList());
        /*if (DataConversionUtil.exists(feature)) {
            info("Found feature called \"{0}\" at {1}, {2}",
                    feature.getName(),
                    DataConversionUtil.getLatitude(feature.getLocation()),
                    DataConversionUtil.getLongitude(feature.getLocation()));
        } else {
            info("Found no feature at {0}, {1}",
                    DataConversionUtil.getLatitude(feature.getLocation()),
                    DataConversionUtil.getLongitude(feature.getLocation()));
        }*/
    }

    /**
     * Blocking server-streaming example. Calls listFeatures with a rectangle of interest. Prints each
     * response feature as it arrives.
     */

    /**
     * Async client-streaming example. Sends {@code numPoints} randomly chosen points from {@code
     * features} with a variable delay in between. Prints the statistics when they are sent from the
     * server.
     */

    /**
     * Bi-directional example, which can only be asynchronous. Send some chat messages, and print any
     * chat messages that are sent from the server.
     */


    /** Issues several different requests and then exits. */
    public static void main(String[] args) throws InterruptedException {
        List<Owner> owners = new ArrayList<>();
        owners.add(Owner.newBuilder().setId(69).build());
        /*try {
            features = DataConversionUtil.parseFeatures(DataConversionUtil.getDefaultFeaturesFile());
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }*/

        DataConversionClient client = new DataConversionClient("localhost", 8980);

        client.listOfCarsPerOwner(owners);
        client.shutdown();
    }

    private void warning(String msg, Object... params) {
        logger.log(Level.WARNING, msg, params);
    }

    @VisibleForTesting
    interface TestHelper {
        /**
         * Used for verify/inspect message received from server.
         */
        void onMessage(Message message);

        /**
         * Used for verify/inspect error received from server.
         */
        void onRpcError(Throwable exception);
    }
}