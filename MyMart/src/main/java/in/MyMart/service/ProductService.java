package in.MyMart.service;

import in.MyMart.Model.Product;

import java.util.List;

public interface ProductService {
    String addNewProduct(Product product, int sellerId);
    List<Product> getAllProduct();
    List<Product> getAllProductBySeller( int sellerId);
    String deleteProduct(int productId, int sellerId);
    Product getProductById(int productId, int sellerId);
    String updateProduct(int productId,  Product product);
    String updatePrice(int productId, double price);
}
