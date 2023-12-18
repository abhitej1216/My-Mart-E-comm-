package in.MyMart.service.Implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import in.MyMart.Model.Product;
import in.MyMart.Model.Seller;
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

class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addNewProduct() {
        int sellerId = 1;
        Seller seller = new Seller();
        Product product = new Product();

        when(userRepository.findById(sellerId)).thenReturn(Optional.of(seller));
        when(productRepository.save(product)).thenReturn(product);

        // Act
        String result = productService.addNewProduct(product, sellerId);

        // Assert
        assertEquals("Product Added successfully", result);
        verify(productRepository, times(1)).save(product);
    }



    @Test
    void getAllProduct() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());

        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> result = productService.getAllProduct();

        // Assert
        assertEquals(productList, result);

    }

    @Test
    void getAllProductBySeller() {
        int sellerId = 1;
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());

        when(productRepository.findBySellerId(sellerId)).thenReturn(productList);

        // Act
        List<Product> result = productService.getAllProductBySeller(sellerId);

        // Assert
        assertEquals(productList, result);

    }

    @Test
    void deleteProduct() {
        int productId = 1;
        int sellerId = 1;
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(productId));


        when(productRepository.findBySellerId(sellerId)).thenReturn(productList);

        // Act
        String result = productService.deleteProduct(productId, sellerId);

        // Assert
        assertEquals("Product Deleted Successfully...", result);
        verify(productRepository, times(1)).deleteById(productId);

    }

    @Test
    void getProductById() {
        int productId = 1;
        int sellerId = 1;
        Product product = new Product(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.getProductById(productId, sellerId);

        // Assert
        assertEquals(product, result);
    }

    @Test
    void updateProduct() {
        // Arrange
        int productId = 1;
        Product product = new Product(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        // Act
        String result = productService.updateProduct(productId, product);

        // Assert
        assertEquals("Product Updated", result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void updatePrice() {
        // Arrange
        int productId = 1;
        double price = 10.0;
        Product product = new Product(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        // Act
        String result = productService.updatePrice(productId, price);

        // Assert
        assertEquals("Product Price Updated", result);
        assertEquals(price, product.getPrice());
        verify(productRepository, times(1)).save(product);
    }
}