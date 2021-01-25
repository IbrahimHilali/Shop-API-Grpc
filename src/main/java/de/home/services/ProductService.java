package de.home.services;


import de.home.collections.Products;
import de.home.database.Storage;
import de.home.entities.Product;

public class ProductService {
	Storage storage = new Storage();
	Products products = storage.getProducts();


}
