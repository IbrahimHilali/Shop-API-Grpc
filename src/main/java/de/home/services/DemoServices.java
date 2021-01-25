package de.home.services;

import java.util.ArrayList;
import java.util.List;


import com.google.protobuf.Empty;
import com.google.rpc.Status;
import de.home.collections.Orders;
import de.home.collections.Products;
import de.home.collections.Users;
import de.home.database.Storage;
import de.home.entities.Order;
import de.home.entities.Product;
import de.home.entities.User;
import de.home.grpc.DemoServiceGrpc;
import io.grpc.stub.StreamObserver;

public class DemoServices extends DemoServiceGrpc.DemoServiceImplBase {
    Storage storage = new Storage();

    @Override
    public void createAll(Empty request, StreamObserver<Status> responseObserver) {
        saveAPIDemoData();
        responseObserver.onNext(Status.newBuilder().setCode(200).setMessage("Successful").build());
        responseObserver.onCompleted();
    }

    @Override
    public void createProducts(Empty request, StreamObserver<Status> responseObserver) {
        super.createProducts(request, responseObserver);
    }

    @Override
    public void createCustomers(Empty request, StreamObserver<Status> responseObserver) {
        super.createCustomers(request, responseObserver);
    }

    @Override
    public void createOrders(Empty request, StreamObserver<Status> responseObserver) {
        super.createOrders(request, responseObserver);
    }

    @Override
    public void createDepartment(Empty request, StreamObserver<Status> responseObserver) {
        super.createDepartment(request, responseObserver);
    }

    protected void saveAPIDemoData() {
        System.out.println(System.getenv("TMPDIR"));
        saveDemoProducts();
        saveDemoOrders();
        saveDemoUsers();
    }

    protected void saveDemoProducts() {
        ArrayList<Product> arrayProducts = new ArrayList<Product>();
        arrayProducts.add(new Product(1, "Audi ", 30000, " Audi A4"));
        arrayProducts.add(new Product(2, "Mazda ", 1000, " Mazda"));
        arrayProducts.add(new Product(3, "BMW ", 3000, " BMW i3"));
        Products products = new Products();
        products.setProdcutList(arrayProducts);
        storage.setProducts(products);

    }

    protected void saveDemoOrders() {
        ArrayList<Order> arrayOrders = new ArrayList<Order>();
        arrayOrders.add(
                new Order(1, 1, 30000,
                        new Products(
                                new ArrayList<Product>(
                                        List.of(
                                                new Product(1, "Audi ", 30000, " Audi A4")
                                                , new Product(2, "Mazda ", 1000, " Mazda")
                                        )
                                )
                        )
                )
        );
        arrayOrders.add(
                new Order(2, 3, 30000,
                        new Products(
                                new ArrayList<Product>(
                                        List.of(
                                                new Product(3, "Audi ", 30000, " Audi A4")
                                                , new Product(2, "Mazda ", 1000, " Mazda")
                                        )
                                )
                        )
                )
        );        arrayOrders.add(
                new Order(3, 1, 30000,
                        new Products(
                                new ArrayList<Product>(
                                        List.of(
                                                new Product(4, "Audi ", 332323, " Audi A4")
                                                , new Product(5, "Mazda ", 33232, " Mazda")
                                        )
                                )
                        )
                )
        );        arrayOrders.add(
                new Order(4, 2, 30000,
                        new Products(
                                new ArrayList<Product>(
                                        List.of(
                                                new Product(1, "Audi ", 30000, " Audi A4")
                                                , new Product(2, "Mazda ", 1000, " Mazda")
                                        )
                                )
                        )
                )
        );
        Orders orders = new Orders();
        orders.setOrderList(arrayOrders);
        storage.setOrders(orders);

    }

    public void saveDemoUsers() {
        ArrayList<User> arrayUsers = new ArrayList<User>();
        arrayUsers.add(new User(1, "User1"));
        arrayUsers.add(new User(2, "User2"));
        arrayUsers.add(new User(3, "User3"));
        arrayUsers.add(new User(4, "Customer1"));
        arrayUsers.add(new User(5, "Customer2"));
        arrayUsers.add(new User(6, "Empolyee"));
        Users users = new Users();
        users.setUserList(arrayUsers);
        storage.setUsers(users);

    }

}
