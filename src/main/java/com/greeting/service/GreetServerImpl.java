package com.greeting.service;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetServerImpl extends GreetServiceGrpc.GreetServiceImplBase {

  @Override
  public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
    // extract the fields we need
    Greeting greeting = request.getGreeting();

    // create the response
    String result = "Hello " + greeting.getFirstName() + " " + greeting.getLastName();
    GreetResponse response = GreetResponse.newBuilder()
        .setResult(result)
        .build();

    // send the response
    responseObserver.onNext(response);

    // complete the RPC call
    responseObserver.onCompleted();
  }
}
