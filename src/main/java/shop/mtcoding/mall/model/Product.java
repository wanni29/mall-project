package shop.mtcoding.mall.model;

import javax.persistence.*;

@Table(name = "product_tb")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
}
