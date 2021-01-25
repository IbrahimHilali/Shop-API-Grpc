package de.home.database;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import de.home.collections.Orders;
import de.home.collections.Products;
import de.home.collections.Users;

import java.io.File;
import java.util.Locale;

public class Storage {

    EntityManager entityManager;

    public Storage() {
        entityManager = new EntityManager();
    }

    public Products getProducts() {
        try {
            entityManager.setPath(getSourcePath(Paths.PRODUCTS_PATH)).setContext(JAXBContext.newInstance(Products.class));
            return (Products) entityManager.read();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public void setProducts(Products products) {
        try {
            entityManager.setPath(getSourcePath(Paths.PRODUCTS_PATH)).setContext(JAXBContext.newInstance(Products.class));
            entityManager.save(products);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Orders getOrders() {
        try {
            entityManager.setPath(getSourcePath(Paths.ORDERS_PATH)).setContext(JAXBContext.newInstance(Orders.class));
            return (Orders) entityManager.read();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void setOrders(Orders orders) {
        try {
            entityManager.setPath(getSourcePath(Paths.ORDERS_PATH)).setContext(JAXBContext.newInstance(Orders.class));
            entityManager.save(orders);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Users getUsers() {
        try {
            entityManager.setPath(getSourcePath(Paths.USERS_PATH)).setContext(JAXBContext.newInstance(Users.class));
            Users users = new Users();
            users = (Users) entityManager.read();
            return users;
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void setUsers(Users users) {
        try {
            entityManager.setPath(getSourcePath(Paths.USERS_PATH)).setContext(JAXBContext.newInstance(Users.class));
            entityManager.save(users);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getSourcePath(String document) {
        File file = new File(Paths.MAIN_RESOURCES_PATH);
        String absolutePath = file.getAbsolutePath();
        System.out.printf("Paht is : %s/%s", absolutePath, document.toLowerCase(Locale.ROOT));

        if (absolutePath.endsWith(Paths.MAIN_RESOURCES_PATH)) {
           System.out.printf("Paht is : %s/%s", absolutePath, document.toLowerCase(Locale.ROOT));
            return "%s/%s".formatted(absolutePath, document.toLowerCase(Locale.ROOT));
        }

        throw new RuntimeException("Data files does not founded");
    }



}
