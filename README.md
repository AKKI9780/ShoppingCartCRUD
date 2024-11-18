# ShoppingCartCRUD
# Shopping Cart Application

This application is a simple implementation of a shopping cart system. It allows users to manage their shopping carts by adding products, updating quantities, and calculating total prices.

---

## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Technology Stack](#technology-stack)
4. [Database Schema](#database-schema)
5. [ShoppingCart POJO](#shoppingcart-pojo)
6. [API Endpoints](#api-endpoints)
7. [JSON Payload Example](#json-payload-example)
8. [Setup and Run](#setup-and-run)
9. [Contributing](#contributing)
10. [License](#license)

---

## Overview

The Shopping Cart Application is designed to handle:
- Adding items to a cart.
- Managing customer-specific shopping carts.
- Updating item quantities and total price calculations.
- Handling CRUD operations for shopping cart entries.

---

## Features
- **Add Product**: Add items to the shopping cart.
- **Update Quantity**: Update the quantity of items in the cart.
- **Calculate Total**: Automatically calculate the total price based on item quantities and price.

---

## Technology Stack

- **Java 11**: Programming language.
- **Spring Boot**: Backend framework for creating RESTful APIs.
- **JPA (Hibernate)**: ORM for database interactions.
- **MySQL**: Database for persisting shopping cart data.
- **Lombok**: Reduces boilerplate code with annotations.

---

## Database Schema

| Column Name   | Data Type       | Constraints          |
|---------------|-----------------|----------------------|
| id            | Long            | Primary Key, Auto-increment |
| customer_name | String          | Not Null            |
| product_name  | String          | Not Null            |
| quantity      | Integer         | Not Null            |
| total_price   | Double          | Not Null            |

---

## ShoppingCart POJO

```java
@Data
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
}
```
---
## API Endpoints

1) Add Item to cart API :

POST http://localhost:8080/api/carts


**Request** :
```json
{
"customerName": "John Doe",
"productName": "Wireless Mouse",
"quantity": 2,
"totalPrice": 39.98
}
```
**Response** :
```json
{
"id": 1,
"customerName": "John Doe",
"productName": "Wireless Mouse",
"quantity": 2,
"totalPrice": 39.98
}
```

2) Get list of carts items :

GET http://localhost:8080/api/carts


3) Update list of carts :

PUT http://localhost:8080/api/carts/{id}

**Request** :
```json
{
"customerName": "John Doe",
"productName": "Wireless Mouse",
"quantity": 2,
"totalPrice": 39.98
}
```
**Response** :
```json
{
"id": 1,
"customerName": "John Doe",
"productName": "Wireless Mouse",
"quantity": 2,
"totalPrice": 39.98
}
```

4) Delete carts :

DELETE http://localhost:8080/api/carts/{id}

---

## How to Run the Application
**Setup RabbitMQ:**

Install and run RabbitMQ. Ensure it is accessible at localhost:5672 with default credentials.

**Setup MySQL:**

Install MySQL and create a database named shopping_cart_db.

Update application.properties with your MySQL username and password.

```
spring.datasource.url=jdbc:mysql://localhost:3306/shopping_cart
spring.datasource.username=root
spring.datasource.password=password
```
---

## Build the Application

```
mvn clean install

```

## Run The Application

```
mvn spring-boot:run
```
