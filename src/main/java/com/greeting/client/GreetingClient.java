package com.greeting.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
  public static void main(String[] args) {
    System.out.println("I'm a gRPC client");

    ManagedChannel channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build();

    System.out.println("Creating stub");

    GreetServiceGrpc.GreetServiceBlockingStub greetClient =
        GreetServiceGrpc.newBlockingStub(channel);

    // create a protocol buffer greeting message
    Greeting greeting = Greeting.newBuilder()
        .setFirstName("Paquito")
        .setLastName("El Chocolatero")
        .build();
    // create a GreetRequest
    GreetRequest request = GreetRequest.newBuilder()
        .setGreeting(greeting)
        .build();

    // call de RPC and get back a response
    GreetResponse response = greetClient.greet(request);
    System.out.println(response.getResult());

    System.out.println("Shutting down channel");
    channel.shutdown();
  }
}
