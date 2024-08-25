
# E-Commerce Console Application

## Overview

This project is a Java-based console application that simulates a simple e-commerce platform. It allows users to browse products, manage a shopping cart, and handle inventory.
The application showcases object-oriented programming principles, efficient coding, and a user-friendly design.

## Features

- **Product Browsing**: Users can view a list of available products along with their details.
- **Shopping Cart Management**: Users can add products to their cart, view the cart contents, and remove items.
- **Inventory Management**: Admin users can add, update, or delete products from the inventory.
- **User Authentication**: The application supports basic user authentication for customers and admin roles.

## Installation

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/your-username/e-commerce-console-app.git](https://github.com/SaurabhTenpeS10/Java-Projects.git)
   ```

2. **Navigate to the project directory:**
   ```bash
   cd ecommerceapp
   ```

3. **Compile the project:**
   ```bash
   javac -d bin src/*.java
   ```

4. **Run the application:**
   ```bash
   java -cp bin Main
   ```

## Usage

### User Flow

1. **Register/Login:** Users can register a new account or log in with existing credentials.
2. **Browse Products:** After logging in, users can view available products and their details.
3. **Add to Cart:** Users can add selected products to their cart.
4. **View Cart:** Users can view the contents of their cart and make changes as needed.
5. **Checkout:** Users can proceed to checkout and confirm their order.

### Admin Flow

1. **Login as Admin:** Admin users can log in with special credentials.
2. **Manage Inventory:** Admins can add, update, or delete products in the inventory.
3. **View Orders:** (Future Feature) Admins can view all orders placed by customers.

## Project Structure

- **src/**: Contains all the source code files.
- **bin/**: Contains compiled Java classes.
- **data/**: (Optional) Can be used to store any data files like product lists or user credentials.

## Technologies Used

- Java (Core Java, Collections, Exception Handling)
- JDBC
- MySQL
