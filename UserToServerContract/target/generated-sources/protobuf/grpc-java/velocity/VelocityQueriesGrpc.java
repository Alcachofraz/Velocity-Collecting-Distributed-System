package velocity;

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
    value = "by gRPC proto compiler (version 1.28.1)",
    comments = "Source: chat.proto")
public final class VelocityQueriesGrpc {

  private VelocityQueriesGrpc() {}

  public static final String SERVICE_NAME = "forum.VelocityQueries";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      velocity.Sample> getQueryHighestVelocityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryHighestVelocity",
      requestType = com.google.protobuf.Empty.class,
      responseType = velocity.Sample.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      velocity.Sample> getQueryHighestVelocityMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, velocity.Sample> getQueryHighestVelocityMethod;
    if ((getQueryHighestVelocityMethod = VelocityQueriesGrpc.getQueryHighestVelocityMethod) == null) {
      synchronized (VelocityQueriesGrpc.class) {
        if ((getQueryHighestVelocityMethod = VelocityQueriesGrpc.getQueryHighestVelocityMethod) == null) {
          VelocityQueriesGrpc.getQueryHighestVelocityMethod = getQueryHighestVelocityMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, velocity.Sample>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "queryHighestVelocity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.Sample.getDefaultInstance()))
              .setSchemaDescriptor(new VelocityQueriesMethodDescriptorSupplier("queryHighestVelocity"))
              .build();
        }
      }
    }
    return getQueryHighestVelocityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      velocity.Sample> getQueryLowestVelocityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryLowestVelocity",
      requestType = com.google.protobuf.Empty.class,
      responseType = velocity.Sample.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      velocity.Sample> getQueryLowestVelocityMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, velocity.Sample> getQueryLowestVelocityMethod;
    if ((getQueryLowestVelocityMethod = VelocityQueriesGrpc.getQueryLowestVelocityMethod) == null) {
      synchronized (VelocityQueriesGrpc.class) {
        if ((getQueryLowestVelocityMethod = VelocityQueriesGrpc.getQueryLowestVelocityMethod) == null) {
          VelocityQueriesGrpc.getQueryLowestVelocityMethod = getQueryLowestVelocityMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, velocity.Sample>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "queryLowestVelocity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.Sample.getDefaultInstance()))
              .setSchemaDescriptor(new VelocityQueriesMethodDescriptorSupplier("queryLowestVelocity"))
              .build();
        }
      }
    }
    return getQueryLowestVelocityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<velocity.City,
      velocity.Answer> getQueryVelocitiesInCityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryVelocitiesInCity",
      requestType = velocity.City.class,
      responseType = velocity.Answer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<velocity.City,
      velocity.Answer> getQueryVelocitiesInCityMethod() {
    io.grpc.MethodDescriptor<velocity.City, velocity.Answer> getQueryVelocitiesInCityMethod;
    if ((getQueryVelocitiesInCityMethod = VelocityQueriesGrpc.getQueryVelocitiesInCityMethod) == null) {
      synchronized (VelocityQueriesGrpc.class) {
        if ((getQueryVelocitiesInCityMethod = VelocityQueriesGrpc.getQueryVelocitiesInCityMethod) == null) {
          VelocityQueriesGrpc.getQueryVelocitiesInCityMethod = getQueryVelocitiesInCityMethod =
              io.grpc.MethodDescriptor.<velocity.City, velocity.Answer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "queryVelocitiesInCity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.City.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.Answer.getDefaultInstance()))
              .setSchemaDescriptor(new VelocityQueriesMethodDescriptorSupplier("queryVelocitiesInCity"))
              .build();
        }
      }
    }
    return getQueryVelocitiesInCityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<velocity.Date,
      velocity.Answer> getQueryVelocitiesInDateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryVelocitiesInDate",
      requestType = velocity.Date.class,
      responseType = velocity.Answer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<velocity.Date,
      velocity.Answer> getQueryVelocitiesInDateMethod() {
    io.grpc.MethodDescriptor<velocity.Date, velocity.Answer> getQueryVelocitiesInDateMethod;
    if ((getQueryVelocitiesInDateMethod = VelocityQueriesGrpc.getQueryVelocitiesInDateMethod) == null) {
      synchronized (VelocityQueriesGrpc.class) {
        if ((getQueryVelocitiesInDateMethod = VelocityQueriesGrpc.getQueryVelocitiesInDateMethod) == null) {
          VelocityQueriesGrpc.getQueryVelocitiesInDateMethod = getQueryVelocitiesInDateMethod =
              io.grpc.MethodDescriptor.<velocity.Date, velocity.Answer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "queryVelocitiesInDate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.Date.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.Answer.getDefaultInstance()))
              .setSchemaDescriptor(new VelocityQueriesMethodDescriptorSupplier("queryVelocitiesInDate"))
              .build();
        }
      }
    }
    return getQueryVelocitiesInDateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<velocity.City,
      velocity.Value> getQueryAverageVelocityInCityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryAverageVelocityInCity",
      requestType = velocity.City.class,
      responseType = velocity.Value.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<velocity.City,
      velocity.Value> getQueryAverageVelocityInCityMethod() {
    io.grpc.MethodDescriptor<velocity.City, velocity.Value> getQueryAverageVelocityInCityMethod;
    if ((getQueryAverageVelocityInCityMethod = VelocityQueriesGrpc.getQueryAverageVelocityInCityMethod) == null) {
      synchronized (VelocityQueriesGrpc.class) {
        if ((getQueryAverageVelocityInCityMethod = VelocityQueriesGrpc.getQueryAverageVelocityInCityMethod) == null) {
          VelocityQueriesGrpc.getQueryAverageVelocityInCityMethod = getQueryAverageVelocityInCityMethod =
              io.grpc.MethodDescriptor.<velocity.City, velocity.Value>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "queryAverageVelocityInCity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.City.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.Value.getDefaultInstance()))
              .setSchemaDescriptor(new VelocityQueriesMethodDescriptorSupplier("queryAverageVelocityInCity"))
              .build();
        }
      }
    }
    return getQueryAverageVelocityInCityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<velocity.Date,
      velocity.Value> getQueryAverageVelocityInDateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryAverageVelocityInDate",
      requestType = velocity.Date.class,
      responseType = velocity.Value.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<velocity.Date,
      velocity.Value> getQueryAverageVelocityInDateMethod() {
    io.grpc.MethodDescriptor<velocity.Date, velocity.Value> getQueryAverageVelocityInDateMethod;
    if ((getQueryAverageVelocityInDateMethod = VelocityQueriesGrpc.getQueryAverageVelocityInDateMethod) == null) {
      synchronized (VelocityQueriesGrpc.class) {
        if ((getQueryAverageVelocityInDateMethod = VelocityQueriesGrpc.getQueryAverageVelocityInDateMethod) == null) {
          VelocityQueriesGrpc.getQueryAverageVelocityInDateMethod = getQueryAverageVelocityInDateMethod =
              io.grpc.MethodDescriptor.<velocity.Date, velocity.Value>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "queryAverageVelocityInDate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.Date.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.Value.getDefaultInstance()))
              .setSchemaDescriptor(new VelocityQueriesMethodDescriptorSupplier("queryAverageVelocityInDate"))
              .build();
        }
      }
    }
    return getQueryAverageVelocityInDateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      velocity.Value> getQueryNumberOfConsumersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryNumberOfConsumers",
      requestType = com.google.protobuf.Empty.class,
      responseType = velocity.Value.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      velocity.Value> getQueryNumberOfConsumersMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, velocity.Value> getQueryNumberOfConsumersMethod;
    if ((getQueryNumberOfConsumersMethod = VelocityQueriesGrpc.getQueryNumberOfConsumersMethod) == null) {
      synchronized (VelocityQueriesGrpc.class) {
        if ((getQueryNumberOfConsumersMethod = VelocityQueriesGrpc.getQueryNumberOfConsumersMethod) == null) {
          VelocityQueriesGrpc.getQueryNumberOfConsumersMethod = getQueryNumberOfConsumersMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, velocity.Value>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "queryNumberOfConsumers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  velocity.Value.getDefaultInstance()))
              .setSchemaDescriptor(new VelocityQueriesMethodDescriptorSupplier("queryNumberOfConsumers"))
              .build();
        }
      }
    }
    return getQueryNumberOfConsumersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static VelocityQueriesStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VelocityQueriesStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VelocityQueriesStub>() {
        @java.lang.Override
        public VelocityQueriesStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VelocityQueriesStub(channel, callOptions);
        }
      };
    return VelocityQueriesStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static VelocityQueriesBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VelocityQueriesBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VelocityQueriesBlockingStub>() {
        @java.lang.Override
        public VelocityQueriesBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VelocityQueriesBlockingStub(channel, callOptions);
        }
      };
    return VelocityQueriesBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static VelocityQueriesFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VelocityQueriesFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VelocityQueriesFutureStub>() {
        @java.lang.Override
        public VelocityQueriesFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VelocityQueriesFutureStub(channel, callOptions);
        }
      };
    return VelocityQueriesFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class VelocityQueriesImplBase implements io.grpc.BindableService {

    /**
     */
    public void queryHighestVelocity(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<velocity.Sample> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryHighestVelocityMethod(), responseObserver);
    }

    /**
     */
    public void queryLowestVelocity(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<velocity.Sample> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryLowestVelocityMethod(), responseObserver);
    }

