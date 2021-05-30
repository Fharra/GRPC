package com.greeting.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class GreetingServer {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("GRPC server");

    Server server = ServerBuilder.forPort(50051)
        .addService(new GreetServerImpl())
        .build();
    server.start();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("Shutdown Request received");
      server.shutdown();
      System.out.println("Successfully stopped the server");
    }));

    server.awaitTermination();
  }
}
