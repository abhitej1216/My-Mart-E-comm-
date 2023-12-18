package in.MyMart.service;

import in.MyMart.Model.Cart_items;

import java.util.List;

public interface  CartItemService {
    public String addProductToCart(int productId, int buyerId);
    public List<Cart_items> cartProducts(int buyerId);
    public String removeProductFromCartById(int productId,  int buyerId);
    public String deleteProductFromCartById(int productId,  int buyerId);
}