    /**
     */
    public void queryVelocitiesInCity(velocity.City request,
        io.grpc.stub.StreamObserver<velocity.Answer> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryVelocitiesInCityMethod(), responseObserver);
    }

    /**
     */
    public void queryVelocitiesInDate(velocity.Date request,
        io.grpc.stub.StreamObserver<velocity.Answer> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryVelocitiesInDateMethod(), responseObserver);
    }

    /**
     */
    public void queryAverageVelocityInCity(velocity.City request,
        io.grpc.stub.StreamObserver<velocity.Value> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryAverageVelocityInCityMethod(), responseObserver);
    }

    /**
     */
    public void queryAverageVelocityInDate(velocity.Date request,
        io.grpc.stub.StreamObserver<velocity.Value> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryAverageVelocityInDateMethod(), responseObserver);
    }

    /**
     */
    public void queryNumberOfConsumers(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<velocity.Value> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryNumberOfConsumersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryHighestVelocityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                velocity.Sample>(
                  this, METHODID_QUERY_HIGHEST_VELOCITY)))
          .addMethod(
            getQueryLowestVelocityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                velocity.Sample>(
                  this, METHODID_QUERY_LOWEST_VELOCITY)))
          .addMethod(
            getQueryVelocitiesInCityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                velocity.City,
                velocity.Answer>(
                  this, METHODID_QUERY_VELOCITIES_IN_CITY)))
          .addMethod(
            getQueryVelocitiesInDateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                velocity.Date,
                velocity.Answer>(
                  this, METHODID_QUERY_VELOCITIES_IN_DATE)))
          .addMethod(
            getQueryAverageVelocityInCityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                velocity.City,
                velocity.Value>(
                  this, METHODID_QUERY_AVERAGE_VELOCITY_IN_CITY)))
          .addMethod(
            getQueryAverageVelocityInDateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                velocity.Date,
                velocity.Value>(
                  this, METHODID_QUERY_AVERAGE_VELOCITY_IN_DATE)))
          .addMethod(
            getQueryNumberOfConsumersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                velocity.Value>(
                  this, METHODID_QUERY_NUMBER_OF_CONSUMERS)))
          .build();
    }
  }

  /**
   */
  public static final class VelocityQueriesStub extends io.grpc.stub.AbstractAsyncStub<VelocityQueriesStub> {
    private VelocityQueriesStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VelocityQueriesStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VelocityQueriesStub(channel, callOptions);
    }

    /**
     */
    public void queryHighestVelocity(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<velocity.Sample> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryHighestVelocityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryLowestVelocity(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<velocity.Sample> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryLowestVelocityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryVelocitiesInCity(velocity.City request,
        io.grpc.stub.StreamObserver<velocity.Answer> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryVelocitiesInCityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryVelocitiesInDate(velocity.Date request,
        io.grpc.stub.StreamObserver<velocity.Answer> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryVelocitiesInDateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryAverageVelocityInCity(velocity.City request,
        io.grpc.stub.StreamObserver<velocity.Value> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryAverageVelocityInCityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryAverageVelocityInDate(velocity.Date request,
        io.grpc.stub.StreamObserver<velocity.Value> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryAverageVelocityInDateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryNumberOfConsumers(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<velocity.Value> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryNumberOfConsumersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class VelocityQueriesBlockingStub extends io.grpc.stub.AbstractBlockingStub<VelocityQueriesBlockingStub> {
    private VelocityQueriesBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VelocityQueriesBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VelocityQueriesBlockingStub(channel, callOptions);
    }

    /**
     */
    public velocity.Sample queryHighestVelocity(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getQueryHighestVelocityMethod(), getCallOptions(), request);
    }

    /**
     */
    public velocity.Sample queryLowestVelocity(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getQueryLowestVelocityMethod(), getCallOptions(), request);
    }

    /**
     */
    public velocity.Answer queryVelocitiesInCity(velocity.City request) {
      return blockingUnaryCall(
          getChannel(), getQueryVelocitiesInCityMethod(), getCallOptions(), request);
    }

    /**
     */
    public velocity.Answer queryVelocitiesInDate(velocity.Date request) {
      return blockingUnaryCall(
          getChannel(), getQueryVelocitiesInDateMethod(), getCallOptions(), request);
    }

    /**
     */
    public velocity.Value queryAverageVelocityInCity(velocity.City request) {
      return blockingUnaryCall(
          getChannel(), getQueryAverageVelocityInCityMethod(), getCallOptions(), request);
    }

    /**
     */
    public velocity.Value queryAverageVelocityInDate(velocity.Date request) {
      return blockingUnaryCall(
          getChannel(), getQueryAverageVelocityInDateMethod(), getCallOptions(), request);
    }

    /**
     */
    public velocity.Value queryNumberOfConsumers(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getQueryNumberOfConsumersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class VelocityQueriesFutureStub extends io.grpc.stub.AbstractFutureStub<VelocityQueriesFutureStub> {
    private VelocityQueriesFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VelocityQueriesFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VelocityQueriesFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<velocity.Sample> queryHighestVelocity(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryHighestVelocityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<velocity.Sample> queryLowestVelocity(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryLowestVelocityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<velocity.Answer> queryVelocitiesInCity(
        velocity.City request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryVelocitiesInCityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<velocity.Answer> queryVelocitiesInDate(
        velocity.Date request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryVelocitiesInDateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<velocity.Value> queryAverageVelocityInCity(
        velocity.City request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryAverageVelocityInCityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<velocity.Value> queryAverageVelocityInDate(
        velocity.Date request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryAverageVelocityInDateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<velocity.Value> queryNumberOfConsumers(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryNumberOfConsumersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_HIGHEST_VELOCITY = 0;
  private static final int METHODID_QUERY_LOWEST_VELOCITY = 1;
  private static final int METHODID_QUERY_VELOCITIES_IN_CITY = 2;
  private static final int METHODID_QUERY_VELOCITIES_IN_DATE = 3;
  private static final int METHODID_QUERY_AVERAGE_VELOCITY_IN_CITY = 4;
  private static final int METHODID_QUERY_AVERAGE_VELOCITY_IN_DATE = 5;
  private static final int METHODID_QUERY_NUMBER_OF_CONSUMERS = 6;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final VelocityQueriesImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(VelocityQueriesImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_HIGHEST_VELOCITY:
          serviceImpl.queryHighestVelocity((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<velocity.Sample>) responseObserver);
          break;
        case METHODID_QUERY_LOWEST_VELOCITY:
          serviceImpl.queryLowestVelocity((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<velocity.Sample>) responseObserver);
          break;
        case METHODID_QUERY_VELOCITIES_IN_CITY:
          serviceImpl.queryVelocitiesInCity((velocity.City) request,
              (io.grpc.stub.StreamObserver<velocity.Answer>) responseObserver);
          break;
        case METHODID_QUERY_VELOCITIES_IN_DATE:
          serviceImpl.queryVelocitiesInDate((velocity.Date) request,
              (io.grpc.stub.StreamObserver<velocity.Answer>) responseObserver);
          break;
        case METHODID_QUERY_AVERAGE_VELOCITY_IN_CITY:
          serviceImpl.queryAverageVelocityInCity((velocity.City) request,
              (io.grpc.stub.StreamObserver<velocity.Value>) responseObserver);
          break;
        case METHODID_QUERY_AVERAGE_VELOCITY_IN_DATE:
          serviceImpl.queryAverageVelocityInDate((velocity.Date) request,
              (io.grpc.stub.StreamObserver<velocity.Value>) responseObserver);
          break;
        case METHODID_QUERY_NUMBER_OF_CONSUMERS:
          serviceImpl.queryNumberOfConsumers((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<velocity.Value>) responseObserver);
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

  private static abstract class VelocityQueriesBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    VelocityQueriesBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return velocity.Chat.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("VelocityQueries");
    }
  }

  private static final class VelocityQueriesFileDescriptorSupplier
      extends VelocityQueriesBaseDescriptorSupplier {
    VelocityQueriesFileDescriptorSupplier() {}
  }

  private static final class VelocityQueriesMethodDescriptorSupplier
      extends VelocityQueriesBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    VelocityQueriesMethodDescriptorSupplier(String methodName) {
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
      synchronized (VelocityQueriesGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new VelocityQueriesFileDescriptorSupplier())
              .addMethod(getQueryHighestVelocityMethod())
              .addMethod(getQueryLowestVelocityMethod())
              .addMethod(getQueryVelocitiesInCityMethod())
              .addMethod(getQueryVelocitiesInDateMethod())
              .addMethod(getQueryAverageVelocityInCityMethod())
              .addMethod(getQueryAverageVelocityInDateMethod())
              .addMethod(getQueryNumberOfConsumersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
