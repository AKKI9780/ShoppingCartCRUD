package com.example.ShoppingCartCRUD.ShppingCart.Service;



import com.example.ShoppingCartCRUD.RabbitMQ.MessageService;
import com.example.ShoppingCartCRUD.ShppingCart.Entity.ShoppingCart;
import com.example.ShoppingCartCRUD.ShppingCart.Repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartRepository cartRepository;
    private final MessageService messageService;

    public ShoppingCart addToCart(ShoppingCart cart) {

        ShoppingCart savedCart = cartRepository.save(cart);
        messageService.sendMessage("cart_exchange", "cart.create", "Created: " + savedCart);
        return savedCart;
    }

    public List<ShoppingCart> getAllCarts() {
        return cartRepository.findAll();
    }

    public ShoppingCart updateCart(Long id, ShoppingCart updatedCart) {
        ShoppingCart cart = cartRepository.findById(id).orElseThrow();
        cart.setCustomerName(updatedCart.getCustomerName());
        cart.setProductName(updatedCart.getProductName());
        cart.setQuantity(updatedCart.getQuantity());
        cart.setTotalPrice(updatedCart.getTotalPrice());
        cartRepository.save(cart);
        messageService.sendMessage("cart_exchange", "cart.update", "Updated: " + cart);
        return cart;
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
        messageService.sendMessage("cart_exchange", "cart.delete", "Deleted cart with ID: " + id);
    }
}
