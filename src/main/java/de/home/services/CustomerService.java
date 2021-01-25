package de.home.services;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.protobuf.Empty;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.google.rpc.Status;
import de.home.collections.Orders;
import de.home.database.Storage;
import de.home.entities.Product;
import de.home.grpc.Customer;
import de.home.grpc.CustomerServiceGrpc;
import de.home.grpc.Order;
import io.grpc.stub.StreamObserver;
import google.protobuf.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

public class CustomerService extends CustomerServiceGrpc.CustomerServiceImplBase {
    Storage storage = new Storage();

    @Override
    public void createOrder(Order.CreateOrderRequest request, StreamObserver<Status> responseObserver) {
        de.home.entities.Order order = new Gson()
                .fromJson(
                        request.toBuilder().toString(),
                        de.home.entities.Order.class
                );
        storage.getOrders().add(order);
        responseObserver.onNext(Status.newBuilder().setCode(200).setMessage("Ordered Successfully").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllOrders(Order.RelatedCustomerRequest request, StreamObserver<Order.OrdersResponse> responseObserver) {
        Order.OrdersResponse.Builder ordersResponse = Order.OrdersResponse.newBuilder();
        ArrayList<de.home.entities.Order> orders = storage.getUsers().find((int) request.getCustomerId()).getOrders();
        orders.forEach(item -> {
            Order.OrderResponse.Builder order = Order.OrderResponse.newBuilder();
            try {
                JsonFormat.parser().ignoringUnknownFields().merge(item.toString(), order);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
            ordersResponse.addOrders(order.build());
        });
        responseObserver.onNext(ordersResponse.build());
        responseObserver.onCompleted();
    }


}
