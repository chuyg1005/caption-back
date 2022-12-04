package com.example.caption.grpc.caption;

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
    value = "by gRPC proto compiler (version 1.26.0)",
    comments = "Source: caption.proto")
public final class CaptionServiceGrpc {

  private CaptionServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc.CaptionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.caption.grpc.caption.CaptionRequest,
      com.example.caption.grpc.caption.CaptionReply> getGetCaptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getCaption",
      requestType = com.example.caption.grpc.caption.CaptionRequest.class,
      responseType = com.example.caption.grpc.caption.CaptionReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.caption.grpc.caption.CaptionRequest,
      com.example.caption.grpc.caption.CaptionReply> getGetCaptionMethod() {
    io.grpc.MethodDescriptor<com.example.caption.grpc.caption.CaptionRequest, com.example.caption.grpc.caption.CaptionReply> getGetCaptionMethod;
    if ((getGetCaptionMethod = CaptionServiceGrpc.getGetCaptionMethod) == null) {
      synchronized (CaptionServiceGrpc.class) {
        if ((getGetCaptionMethod = CaptionServiceGrpc.getGetCaptionMethod) == null) {
          CaptionServiceGrpc.getGetCaptionMethod = getGetCaptionMethod =
              io.grpc.MethodDescriptor.<com.example.caption.grpc.caption.CaptionRequest, com.example.caption.grpc.caption.CaptionReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getCaption"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.caption.grpc.caption.CaptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.caption.grpc.caption.CaptionReply.getDefaultInstance()))
              .setSchemaDescriptor(new CaptionServiceMethodDescriptorSupplier("getCaption"))
              .build();
        }
      }
    }
    return getGetCaptionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CaptionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CaptionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CaptionServiceStub>() {
        @java.lang.Override
        public CaptionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CaptionServiceStub(channel, callOptions);
        }
      };
    return CaptionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CaptionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CaptionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CaptionServiceBlockingStub>() {
        @java.lang.Override
        public CaptionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CaptionServiceBlockingStub(channel, callOptions);
        }
      };
    return CaptionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CaptionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CaptionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CaptionServiceFutureStub>() {
        @java.lang.Override
        public CaptionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CaptionServiceFutureStub(channel, callOptions);
        }
      };
    return CaptionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CaptionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getCaption(com.example.caption.grpc.caption.CaptionRequest request,
        io.grpc.stub.StreamObserver<com.example.caption.grpc.caption.CaptionReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCaptionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetCaptionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.caption.grpc.caption.CaptionRequest,
                com.example.caption.grpc.caption.CaptionReply>(
                  this, METHODID_GET_CAPTION)))
          .build();
    }
  }

  /**
   */
  public static final class CaptionServiceStub extends io.grpc.stub.AbstractAsyncStub<CaptionServiceStub> {
    private CaptionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CaptionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CaptionServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCaption(com.example.caption.grpc.caption.CaptionRequest request,
        io.grpc.stub.StreamObserver<com.example.caption.grpc.caption.CaptionReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetCaptionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CaptionServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CaptionServiceBlockingStub> {
    private CaptionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CaptionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CaptionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.caption.grpc.caption.CaptionReply getCaption(com.example.caption.grpc.caption.CaptionRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetCaptionMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CaptionServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CaptionServiceFutureStub> {
    private CaptionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CaptionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CaptionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.caption.grpc.caption.CaptionReply> getCaption(
        com.example.caption.grpc.caption.CaptionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetCaptionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CAPTION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CaptionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CaptionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CAPTION:
          serviceImpl.getCaption((com.example.caption.grpc.caption.CaptionRequest) request,
              (io.grpc.stub.StreamObserver<com.example.caption.grpc.caption.CaptionReply>) responseObserver);
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

  private static abstract class CaptionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CaptionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.caption.grpc.caption.CaptionServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CaptionService");
    }
  }

  private static final class CaptionServiceFileDescriptorSupplier
      extends CaptionServiceBaseDescriptorSupplier {
    CaptionServiceFileDescriptorSupplier() {}
  }

  private static final class CaptionServiceMethodDescriptorSupplier
      extends CaptionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CaptionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CaptionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CaptionServiceFileDescriptorSupplier())
              .addMethod(getGetCaptionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
