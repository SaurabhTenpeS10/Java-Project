package com.jspiders.ecommerceapp.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.jspiders.ecommerceapp.entity.User;
import com.jspiders.ecommerceapp.main.ProductMain;
import com.jspiders.ecommerceapp.operation.ProductOperation;

public class UserJDBC {
   private Driver driver;
   private Connection connection;
   private PreparedStatement preparedStatement;
   private ResultSet resultSet;
   private String query;
   ProductOperation productOperation = new ProductOperation();
   


   public void addUser(User user) {
	   openConnection();
	   try {
		  query = "insert into users values(?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, user.getId());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setLong(4, user.getMobile());
		preparedStatement.setString(5, user.getPassword());
		preparedStatement.setString(6, user.getRole());
		int res = preparedStatement.executeUpdate();
		System.out.println(res + " row(s) affected");
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
	   
   }
 
   public void findUserById(int id) {
	   openConnection();
	   query= "select * from users where id=?";
	   try {
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			System.out.print(resultSet.getInt(1)+" ");
			System.out.print(resultSet.getString(2)+" ");
			System.out.print(resultSet.getString(3)+" ");
			System.out.print(resultSet.getLong(4)+" ");
			System.out.print(resultSet.getString(5)+" ");
			System.out.println(resultSet.getString(6));
		} else {
			System.out.println("User not Found");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
   }
   
   public void findAllUsers() {
	   openConnection();
	   query= "select * from users";
	   try {
		preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			System.out.print(resultSet.getInt(1)+" ");
			System.out.print(resultSet.getString(2)+" ");
			System.out.print(resultSet.getString(3)+" ");
			System.out.print(resultSet.getLong(4)+" ");
			System.out.print(resultSet.getString(5)+" ");
			System.out.println(resultSet.getString(6));
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		} finally {
			closeConnection();
		}
   }
   
   
   public void findUserByEmailAndPassword(String email, String password) {
	   openConnection();
	   
	   query = "select * from users where email=? and password=? ";
	   try {
		preparedStatement= connection.prepareStatement(query);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			System.out.print(resultSet.getInt(1) + " ");
			System.out.print(resultSet.getString(2)+" ");
			System.out.print(resultSet.getString(3)+" ");
			System.out.print(resultSet.getLong(4)+" ");
			System.out.print(resultSet.getString(5)+" ");
			System.out.println(resultSet.getString(6));
			if ("SELLER".equals(resultSet.getString(6))) {
			    ProductMain.main(email);
			} else {
			    ProductMain.buyerMenu(email);
			}
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	      
	}
   
   
   public void deleteUser(int id) {
		openConnection();
		query = "delete from users where id = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			int res = preparedStatement.executeUpdate();
			System.out.println(res + " row(s) affected");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
	}
   
   
   public void userToBeUpdated(String email, String password, Scanner scanner) {
		openConnection();
//		query = "update users set name=?, email=?, mobile=?, password=?, role=? where id=?";
			
		   query = "select * from users where email=? and password=? ";
		   try {
			preparedStatement= connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				int flag=1;
				while(flag==1) {
					System.out.println("Enter \n1 to Update Name \n2 to Update Email \n3 to Update Password"
							+ "\n4 to Update Mobile \n5 to Change Role \n6 to Exit");
					int choice = scanner.nextInt();
					scanner.nextLine();
					switch(choice) {
					case 1 : {
						String query1 = "update users set name=? where email=?";
						preparedStatement= connection.prepareStatement(query1);
						String newName = scanner.nextLine();
						preparedStatement.setString(1, newName);
						preparedStatement.setString(2, email);
						preparedStatement.executeUpdate();
						break;
					}
					case 2: {
						String query1 = "update users set email=? where email=?";
						preparedStatement= connection.prepareStatement(query1);
						String newEmail = scanner.nextLine();
						preparedStatement.setString(1, newEmail);
						preparedStatement.setString(2, email);
						preparedStatement.executeUpdate();
						break;
					}
					case 3: {
						String query1 = "update users set password=? where email=?";
						preparedStatement= connection.prepareStatement(query1);
						String newPassword = scanner.nextLine();
						preparedStatement.setString(1, newPassword);
						preparedStatement.setString(2, email);
						preparedStatement.executeUpdate();
						break;
					}
					case 4: {
						String query1 = "update users set mobile=? where=?";
						preparedStatement= connection.prepareStatement(query1);
						long newMobile = scanner.nextLong();
						preparedStatement.setLong(1, newMobile);
						preparedStatement.setString(2, email);
						preparedStatement.executeUpdate();
						break;
					}
					case 5: {
						String query1 = "update users set role=? where email=?";
						System.out.println("Enter 1 for SELLER \nEnter 2 for BUYER");
						int choice1 = scanner.nextInt();
						String newRole = "";
						switch (choice1) {
						case 1:
							newRole = "SELLER";
							break;
						case 2:
							newRole = "BUYER";
							break;
						default:  System.out.println("Invalid choice. No changes made.");
						}
						if(!newRole.isEmpty()) {
						preparedStatement= connection.prepareStatement(query1);
						preparedStatement.setString(1, newRole);
						preparedStatement.setString(2, email);
						preparedStatement.executeUpdate();
						}
						break;
					}
					default : System.out.println("Invalid Input");
					           flag=0;
							
					}
					

				} 
				
			}
			else {
				System.err.println("Wrong Email or Password...");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
   }
   
   
   private void openConnection()  {
	   try
	   {
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   driver = new com.mysql.cj.jdbc.Driver();
	   DriverManager.registerDriver(driver);
	   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_app" ,"root","Mysql#1432");
	   }
	   catch(Exception e) {
		   e.printStackTrace();
	   }
   }
   
   private void closeConnection() {
	   try {
		   if(preparedStatement !=null) {
			   preparedStatement.close();
		   }
		   if(connection != null) {
			   connection.close();
		   }
		   if(driver != null) {
			   DriverManager.deregisterDriver(driver);
		   }
	   }
	   catch(Exception e) {
		   e.printStackTrace();
	   }
   }





}
