package com.jspiders.ecommerceapp.main;

import java.util.Scanner;

import com.jspiders.ecommerceapp.operation.BuyerOperation;
import com.jspiders.ecommerceapp.operation.ProductOperation;

public class ProductMain {
    private static ProductOperation productOperation = new ProductOperation();
    private static BuyerOperation buyerOperation = new BuyerOperation();

    public static void main(String email) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("=========================================================================================================================================================");
            System.out.println("Enter 1 to Add Product \nEnter 2 to Find Product by Id \nEnter 3 to Find All Products \nEnter 4 to Delete Product \nEnter 5 to Update Product \nEnter 6 to Buyer Menu \nEnter 7 to Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    productOperation.addProduct(email, scanner);
                    break;
                case 2:
                    productOperation.findProductById(scanner);
                    break;
                case 3:
                    productOperation.findAllProducts();
                    break;
                case 4:
                    productOperation.deleteProduct(email);
                    break;
                case 5:
                    productOperation.updateProduct(email, scanner);
                    break;
                
                case 6:
                    buyerMenu(email);
                    break;
                case 7:
                	flag=false;
                	break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }

    
    public static void buyerMenu(String email) {
    	Scanner scanner = new Scanner(System.in);
    	boolean buyerFlag = true;
        while (buyerFlag) {
            System.out.println("Enter 1 to Show All Products \nEnter 2 to Search Product \nEnter 3 to Add to Cart \nEnter 4 to View Cart \nEnter 5 to Checkout \nEnter 6 to Buy Cart Items \nEnter 7 to Go Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    buyerOperation.showAllProducts();
                    break;
                case 2:
                    System.out.println("Enter keyword to search:");
                    String keyword = scanner.nextLine();
                    buyerOperation.searchProduct(keyword);
                    break;
                case 3:
                    System.out.println("Enter product ID to add to cart:");
                    int productId = scanner.nextInt();
                    buyerOperation.addToCart(productId, email);
                    break;
                case 4:
                    buyerOperation.viewCart(email);
                    break;
                case 5:
                    buyerOperation.checkout(email);
                    break;
                case 6:
                	 buyerOperation.buyCartItems(email);
                	    break;
                case 7:
                	 buyerFlag=false;
               	    break;
                default:
                    System.out.println("Invalid choice");
                   
            }
        }
        scanner.close();
    }
}
