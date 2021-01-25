package de.home.services;


import de.home.collections.Orders;
import de.home.database.Storage;
import de.home.entities.Order;

import java.util.ArrayList;


public class OrderService  {
	Storage storage = new Storage();
	Orders orders = storage.getOrders();


	public ArrayList<Order> getAll() {
		return orders.getOrderList();

	}

	public void newOrder(Order order) {
		orders.add(order);
		storage.setOrders(orders);
		orders = storage.getOrders();
	}

	


}
