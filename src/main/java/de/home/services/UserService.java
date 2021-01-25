package de.home.services;

import de.home.collections.Orders;
import de.home.collections.Users;
import de.home.database.Storage;
import de.home.entities.Order;
import de.home.entities.User;

import java.util.ArrayList;

public class UserService {
	Storage storage = new Storage();
	Users users = storage.getUsers();

	public void newOrderforClient( Integer id, Order order) {
		System.out.println(order);
		order.setCustomerId(id);
		Orders orders = storage.getOrders();
		orders.add(order);
		storage.setOrders(orders);

	}

	public ArrayList<Order> getordedProducts(Integer id) {
		User user = users.find(id);
		System.out.println(user);
		System.out.println(user.getOrders());
		return user.getOrders();
	}

	public User getDetails( Integer id) {

		return users.find(id);

	}

}
