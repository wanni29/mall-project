package shop.mtcoding.mall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Table(name = "product_tb") // 테이블 -> 엔티티, 테이블, 릴레이션
@Entity
public class Product { // 연관관계의 주인
// 이게 오브젝트 릴레이션 맵핑임
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;

    @ManyToOne // product 가 Many to one이 seller니까
    private Seller seller;
}
