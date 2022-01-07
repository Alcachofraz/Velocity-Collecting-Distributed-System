package user;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class UserObserver implements StreamObserver<Empty> {
    @Override
    public void onNext(Empty value) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {

    }
}
