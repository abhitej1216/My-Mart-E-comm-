package in.MyMart.service.Implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import in.MyMart.Model.Buyer;
import in.MyMart.Model.Cart_items;
import in.MyMart.Model.Product;
import in.MyMart.Dao.CartItemRepository;
import in.MyMart.Dao.ProductRepository;
import in.MyMart.Dao.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class CartItemServiceImplTest {
    @Mock
    private CartItemRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartItemServiceImpl cartItemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addProductToCart() {
        // Arrange
        int productId = 1;
        int buyerId = 1;
        Product product = new Product();
        Buyer buyer = new Buyer();
        Cart_items cartItems = new Cart_items();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(userRepository.findById(buyerId)).thenReturn(Optional.of(buyer));
        when(cartRepository.save(any(Cart_items.class))).thenReturn(cartItems);

        // Act
        String result = cartItemService.addProductToCart(productId, buyerId);

        // Assert
        assertEquals("Product added to cart", result);
        verify(userRepository, times(1)).save(buyer);
    }

//    @Test
//    void cartProducts() {
//        // Arrange
//        int buyerId = 1;
//        Buyer buyer = new Buyer();
//        List<Cart_items> cartItemsList = new ArrayList<>();
//        cartItemsList.add(new Cart_items());
//
//        when(userRepository.findById(buyerId)).thenReturn(Optional.of(buyer));
//        when(buyer.getCart_items()).thenReturn(cartItemsList);
//
//        // Act
//        List<Cart_items> result = cartItemService.cartProducts(buyerId);
//
//        // Assert
//        assertEquals(cartItemsList, result);
//    }




    @Test
    void removeProductFromCartById() {
        // Arrange
        int productId = 1;
        int buyerId = 1;
        Cart_items cartItems = new Cart_items();
        cartItems.setQuantity(2);

        when(cartRepository.findByProductIdAndBuyerId(productId, buyerId)).thenReturn(cartItems);

        // Act
        String result = cartItemService.removeProductFromCartById(productId, buyerId);

        // Assert
        assertEquals("Product removed successfully", result);
        verify(cartRepository, times(1)).save(cartItems);

    }

//    @Test
//    void deleteProductFromCartById() {
//        // Arrange
//        int productId = 1;
//        int buyerId = 1;
//        Buyer buyer = new Buyer();
//        Cart_items cartItems = new Cart_items();
//        cartItems.setCartID(1);
//
//        when(userRepository.findById(buyerId)).thenReturn(Optional.of(buyer));
//        when(cartRepository.findByProductIdAndBuyerId(productId, buyerId)).thenReturn(cartItems);
//
//        // Act
//        String result = cartItemService.deleteProductFromCartById(productId, buyerId);
//
//        // Assert
//        assertEquals("Product deleted from cart", result);
//        verify(userRepository, times(1)).save(buyer);
//        verify(cartRepository, times(1)).deleteById(cartItems.getCartID());
//    }
}