package in.MyMart.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private String itemTitle;
    private double price;

    //long text data type
    @Lob
    @Nationalized
    private String description;

    private String brand;
    private String category;
    private String image;

    public Product(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnore
    private Seller seller;
}
