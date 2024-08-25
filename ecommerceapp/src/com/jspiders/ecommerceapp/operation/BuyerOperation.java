package com.jspiders.ecommerceapp.operation;

import java.sql.*;
import java.util.Scanner;

public class BuyerOperation {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Driver driver;
    

    public void showAllProducts() {
        openConnection();
        String query = "SELECT * FROM products";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") + 
                                   ", Title: " + resultSet.getString("title") + 
                                   ", Description: " + resultSet.getString("description") + 
                                   ", Price: " + resultSet.getDouble("price") + 
                                   ", Availability: " + resultSet.getString("sold"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void searchProduct(String keyword) {
        openConnection();
        String query = "SELECT * FROM products WHERE title LIKE ? OR description LIKE ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") + 
                                   ", Title: " + resultSet.getString("title") + 
                                   ", Description: " + resultSet.getString("description") + 
                                   ", Price: " + resultSet.getDouble("price") + 
                                   ", Availability: " + resultSet.getString("sold"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void addToCart(int productId, String email) {
        openConnection();
        String query = "INSERT INTO cart (user_email, product_id) VALUES (?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, productId);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Product added to cart successfully.");
            } else {
                System.out.println("Failed to add product to cart.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void viewCart(String email) {
        openConnection();
        String query = "SELECT products.id, products.title, products.description, products.price " +
                       "FROM cart " +
                       "JOIN products ON cart.product_id = products.id " +
                       "WHERE cart.user_email = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") + 
                                   ", Title: " + resultSet.getString("title") + 
                                   ", Description: " + resultSet.getString("description") + 
                                   ", Price: " + resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void checkout(String email) {
        openConnection();
        String query = "DELETE FROM cart WHERE user_email = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println("Checkout successful. Cart cleared.");
            } else {
                System.out.println("Cart is empty or checkout failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    
    public void buyCartItems(String email) {
        openConnection();
        try {
            String queryCart = "SELECT product_id, quantity FROM cart WHERE user_email = ?";
            preparedStatement = connection.prepareStatement(queryCart);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Your cart is empty. Please add items to your cart before proceeding.");
                return;
            }

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("quantity");

                // Process each item (e.g., update stock, add to orders, etc.)
            }

            String queryClearCart = "DELETE FROM cart WHERE user_email = ?";
            preparedStatement = connection.prepareStatement(queryClearCart);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();

            System.out.println("Purchase successful. Cart cleared.");

        } catch (SQLException e) {
            System.out.println("Purchase failed. Please try again.");
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    
    
    private void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_app", "root", "Mysql#1432");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeConnection() {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
			if (driver != null) {
				DriverManager.deregisterDriver(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
