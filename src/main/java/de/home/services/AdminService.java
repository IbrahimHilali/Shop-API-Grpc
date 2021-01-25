package de.home.services;

import com.google.protobuf.Empty;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import de.home.database.Storage;
import de.home.entities.User;
import de.home.grpc.Customer.*;
import de.home.grpc.*;
import io.grpc.stub.StreamObserver;

import java.util.stream.Stream;

public class AdminService extends AdministratorGrpc.AdministratorImplBase {
    Storage storage = new Storage();

    @Override
    public void getAllCustomer(Empty request, StreamObserver<Customer.AllCustomersResponse> responseObserver) {
       AllCustomersResponse.Builder customers = AllCustomersResponse.newBuilder();
        storage.getUsers().getuserList().forEach(item ->{
            CustomerResponse.Builder customer = CustomerResponse.newBuilder();
            try {
                JsonFormat.parser().ignoringUnknownFields().merge(item.toString(), customer);
                customers.addCustomer(customer);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        });
        responseObserver.onNext(customers.build());
        responseObserver.onCompleted();

    }

    @Override
    public void getCustomerDetails(Customer.GetCustomerRequest request, StreamObserver<Customer.CustomerResponse> responseObserver) {
        super.getCustomerDetails(request, responseObserver);
    }

    @Override
    public void updateCustomerDetails(Customer.UpdateCustomerDetailsRequest request, StreamObserver<Customer.CustomerResponse> responseObserver) {
        super.updateCustomerDetails(request, responseObserver);
    }

    @Override
    public void deleteCustomer(Customer.DeleteCustomerRequest request, StreamObserver<Customer.CustomerResponse> responseObserver) {
        super.deleteCustomer(request, responseObserver);
    }
}
