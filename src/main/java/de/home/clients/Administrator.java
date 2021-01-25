package de.home.clients;

import com.google.protobuf.Empty;
import com.google.protobuf.Service;
import com.google.rpc.Status;
import de.home.grpc.AdministratorGrpc;
import de.home.grpc.AdministratorGrpc.*;
import de.home.grpc.Customer;
import de.home.grpc.DemoServiceGrpc;
import de.home.grpc.DemoServiceGrpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Administrator {
    private ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
    private AdministratorBlockingStub adminStub = AdministratorGrpc.newBlockingStub(channel);
    private DemoServiceBlockingStub demoStub = DemoServiceGrpc.newBlockingStub(channel);

    public Status createDemos() {
        return demoStub.createAll(Empty.newBuilder().build());
    }

    public static void main(String[] args) {
        System.out.println("Running gRPC Shop client");
        Administrator admin = new Administrator();
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            boolean finish = false;
            while (!finish) {
                System.out.println("Enter operation code (1: create Demo Data; 2: fetch all items; 3: exit)");
                Integer opr = scanner.nextInt();
                switch (opr) {
                    case 1:
                         System.out.println(admin.createDemos());
                        break;
                    case 2:
                        Customer.AllCustomersResponse customers =  admin.adminStub.getAllCustomer(Empty.newBuilder().build());
                        System.out.println(customers.toString());
                        break;
                    default:
                        finish = true;
                        break;
                }
            }
        } finally {
            if (scanner != null)
                scanner.close();
        }

        try {
            admin.channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
