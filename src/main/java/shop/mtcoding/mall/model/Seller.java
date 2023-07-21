package shop.mtcoding.mall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 판매자가 있어야, 상품등록을 할수있다.

@Getter
@Setter
@Table(name = "seller_tb") // 테이블 -> 엔티티, 테이블, 릴레이션
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;

    private String name;
    private String email;

}
