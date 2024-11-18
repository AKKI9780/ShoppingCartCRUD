package com.example.ShoppingCartCRUD.MockMVC;

import com.example.ShoppingCartCRUD.RabbitMQ.MessageService;
import com.example.ShoppingCartCRUD.ShppingCart.Entity.ShoppingCart;
import com.example.ShoppingCartCRUD.ShppingCart.Repository.ShoppingCartRepository;
import com.example.ShoppingCartCRUD.ShppingCart.Service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private ShoppingCart shoppingCart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setCustomerName("John Doe");
        shoppingCart.setProductName("Laptop");
        shoppingCart.setQuantity(1);
        shoppingCart.setTotalPrice(1000.0);
    }

    @Test
    void addToCart_success() {
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(shoppingCart);

        ShoppingCart savedCart = shoppingCartService.addToCart(shoppingCart);

        assertNotNull(savedCart);
        assertEquals("John Doe", savedCart.getCustomerName());
        verify(messageService, times(1)).sendMessage(anyString(), anyString(), anyString());
    }

    @Test
    void updateCart_success() {
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(shoppingCart));
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(shoppingCart);

        shoppingCart.setQuantity(2);
        ShoppingCart updatedCart = shoppingCartService.updateCart(1L, shoppingCart);

        assertNotNull(updatedCart);
        assertEquals(2, updatedCart.getQuantity());
        verify(messageService, times(1)).sendMessage(anyString(), anyString(), anyString());
    }

    @Test
    void updateCart_failure() {
        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> shoppingCartService.updateCart(1L, shoppingCart));
        assertEquals("Cart not found", exception.getMessage());
    }

    @Test
    void removeFromCart_success() {
        when(shoppingCartRepository.existsById(1L)).thenReturn(true);

        shoppingCartService.removeFromCart(1L);

        verify(shoppingCartRepository, times(1)).deleteById(1L);
        verify(messageService, times(1)).sendMessage(anyString(), anyString(), anyString());
    }
}
