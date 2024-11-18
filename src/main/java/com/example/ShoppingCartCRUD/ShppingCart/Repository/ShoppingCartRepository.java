package com.example.ShoppingCartCRUD.ShppingCart.Repository;


import com.example.ShoppingCartCRUD.ShppingCart.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
