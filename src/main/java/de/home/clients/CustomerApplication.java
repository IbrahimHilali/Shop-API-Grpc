package de.home.clients;

import com.google.api.AuthenticationRuleOrBuilder;
import com.google.protobuf.Empty;
import com.google.rpc.Status;
import de.home.entities.User;
import de.home.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CustomerApplication {
    private User user;
    private ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
    private CustomerServiceGrpc.CustomerServiceBlockingStub customerStub = CustomerServiceGrpc.newBlockingStub(channel);
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceStub = ProductServiceGrpc.newBlockingStub(channel);

    CustomerApplication(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        System.out.println("Running gRPC Shop client");
        CustomerApplication customer = new CustomerApplication(new User(1, "Test"));
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            boolean finish = false;
            while (!finish) {
                System.out.println("Enter operation code (1: Auflisten Order der Kunde; 2: fetch item with id ; 3:Create new Order ; 4: exit)");
                Integer opr = scanner.nextInt();
                switch (opr) {
                    case 1:
                        Order.RelatedCustomerRequest relatedCustomer = Order.RelatedCustomerRequest.newBuilder()
                                .setCustomerId(customer.user.getId())
                                .build();
                        Order.OrdersResponse orders = customer.customerStub.getAllOrders(relatedCustomer);
                        System.out.println(orders.toString());
                        break;
                    case 3:
                        Product.ProductsResponse products = customer.productServiceStub.getAllProducts(Empty.newBuilder().build());
                        customer.showProductsList(products);

                        Order.CreateOrderRequest.Builder createOrdersRequest = customer.selectProducts(products);
                        createOrdersRequest.setCustomerId(customer.user.getId());
                        Status status = customer.customerStub.createOrder(createOrdersRequest.build());
                        if (status.getCode() == 200) {
                            System.out.println(status.getMessageBytes().toString());
                        } else {
                            System.out.println("You had been not Successfully ordered ");
                        }
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
            customer.channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Order.CreateOrderRequest.Builder selectProducts(Product.ProductsResponse products) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter (exit) to back main menu");
        long total = 0;
        Order.CreateOrderRequest.Builder createdOrderRequest = Order.CreateOrderRequest.newBuilder();
        while (!scanner.hasNext("exit")) {
            System.out.println("Select a Product from List by ID or enter (exit) to return to Main menu");
            long productId = scanner.nextLong();
            Product.ProductRequest product = Product.ProductRequest
                    .newBuilder()
                    .setId(productId).build();
            total += products.getProducts((int) productId).getPrice();
            createdOrderRequest.addProducts(product);
        }
        return createdOrderRequest.setTotal(total).setOrderId(332);
    }

    public void showProductsList(Product.ProductsResponse products) {
        System.out.println("==================Products List===================\n\n");
        for (Product.ProductResponse product : products.getProductsList()) {
            System.out.printf("id :%s , name : %s, price: %s%n", product.getId(), product.getName(), product.getPrice());
        }
        System.out.println("==================Products List===================\n\n");
    }
}
