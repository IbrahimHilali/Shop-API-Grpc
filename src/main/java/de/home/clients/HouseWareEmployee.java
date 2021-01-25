package de.home.clients;

import com.google.protobuf.Empty;
import de.home.grpc.Order;
import de.home.grpc.OrderServiceGrpc.*;
import de.home.grpc.OrderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class HouseWareEmployee {
    private ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
    private OrderServiceBlockingStub orderServiceStub = OrderServiceGrpc.newBlockingStub(channel);

    public static void main(String[] args) {
        System.out.println("Running gRPC Shop client");
        HouseWareEmployee houseWareEmployee = new HouseWareEmployee();
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            boolean finish = false;
            while (!finish) {
                System.out.println("Enter operation code (1: Auflisten; 2: fetch item with id ; 3: exit)");
                Integer opr = scanner.nextInt();
                switch (opr) {
                    case 1:
                        Order.OrdersResponse orders = houseWareEmployee.orderServiceStub.getAllOrders(Empty.newBuilder().build());
                        System.out.println(orders.toString());
                        break;
                    case 2:
                        System.out.print("Enter Order Id : ");
                        Long id = scanner.nextLong();
                        Order.OrderRequest orderRequest = Order.OrderRequest.newBuilder()
                                .setOrderId(id)
                                .build();
                       Order.OrderResponse orderResponse =houseWareEmployee.orderServiceStub.getOrder(orderRequest);
                        System.out.println(orderResponse.toString());
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
            houseWareEmployee.channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
