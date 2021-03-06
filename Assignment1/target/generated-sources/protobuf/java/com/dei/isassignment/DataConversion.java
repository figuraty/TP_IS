// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DataConversion.proto

package com.dei.isassignment;

/**
 * Protobuf service {@code com.dei.isassignment.DataConversion}
 */
public  abstract class DataConversion
    implements com.google.protobuf.Service {
  protected DataConversion() {}

  public interface Interface {
    /**
     * <code>rpc listOfCarsPerOwnerProtobuf(.com.dei.isassignment.Request) returns (.com.dei.isassignment.Response);</code>
     */
    public abstract void listOfCarsPerOwnerProtobuf(
        com.google.protobuf.RpcController controller,
        com.dei.isassignment.Request request,
        com.google.protobuf.RpcCallback<com.dei.isassignment.Response> done);

    /**
     * <code>rpc listOfCarsPerOwnerXML(.com.dei.isassignment.XMLRequest) returns (.com.dei.isassignment.XMLResponse);</code>
     */
    public abstract void listOfCarsPerOwnerXML(
        com.google.protobuf.RpcController controller,
        com.dei.isassignment.XMLRequest request,
        com.google.protobuf.RpcCallback<com.dei.isassignment.XMLResponse> done);

  }

  public static com.google.protobuf.Service newReflectiveService(
      final Interface impl) {
    return new DataConversion() {
      @java.lang.Override
      public  void listOfCarsPerOwnerProtobuf(
          com.google.protobuf.RpcController controller,
          com.dei.isassignment.Request request,
          com.google.protobuf.RpcCallback<com.dei.isassignment.Response> done) {
        impl.listOfCarsPerOwnerProtobuf(controller, request, done);
      }

      @java.lang.Override
      public  void listOfCarsPerOwnerXML(
          com.google.protobuf.RpcController controller,
          com.dei.isassignment.XMLRequest request,
          com.google.protobuf.RpcCallback<com.dei.isassignment.XMLResponse> done) {
        impl.listOfCarsPerOwnerXML(controller, request, done);
      }

    };
  }

  public static com.google.protobuf.BlockingService
      newReflectiveBlockingService(final BlockingInterface impl) {
    return new com.google.protobuf.BlockingService() {
      public final com.google.protobuf.Descriptors.ServiceDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }

      public final com.google.protobuf.Message callBlockingMethod(
          com.google.protobuf.Descriptors.MethodDescriptor method,
          com.google.protobuf.RpcController controller,
          com.google.protobuf.Message request)
          throws com.google.protobuf.ServiceException {
        if (method.getService() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "Service.callBlockingMethod() given method descriptor for " +
            "wrong service type.");
        }
        switch(method.getIndex()) {
          case 0:
            return impl.listOfCarsPerOwnerProtobuf(controller, (com.dei.isassignment.Request)request);
          case 1:
            return impl.listOfCarsPerOwnerXML(controller, (com.dei.isassignment.XMLRequest)request);
          default:
            throw new java.lang.AssertionError("Can't get here.");
        }
      }

      public final com.google.protobuf.Message
          getRequestPrototype(
          com.google.protobuf.Descriptors.MethodDescriptor method) {
        if (method.getService() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "Service.getRequestPrototype() given method " +
            "descriptor for wrong service type.");
        }
        switch(method.getIndex()) {
          case 0:
            return com.dei.isassignment.Request.getDefaultInstance();
          case 1:
            return com.dei.isassignment.XMLRequest.getDefaultInstance();
          default:
            throw new java.lang.AssertionError("Can't get here.");
        }
      }

      public final com.google.protobuf.Message
          getResponsePrototype(
          com.google.protobuf.Descriptors.MethodDescriptor method) {
        if (method.getService() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "Service.getResponsePrototype() given method " +
            "descriptor for wrong service type.");
        }
        switch(method.getIndex()) {
          case 0:
            return com.dei.isassignment.Response.getDefaultInstance();
          case 1:
            return com.dei.isassignment.XMLResponse.getDefaultInstance();
          default:
            throw new java.lang.AssertionError("Can't get here.");
        }
      }

    };
  }

  /**
   * <code>rpc listOfCarsPerOwnerProtobuf(.com.dei.isassignment.Request) returns (.com.dei.isassignment.Response);</code>
   */
  public abstract void listOfCarsPerOwnerProtobuf(
      com.google.protobuf.RpcController controller,
      com.dei.isassignment.Request request,
      com.google.protobuf.RpcCallback<com.dei.isassignment.Response> done);

  /**
   * <code>rpc listOfCarsPerOwnerXML(.com.dei.isassignment.XMLRequest) returns (.com.dei.isassignment.XMLResponse);</code>
   */
  public abstract void listOfCarsPerOwnerXML(
      com.google.protobuf.RpcController controller,
      com.dei.isassignment.XMLRequest request,
      com.google.protobuf.RpcCallback<com.dei.isassignment.XMLResponse> done);

  public static final
      com.google.protobuf.Descriptors.ServiceDescriptor
      getDescriptor() {
    return com.dei.isassignment.DataConversionOuterClass.getDescriptor().getServices().get(0);
  }
  public final com.google.protobuf.Descriptors.ServiceDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }

  public final void callMethod(
      com.google.protobuf.Descriptors.MethodDescriptor method,
      com.google.protobuf.RpcController controller,
      com.google.protobuf.Message request,
      com.google.protobuf.RpcCallback<
        com.google.protobuf.Message> done) {
    if (method.getService() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "Service.callMethod() given method descriptor for wrong " +
        "service type.");
    }
    switch(method.getIndex()) {
      case 0:
        this.listOfCarsPerOwnerProtobuf(controller, (com.dei.isassignment.Request)request,
          com.google.protobuf.RpcUtil.<com.dei.isassignment.Response>specializeCallback(
            done));
        return;
      case 1:
        this.listOfCarsPerOwnerXML(controller, (com.dei.isassignment.XMLRequest)request,
          com.google.protobuf.RpcUtil.<com.dei.isassignment.XMLResponse>specializeCallback(
            done));
        return;
      default:
        throw new java.lang.AssertionError("Can't get here.");
    }
  }

  public final com.google.protobuf.Message
      getRequestPrototype(
      com.google.protobuf.Descriptors.MethodDescriptor method) {
    if (method.getService() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "Service.getRequestPrototype() given method " +
        "descriptor for wrong service type.");
    }
    switch(method.getIndex()) {
      case 0:
        return com.dei.isassignment.Request.getDefaultInstance();
      case 1:
        return com.dei.isassignment.XMLRequest.getDefaultInstance();
      default:
        throw new java.lang.AssertionError("Can't get here.");
    }
  }

  public final com.google.protobuf.Message
      getResponsePrototype(
      com.google.protobuf.Descriptors.MethodDescriptor method) {
    if (method.getService() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "Service.getResponsePrototype() given method " +
        "descriptor for wrong service type.");
    }
    switch(method.getIndex()) {
      case 0:
        return com.dei.isassignment.Response.getDefaultInstance();
      case 1:
        return com.dei.isassignment.XMLResponse.getDefaultInstance();
      default:
        throw new java.lang.AssertionError("Can't get here.");
    }
  }

  public static Stub newStub(
      com.google.protobuf.RpcChannel channel) {
    return new Stub(channel);
  }

  public static final class Stub extends com.dei.isassignment.DataConversion implements Interface {
    private Stub(com.google.protobuf.RpcChannel channel) {
      this.channel = channel;
    }

    private final com.google.protobuf.RpcChannel channel;

    public com.google.protobuf.RpcChannel getChannel() {
      return channel;
    }

    public  void listOfCarsPerOwnerProtobuf(
        com.google.protobuf.RpcController controller,
        com.dei.isassignment.Request request,
        com.google.protobuf.RpcCallback<com.dei.isassignment.Response> done) {
      channel.callMethod(
        getDescriptor().getMethods().get(0),
        controller,
        request,
        com.dei.isassignment.Response.getDefaultInstance(),
        com.google.protobuf.RpcUtil.generalizeCallback(
          done,
          com.dei.isassignment.Response.class,
          com.dei.isassignment.Response.getDefaultInstance()));
    }

    public  void listOfCarsPerOwnerXML(
        com.google.protobuf.RpcController controller,
        com.dei.isassignment.XMLRequest request,
        com.google.protobuf.RpcCallback<com.dei.isassignment.XMLResponse> done) {
      channel.callMethod(
        getDescriptor().getMethods().get(1),
        controller,
        request,
        com.dei.isassignment.XMLResponse.getDefaultInstance(),
        com.google.protobuf.RpcUtil.generalizeCallback(
          done,
          com.dei.isassignment.XMLResponse.class,
          com.dei.isassignment.XMLResponse.getDefaultInstance()));
    }
  }

  public static BlockingInterface newBlockingStub(
      com.google.protobuf.BlockingRpcChannel channel) {
    return new BlockingStub(channel);
  }

  public interface BlockingInterface {
    public com.dei.isassignment.Response listOfCarsPerOwnerProtobuf(
        com.google.protobuf.RpcController controller,
        com.dei.isassignment.Request request)
        throws com.google.protobuf.ServiceException;

    public com.dei.isassignment.XMLResponse listOfCarsPerOwnerXML(
        com.google.protobuf.RpcController controller,
        com.dei.isassignment.XMLRequest request)
        throws com.google.protobuf.ServiceException;
  }

  private static final class BlockingStub implements BlockingInterface {
    private BlockingStub(com.google.protobuf.BlockingRpcChannel channel) {
      this.channel = channel;
    }

    private final com.google.protobuf.BlockingRpcChannel channel;

    public com.dei.isassignment.Response listOfCarsPerOwnerProtobuf(
        com.google.protobuf.RpcController controller,
        com.dei.isassignment.Request request)
        throws com.google.protobuf.ServiceException {
      return (com.dei.isassignment.Response) channel.callBlockingMethod(
        getDescriptor().getMethods().get(0),
        controller,
        request,
        com.dei.isassignment.Response.getDefaultInstance());
    }


    public com.dei.isassignment.XMLResponse listOfCarsPerOwnerXML(
        com.google.protobuf.RpcController controller,
        com.dei.isassignment.XMLRequest request)
        throws com.google.protobuf.ServiceException {
      return (com.dei.isassignment.XMLResponse) channel.callBlockingMethod(
        getDescriptor().getMethods().get(1),
        controller,
        request,
        com.dei.isassignment.XMLResponse.getDefaultInstance());
    }

  }

  // @@protoc_insertion_point(class_scope:com.dei.isassignment.DataConversion)
}

