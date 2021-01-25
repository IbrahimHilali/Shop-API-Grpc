package de.home.collections;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import de.home.entities.Order;

@XmlRootElement(name = "Orders")
public class Orders {

	
	//@XmlElementWrapper(name = "orderList")
	//@XmlElement(name = "Order.proto")
	private ArrayList<Order> orderList;

	public Orders() {}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}
	

	public Order search(String name) {
		for (Order order : orderList) {
			if (order.getTitle().contains(name)) {
				return order;
			}
		}
		return null;
	}

	public Order find(Integer id) {
		for (Order order : orderList) {
			if (order.getId().equals(id)) {
				return order;
			}
		}
		return null;

	}
	
	public Orders  findRelatedToUser(Integer id) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order order : orderList) {
			if (order.getCustomerId().equals(id)) {
				orders.add(order);
			}
		}
		Orders filtered =  (new Orders());
		filtered.setOrderList(orders);
		return filtered;

	}
	public Orders add(Order order) {
		 orderList.add(order);
		return this;
	}


}
