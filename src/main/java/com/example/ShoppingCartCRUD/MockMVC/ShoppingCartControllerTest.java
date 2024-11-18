package com.example.ShoppingCartCRUD.MockMVC;



import com.example.ShoppingCartCRUD.ShppingCart.Controller.ShoppingCartController;
import com.example.ShoppingCartCRUD.ShppingCart.Entity.ShoppingCart;
import com.example.ShoppingCartCRUD.ShppingCart.Service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingCartControllerTest {

    @Mock
    private ShoppingCartService shoppingCartService;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addToCart_success() {
        ShoppingCart cart = new ShoppingCart();
        cart.setCustomerName("John Doe");
        cart.setProductName("Laptop");
        cart.setQuantity(1);
        cart.setTotalPrice(1000.0);

        when(shoppingCartService.addToCart(cart)).thenReturn(cart);

        ResponseEntity<ShoppingCart> response = shoppingCartController.addToCart(cart);

        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getCustomerName());
        verify(shoppingCartService, times(1)).addToCart(cart);
    }

    @Test
    void getAllCarts_success() {
        List<ShoppingCart> carts = List.of(new ShoppingCart(), new ShoppingCart());
        when(shoppingCartService.getAllCarts()).thenReturn(carts);

        ResponseEntity<List<ShoppingCart>> response = shoppingCartController.getAllCarts();

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(shoppingCartService, times(1)).getAllCarts();
    }
}
