package com.example.ShoppingCartCRUD.ShppingCart.Controller;


import com.example.ShoppingCartCRUD.ShppingCart.Entity.ShoppingCart;
import com.example.ShoppingCartCRUD.ShppingCart.Service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService cartService;

    @PostMapping
    public ResponseEntity<ShoppingCart> addToCart(@RequestBody ShoppingCart cart) {
        return ResponseEntity.ok(cartService.addToCart(cart));
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> getAllCarts() {

        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateCart(@PathVariable Long id, @RequestBody ShoppingCart cart) {
        return ResponseEntity.ok(cartService.updateCart(id, cart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return ResponseEntity.noContent().build();
    }
}
