package server;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import spread.SpreadException;
import velocity.*;

import java.util.logging.Level;

class ServerUser extends VelocityGrpc.VelocityImplBase {
    @Override
    public void queryHighestVelocity(Empty request, StreamObserver<Sample> responseObserver) {
        Server.logger.log(Level.INFO, "QUERY: Highest Velocity");
        VelocitySample highest = new VelocitySample("NONE", "NONE", "NONE", Integer.MIN_VALUE);
        for (VelocitySample sample : Server.history) {
            if (sample.getVelocity() > highest.getVelocity()) {
                highest = sample;
            }
        }
        responseObserver.onNext(getSample(highest));
        responseObserver.onCompleted();
    }

    @Override
    public void queryLowestVelocity(Empty request, StreamObserver<Sample> responseObserver) {
        Server.logger.log(Level.INFO, "QUERY: Lowest Velocity");
        VelocitySample lowest = new VelocitySample("NONE", "NONE", "NONE", Integer.MAX_VALUE);
        for (VelocitySample sample : Server.history) {
            if (sample.getVelocity() < lowest.getVelocity()) {
                lowest = sample;
            }
        }
        responseObserver.onNext(getSample(lowest));
        responseObserver.onCompleted();
    }

    @Override
    public void queryVelocitiesInCity(City request, StreamObserver<Answer> responseObserver) {
        Server.logger.log(Level.INFO, "QUERY: Velocities In City");
        Answer.Builder answer = Answer.newBuilder();
        for (VelocitySample sample : Server.history) {
            if (sample.getCity().equals(request.getCity())) answer.addSamples(getSample(sample));
        }
        responseObserver.onNext(answer.build());
        responseObserver.onCompleted();
    }

    @Override
    public void queryVelocitiesInDate(Date request, StreamObserver<Answer> responseObserver) {
        Server.logger.log(Level.INFO, "QUERY: Velocities In Date");
        Answer.Builder answer = Answer.newBuilder();
        for (VelocitySample sample : Server.history) {
            if (sample.getDate().equals(request.getDate())) answer.addSamples(getSample(sample));
        }
        responseObserver.onNext(answer.build());
        responseObserver.onCompleted();
    }

    @Override
    public void queryAverageVelocityInCity(City request, StreamObserver<Value> responseObserver) {
        Server.logger.log(Level.INFO, "QUERY: Average Velocity In City");
        int sum = 0;
        int ct = 0;
        for (VelocitySample sample : Server.history) {
            if (sample.getCity().equals(request.getCity())) {
                sum += sample.getVelocity();
                ct++;
            }
        }
        responseObserver.onNext(Value.newBuilder().setValue(ct == 0 ? -1 : sum/ct).build());
        responseObserver.onCompleted();
    }

    @Override
    public void queryAverageVelocityInDate(Date request, StreamObserver<Value> responseObserver) {
        Server.logger.log(Level.INFO, "QUERY: Average Velocity In Date");
        int sum = 0;
        int ct = 0;
        for (VelocitySample sample : Server.history) {
            if (sample.getDate().equals(request.getDate())) {
                sum += sample.getVelocity();
                ct++;
            }
        }
        responseObserver.onNext(Value.newBuilder().setValue(ct == 0 ? -1 : sum/ct).build());
        responseObserver.onCompleted();
    }

    @Override
    public void queryNumberOfConsumers(Empty request, StreamObserver<Value> responseObserver) {
        Server.logger.log(Level.INFO, "QUERY: Number Of Consumers");
        responseObserver.onNext(Value.newBuilder().setValue(Server.consumers).build());
        responseObserver.onCompleted();
    }

    @Override
    public void requestNewConsumer(Empty request, StreamObserver<Empty> responseObserver) {
        Server.logger.log(Level.INFO, "REQUEST: New Consumer");
        try {
            Server.member.sendMessage(Server.EVENT_PROCESSING_GROUP_NAME, "NEW_CONSUMER");
        } catch (SpreadException e) {
            e.printStackTrace();
        }
    }

    Sample getSample(VelocitySample sample) {
        return Sample.newBuilder()
                .setSid(sample.getSid())
                .setCity(sample.getCity())
                .setDate(sample.getDate())
                .setVelocity(sample.getVelocity())
                .build();
    }
}
