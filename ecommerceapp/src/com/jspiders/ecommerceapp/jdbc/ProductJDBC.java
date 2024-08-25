package com.jspiders.ecommerceapp.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductJDBC {
	private Driver driver;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String query;

	public void addProduct(int id, String email, String title, String description, double price, String available) {
		openConnection();
		query = "insert into products values(?,?,?,?,?)";
		String query1 = "insert into sellerproducts values(?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, description);
			preparedStatement.setDouble(4, price);
			preparedStatement.setString(5, available);
			boolean res = preparedStatement.execute();
//			System.out.println(res);
			preparedStatement = connection.prepareStatement(query1);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, title);
			preparedStatement.setString(4, description);
			preparedStatement.setDouble(5, price);
			res = preparedStatement.execute();
//			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void findProductById(int id) {
		openConnection();
		query = "select * from products where id=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.print(resultSet.getInt(1) + " ");
				System.out.print(resultSet.getString(2) + " ");
				System.out.print(resultSet.getString(3) + " ");
				System.out.print(resultSet.getDouble(4) + " ");
				System.out.println(resultSet.getString(5));

			} else {
				System.out.println("Product not Found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void findAllProducts() {
		openConnection();
		query = "select * from products";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.print(resultSet.getInt(1) + " ");
				System.out.print(resultSet.getString(2) + " ");
				System.out.print(resultSet.getString(3) + " ");
				System.out.print(resultSet.getDouble(4) + " ");
				System.out.println(resultSet.getString(5));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void deleteProduct(String email) {
		openConnection();
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> al = new ArrayList<Integer>();
		String query1 = "select * from sellerproducts where email=?";
		try {
			preparedStatement = connection.prepareStatement(query1);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next() ) {
				System.out.print(resultSet.getInt("pid" + " "));
				al.add(resultSet.getInt(1));
				System.out.print(resultSet.getString("title")+" ");
				System.out.print(resultSet.getString("description"+ " "));
				System.out.println(resultSet.getDouble("price"));
			}
			System.out.println("Enter Product Id to Delete");
			int pid = scanner.nextInt();
			boolean flag=false;
			if(al.contains(pid)) {
				flag=true;
			} else {
				System.out.println("You Cannot Delete This Product");
			}
			if(flag) {
				query = "delete from products where id=?";
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, pid);
				int res = preparedStatement.executeUpdate();
				System.out.println(res + "row(s) deleted");
				
				String query2 = "delete from sellerproducts where pid=?";
				preparedStatement = connection.prepareStatement(query2);
				preparedStatement.setInt(1, pid);
				preparedStatement.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter Product id to Delete");
//		int id = scanner.nextInt();
//		
//		query = "delete from products where id = ? ";
//		try {
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, id);
//			int res = preparedStatement.executeUpdate();
//			System.out.println(res + " row(s) affected");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			closeConnection();
//		}
		
	}

	public void updateProduct(String email, Scanner scanner) {
	    openConnection();
	    ArrayList<Integer> al = new ArrayList<>();
	    String query1 = "select * from sellerproducts where email=?";
	    try {
	        preparedStatement = connection.prepareStatement(query1);
	        preparedStatement.setString(1, email);
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            System.out.print(resultSet.getInt("pid") + " ");
	            al.add(resultSet.getInt(1));
	            System.out.print(resultSet.getString("title") + " ");
	            System.out.print(resultSet.getString("description") + " ");
	            System.out.println(resultSet.getDouble("price"));
	        }
	        System.out.println("Enter Product Id to Update");
	        int pid = scanner.nextInt();
	        boolean flag = false;
	        if (al.contains(pid)) {
	            flag = true;
	        } else {
	            System.out.println("You Cannot Update this Product");
	        }
	        if (flag) {
	            int whileflag = 1;
	            while (whileflag == 1) {
	                System.out.println("Enter \n1 to Update Name \n2 to Update Email \n3 to Update Description"
	                        + "\n4 to Update Price \n5 to Update Stock Status \n6 to Exit");
	                int choice = scanner.nextInt();
	                scanner.nextLine();
	                switch (choice) {
	                    case 1: {
	                        // Update title
	                        System.out.println("Enter new title:");
	                        String newTitle = scanner.nextLine();
	                        // Update sellerproducts
	                        String query = "update sellerproducts set title=? where email=? and pid=?";
	                        preparedStatement = connection.prepareStatement(query);
	                        preparedStatement.setString(1, newTitle);
	                        preparedStatement.setString(2, email);
	                        preparedStatement.setInt(3, pid);
	                        preparedStatement.executeUpdate();
	                        // Update products
	                        query = "update products set title=? where id=?";
	                        preparedStatement = connection.prepareStatement(query);
	                        preparedStatement.setString(1, newTitle);
	                        preparedStatement.setInt(2, pid);
	                        preparedStatement.executeUpdate();
	                        break;
	                    }
	                    case 2: {
	                        // Update email
	                        System.out.println("Enter new email:");
	                        String newEmail = scanner.nextLine();
	                        String query = "update sellerproducts set email=? where email=? and pid=?";
	                        preparedStatement = connection.prepareStatement(query);
	                        preparedStatement.setString(1, newEmail);
	                        preparedStatement.setString(2, email);
	                        preparedStatement.setInt(3, pid);
	                        preparedStatement.executeUpdate();
	                        break;
	                    }
	                    case 3: {
	                        // Update description
	                        System.out.println("Enter new description:");
	                        String newDescription = scanner.nextLine();
	                        // Update sellerproducts
	                        String query = "update sellerproducts set description=? where email=? and pid=?";
	                        preparedStatement = connection.prepareStatement(query);
	                        preparedStatement.setString(1, newDescription);
	                        preparedStatement.setString(2, email);
	                        preparedStatement.setInt(3, pid);
	                        preparedStatement.executeUpdate();
	                        // Update products
	                        query = "update products set description=? where id=?";
	                        preparedStatement = connection.prepareStatement(query);
	                        preparedStatement.setString(1, newDescription);
	                        preparedStatement.setInt(2, pid);
	                        preparedStatement.executeUpdate();
	                        break;
	                    }
	                    case 4: {
	                        // Update price
	                        System.out.println("Enter new price:");
	                        double newPrice = scanner.nextDouble();
	                        // Update sellerproducts
	                        String query = "update sellerproducts set price=? where email=? and pid=?";
	                        preparedStatement = connection.prepareStatement(query);
	                        preparedStatement.setDouble(1, newPrice);
	                        preparedStatement.setString(2, email);
	                        preparedStatement.setInt(3, pid);
	                        preparedStatement.executeUpdate();
	                        // Update products
	                        query = "update products set price=? where id=?";
	                        preparedStatement = connection.prepareStatement(query);
	                        preparedStatement.setDouble(1, newPrice);
	                        preparedStatement.setInt(2, pid);
	                        preparedStatement.executeUpdate();
	                        break;
	                    }
	                    case 5: {
	                        // Update stock status
	                        System.out.println("Enter \n1 for In Stock  \n2 for Out of Stock");
	                        int availabilityChoice = scanner.nextInt();
	                        String available = "";
	                        switch (availabilityChoice) {
	                            case 1:
	                                available = "In Stock";
	                                break;
	                            case 2:
	                                available = "Out of Stock";
	                                break;
	                            default:
	                                System.out.println("Invalid choice. No changes made.");
	                                break;
	                        }
	                        if (!available.isEmpty()) {
	                            String query = "update products set sold=? where id=?";
	                            preparedStatement = connection.prepareStatement(query);
	                            preparedStatement.setString(1, available);
	                            preparedStatement.setInt(2, pid);
	                            preparedStatement.executeUpdate();
	                        }
	                        break;
	                    }
	                    case 6: {
	                        // Exit
	                        whileflag = 0;
	                        break;
	                    }
	                    default: {
	                        System.out.println("Invalid Input");
	                    }
	                }
	            }
	        }
	    } catch (SQLException e) {
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