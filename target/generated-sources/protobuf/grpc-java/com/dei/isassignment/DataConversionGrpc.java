package com.dei.isassignment;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.23.0)",
    comments = "Source: DataConversion.proto")
public final class DataConversionGrpc {

  private DataConversionGrpc() {}

  public static final String SERVICE_NAME = "com.dei.isassignment.DataConversion";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.dei.isassignment.Request,
      com.dei.isassignment.Response> getListOfCarsPerOwnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listOfCarsPerOwner",
      requestType = com.dei.isassignment.Request.class,
      responseType = com.dei.isassignment.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.dei.isassignment.Request,
      com.dei.isassignment.Response> getListOfCarsPerOwnerMethod() {
    io.grpc.MethodDescriptor<com.dei.isassignment.Request, com.dei.isassignment.Response> getListOfCarsPerOwnerMethod;
    if ((getListOfCarsPerOwnerMethod = DataConversionGrpc.getListOfCarsPerOwnerMethod) == null) {
      synchronized (DataConversionGrpc.class) {
        if ((getListOfCarsPerOwnerMethod = DataConversionGrpc.getListOfCarsPerOwnerMethod) == null) {
          DataConversionGrpc.getListOfCarsPerOwnerMethod = getListOfCarsPerOwnerMethod =
              io.grpc.MethodDescriptor.<com.dei.isassignment.Request, com.dei.isassignment.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listOfCarsPerOwner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dei.isassignment.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dei.isassignment.Response.getDefaultInstance()))
              .setSchemaDescriptor(new DataConversionMethodDescriptorSupplier("listOfCarsPerOwner"))
              .build();
        }
      }
    }
    return getListOfCarsPerOwnerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DataConversionStub newStub(io.grpc.Channel channel) {
    return new DataConversionStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DataConversionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DataConversionBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DataConversionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DataConversionFutureStub(channel);
  }

  /**
   */
  public static abstract class DataConversionImplBase implements io.grpc.BindableService {

    /**
     */
    public void listOfCarsPerOwner(com.dei.isassignment.Request request,
        io.grpc.stub.StreamObserver<com.dei.isassignment.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getListOfCarsPerOwnerMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getListOfCarsPerOwnerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dei.isassignment.Request,
                com.dei.isassignment.Response>(
                  this, METHODID_LIST_OF_CARS_PER_OWNER)))
          .build();
    }
  }

  /**
   */
  public static final class DataConversionStub extends io.grpc.stub.AbstractStub<DataConversionStub> {
    private DataConversionStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataConversionStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataConversionStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataConversionStub(channel, callOptions);
    }

    /**
     */
    public void listOfCarsPerOwner(com.dei.isassignment.Request request,
        io.grpc.stub.StreamObserver<com.dei.isassignment.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListOfCarsPerOwnerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DataConversionBlockingStub extends io.grpc.stub.AbstractStub<DataConversionBlockingStub> {
    private DataConversionBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataConversionBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataConversionBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataConversionBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.dei.isassignment.Response listOfCarsPerOwner(com.dei.isassignment.Request request) {
      return blockingUnaryCall(
          getChannel(), getListOfCarsPerOwnerMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DataConversionFutureStub extends io.grpc.stub.AbstractStub<DataConversionFutureStub> {
    private DataConversionFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DataConversionFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataConversionFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DataConversionFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dei.isassignment.Response> listOfCarsPerOwner(
        com.dei.isassignment.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getListOfCarsPerOwnerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_OF_CARS_PER_OWNER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DataConversionImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DataConversionImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST_OF_CARS_PER_OWNER:
          serviceImpl.listOfCarsPerOwner((com.dei.isassignment.Request) request,
              (io.grpc.stub.StreamObserver<com.dei.isassignment.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DataConversionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DataConversionBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dei.isassignment.DataConversionOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DataConversion");
    }
  }

  private static final class DataConversionFileDescriptorSupplier
      extends DataConversionBaseDescriptorSupplier {
    DataConversionFileDescriptorSupplier() {}
  }

  private static final class DataConversionMethodDescriptorSupplier
      extends DataConversionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DataConversionMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DataConversionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DataConversionFileDescriptorSupplier())
              .addMethod(getListOfCarsPerOwnerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
