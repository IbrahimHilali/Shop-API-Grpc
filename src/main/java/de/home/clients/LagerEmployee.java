package de.home.clients;

import com.google.protobuf.Empty;
import de.home.grpc.Order;
import de.home.grpc.OrderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LagerEmployee {
    private ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceStub = OrderServiceGrpc.newBlockingStub(channel);

    public static void main(String[] args) {
        System.out.println("Running gRPC Shop client");
    }

}
