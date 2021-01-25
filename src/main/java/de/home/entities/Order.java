package de.home.entities;

import com.google.gson.Gson;
import de.home.collections.Products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "Product")
@XmlType(propOrder = { "id", "customerId", "total","products"})
public class Order {
	// @XmlTransient
	private Integer id;
	private Integer customerId;
	private Products products ;
	private Integer total;

	public Order() {

	}

	public Order(Integer id, Integer customerId, Integer total, Products products) {
    	this.id = id;
		this.customerId = customerId;
		this.total = total;
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return (new Gson()).toJson(this);
	}

}
