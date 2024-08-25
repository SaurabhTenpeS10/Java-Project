package com.jspiders.ecommerceapp.operation;

import java.util.List;
import java.util.Scanner;

import com.jspiders.ecommerceapp.collection.ProductCollection;
import com.jspiders.ecommerceapp.entity.Product;
import com.jspiders.ecommerceapp.jdbc.ProductJDBC;

public class ProductOperation {

	private static ProductCollection productCollection = new ProductCollection();
	ProductJDBC productJDBC = new ProductJDBC();

	public void addProduct(String email, Scanner scanner) {

		System.out.println("Enter product id");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter product title");
		String title = scanner.nextLine();
		System.out.println("Enter product description");
		String description = scanner.nextLine();
		System.out.println("Enter product price");
		double price = scanner.nextDouble();
		String available = "null";
		 System.out.println("Enter \n1 for In Stock  \n2 for Out of Stock");
	        int choice = scanner.nextInt();
	     switch(choice) {
	     case 1 : available= "In Stock";
	     			break;
	     case 2:  available= "Out of Stock";
	     
	     }
		Product product = new Product(id, title, description, price, false);
		List<Product> products = productCollection.getProducts();
//		products.add(product);
		System.out.println("Product added");
		productJDBC.addProduct(id,email,title,description, price, available);
		
		
	}

	public void findProductById(Scanner scanner) {

		System.out.println("Enter product id");
		int id = scanner.nextInt();
//		List<Product> products = productCollection.getProducts();
//		Product productById = null;
//		for (Product product : products) {
//			if (product.getId() == id) {
//				productById = product;
//				break;
//			}
//		}
//		if (productById != null) {
//			System.out.println(productById);
//		} else {
//			System.out.println("Product not found");
//		}
		productJDBC.findProductById(id);
	}

	public void findAllProducts() {

//		List<Product> products = productCollection.getProducts();
//		if (products.size() > 0) {
//			for (Product product : products) {
//				System.out.println(product);
//			}
//		} else {
//			System.out.println("Products not found");
//		}
		productJDBC.findAllProducts();
	}

	public void deleteProduct(String email) {
		productJDBC.deleteProduct(email);
//		List<Product> products = productCollection.getProducts();
//		Product productToBeDeleted = null;
//		for (Product product : products) {
//			if (product.getId() == id) {
//				productToBeDeleted = product;
//				break;
//			}
//		}
//		if (productToBeDeleted != null) {
//			products.remove(productToBeDeleted);
//			System.out.println("Product deleted");
//		} else {
//			System.out.println("Invalid product id");
//		}
		
	}

	public void updateProduct(String email, Scanner scanner) {
		
		productJDBC.updateProduct(email,scanner);
//		System.out.println("Enter product id");
//		int id = scanner.nextInt();
//		List<Product> products = productCollection.getProducts();
//		Product productToBeUpdated = null;
//		for (Product product : products) {
//			if (product.getId() == id) {
//				productToBeUpdated = product;
//				break;
//			}
//		}
//		if (productToBeUpdated != null) {
//
//			scanner.nextLine();
//			System.out.println("Enter product title");
//			String newTitle = scanner.nextLine();
//			System.out.println("Enter product description");
//			String newDescription = scanner.nextLine();
//			System.out.println("Enter product price");
//			double newPrice = scanner.nextDouble();
//			productToBeUpdated.setTitle(newTitle);
//			productToBeUpdated.setDescription(newDescription);
//			productToBeUpdated.setPrice(newPrice);
//			System.out.println("Select product sale status");
//			System.out.println("Enter 1 for sold \nEnter 2 for unsold");
//			int choice = scanner.nextInt();
//			switch (choice) {
//			case 1:
//				productToBeUpdated.setSold(true);
//				break;
//			case 2:
//				productToBeUpdated.setSold(false);
//				break;
//			default:
//				System.out.println("Invalid choice");
//			}
//			System.out.println("Product updated");
//
//		} else {
//			System.out.println("Invalid product id");
//		}
		
	}

}
