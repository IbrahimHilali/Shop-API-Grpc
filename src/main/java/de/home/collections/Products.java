package de.home.collections;



import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import de.home.entities.Product;

@XmlRootElement(name = "Products")
public class Products {

	
	@XmlElementWrapper(name = "productList")
	@XmlElement(name = "Product")
	private ArrayList<Product> productList;

	public Products() {}
	public Products(ArrayList<Product> products) {}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProdcutList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	

	public Product search(String name) {
		for (Product product : productList) {
			if (product.getName() == name) {
				return product;
			}
		}
		return null;
	}

	public Product find(Integer id) {
		for (Product product : productList) {
			if (product.getId().equals(id)) {
				return product;
			}
		}
		return null;

	}
	
	public Products add(Product product) {
		 productList.add(product);
		return this;
	}


}
