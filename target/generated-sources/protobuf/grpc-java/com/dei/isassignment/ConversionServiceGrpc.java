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
    value = "by gRPC proto compiler (version 1.12.0)",
    comments = "Source: ClientServer.proto")
public final class ConversionServiceGrpc {

  private ConversionServiceGrpc() {}

  public static final String SERVICE_NAME = "com.dei.isassignment.ConversionService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getRequestCarMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.dei.isassignment.CarRequest,
      com.dei.isassignment.CarReply> METHOD_REQUEST_CAR = getRequestCarMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.dei.isassignment.CarRequest,
      com.dei.isassignment.CarReply> getRequestCarMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.dei.isassignment.CarRequest,
      com.dei.isassignment.CarReply> getRequestCarMethod() {
    return getRequestCarMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.dei.isassignment.CarRequest,
      com.dei.isassignment.CarReply> getRequestCarMethodHelper() {
    io.grpc.MethodDescriptor<com.dei.isassignment.CarRequest, com.dei.isassignment.CarReply> getRequestCarMethod;
    if ((getRequestCarMethod = ConversionServiceGrpc.getRequestCarMethod) == null) {
      synchronized (ConversionServiceGrpc.class) {
        if ((getRequestCarMethod = ConversionServiceGrpc.getRequestCarMethod) == null) {
          ConversionServiceGrpc.getRequestCarMethod = getRequestCarMethod = 
              io.grpc.MethodDescriptor.<com.dei.isassignment.CarRequest, com.dei.isassignment.CarReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.dei.isassignment.ConversionService", "requestCar"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dei.isassignment.CarRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dei.isassignment.CarReply.getDefaultInstance()))
                  .setSchemaDescriptor(new ConversionServiceMethodDescriptorSupplier("requestCar"))
                  .build();
          }
        }
     }
     return getRequestCarMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getRequestOwnerMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.dei.isassignment.OwnerRequest,
      com.dei.isassignment.OwnerReply> METHOD_REQUEST_OWNER = getRequestOwnerMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.dei.isassignment.OwnerRequest,
      com.dei.isassignment.OwnerReply> getRequestOwnerMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.dei.isassignment.OwnerRequest,
      com.dei.isassignment.OwnerReply> getRequestOwnerMethod() {
    return getRequestOwnerMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.dei.isassignment.OwnerRequest,
      com.dei.isassignment.OwnerReply> getRequestOwnerMethodHelper() {
    io.grpc.MethodDescriptor<com.dei.isassignment.OwnerRequest, com.dei.isassignment.OwnerReply> getRequestOwnerMethod;
    if ((getRequestOwnerMethod = ConversionServiceGrpc.getRequestOwnerMethod) == null) {
      synchronized (ConversionServiceGrpc.class) {
        if ((getRequestOwnerMethod = ConversionServiceGrpc.getRequestOwnerMethod) == null) {
          ConversionServiceGrpc.getRequestOwnerMethod = getRequestOwnerMethod = 
              io.grpc.MethodDescriptor.<com.dei.isassignment.OwnerRequest, com.dei.isassignment.OwnerReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.dei.isassignment.ConversionService", "requestOwner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dei.isassignment.OwnerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.dei.isassignment.OwnerReply.getDefaultInstance()))
                  .setSchemaDescriptor(new ConversionServiceMethodDescriptorSupplier("requestOwner"))
                  .build();
          }
        }
     }
     return getRequestOwnerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConversionServiceStub newStub(io.grpc.Channel channel) {
    return new ConversionServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConversionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ConversionServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConversionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ConversionServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ConversionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void requestCar(com.dei.isassignment.CarRequest request,
        io.grpc.stub.StreamObserver<com.dei.isassignment.CarReply> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestCarMethodHelper(), responseObserver);
    }

    /**
     */
    public void requestOwner(com.dei.isassignment.OwnerRequest request,
        io.grpc.stub.StreamObserver<com.dei.isassignment.OwnerReply> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestOwnerMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRequestCarMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dei.isassignment.CarRequest,
                com.dei.isassignment.CarReply>(
                  this, METHODID_REQUEST_CAR)))
          .addMethod(
            getRequestOwnerMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.dei.isassignment.OwnerRequest,
                com.dei.isassignment.OwnerReply>(
                  this, METHODID_REQUEST_OWNER)))
          .build();
    }
  }

  /**
   */
  public static final class ConversionServiceStub extends io.grpc.stub.AbstractStub<ConversionServiceStub> {
    private ConversionServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConversionServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConversionServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConversionServiceStub(channel, callOptions);
    }

    /**
     */
    public void requestCar(com.dei.isassignment.CarRequest request,
        io.grpc.stub.StreamObserver<com.dei.isassignment.CarReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestCarMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestOwner(com.dei.isassignment.OwnerRequest request,
        io.grpc.stub.StreamObserver<com.dei.isassignment.OwnerReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestOwnerMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ConversionServiceBlockingStub extends io.grpc.stub.AbstractStub<ConversionServiceBlockingStub> {
    private ConversionServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConversionServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConversionServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConversionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.dei.isassignment.CarReply requestCar(com.dei.isassignment.CarRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestCarMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.dei.isassignment.OwnerReply requestOwner(com.dei.isassignment.OwnerRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestOwnerMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ConversionServiceFutureStub extends io.grpc.stub.AbstractStub<ConversionServiceFutureStub> {
    private ConversionServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ConversionServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConversionServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ConversionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dei.isassignment.CarReply> requestCar(
        com.dei.isassignment.CarRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestCarMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dei.isassignment.OwnerReply> requestOwner(
        com.dei.isassignment.OwnerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestOwnerMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REQUEST_CAR = 0;
  private static final int METHODID_REQUEST_OWNER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ConversionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ConversionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REQUEST_CAR:
          serviceImpl.requestCar((com.dei.isassignment.CarRequest) request,
              (io.grpc.stub.StreamObserver<com.dei.isassignment.CarReply>) responseObserver);
          break;
        case METHODID_REQUEST_OWNER:
          serviceImpl.requestOwner((com.dei.isassignment.OwnerRequest) request,
              (io.grpc.stub.StreamObserver<com.dei.isassignment.OwnerReply>) responseObserver);
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

  private static abstract class ConversionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ConversionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dei.isassignment.ClientServer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ConversionService");
    }
  }

  private static final class ConversionServiceFileDescriptorSupplier
      extends ConversionServiceBaseDescriptorSupplier {
    ConversionServiceFileDescriptorSupplier() {}
  }

  private static final class ConversionServiceMethodDescriptorSupplier
      extends ConversionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ConversionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ConversionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConversionServiceFileDescriptorSupplier())
              .addMethod(getRequestCarMethodHelper())
              .addMethod(getRequestOwnerMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
