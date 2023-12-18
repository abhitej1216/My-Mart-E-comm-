package in.MyMart.Dao;

import in.MyMart.Model.Cart_items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<Cart_items, Integer> {
    Cart_items findByProductIdAndBuyerId(Integer product_id, Integer buyer_id);

}
