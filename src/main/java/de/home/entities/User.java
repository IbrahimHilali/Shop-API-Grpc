package de.home.entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;
import de.home.collections.Orders;
import de.home.database.Storage;

@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "User")
@XmlType(propOrder = {"id", "name","departmentId"})
public class User {
    // @XmlTransient
    private Integer id;
    private String name;
    private Integer departmentId;
    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the orders
     */
    public ArrayList<Order> getOrders() {
        Storage storage = new Storage();
        Orders orders = storage.getOrders();
        if (orders != null && !orders.getOrderList().isEmpty()) {
            orders = orders.findRelatedToUser(id);
        } else {
            orders = new Orders();
        }
        return orders.getOrderList();
    }


    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }

}
