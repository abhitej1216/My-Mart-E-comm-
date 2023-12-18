package in.MyMart.service.Implementation;

import in.MyMart.Model.Product;
import in.MyMart.Model.Seller;
import in.MyMart.service.ProductService;
import in.MyMart.Dao.ProductRepository;
import in.MyMart.Dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String addNewProduct(Product product, int sellerId) {
        Seller seller = (Seller) userRepository.findById(sellerId).get();
        product.setSeller(seller);
        productRepository.save(product);
        return "Product Added successfully";
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductBySeller(int sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    @Override
    public String deleteProduct(int productId, int sellerId) {
        List<Product> list = productRepository.findBySellerId(sellerId);
        boolean isAvailable = false;
        for(Product p : list){
            if(p.getId() == productId){
                isAvailable = true;
                break;
            }
        }
        if(isAvailable){
            productRepository.deleteById(productId);
            return "Product Deleted Successfully...";
        }
        return "Product id is invalid";
    }

    @Override
    public Product getProductById(int productId, int sellerId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public String updateProduct(int id, Product product) {
        Optional<Product> opt = productRepository.findById(id);
        if(opt.isPresent()){
            product.setId(id);
            productRepository.save(opt.get());
            return "Product Updated";
        }
        return "Product id is invalid";
    }

    @Override
    public String updatePrice(int id, double price) {
        Optional<Product> opt = productRepository.findById(id);
        if(opt.isPresent()){
            opt.get().setPrice(price);
            productRepository.save(opt.get());
            return "Product Price Updated";
        }
        return "Product id is invalid";
    }
}
