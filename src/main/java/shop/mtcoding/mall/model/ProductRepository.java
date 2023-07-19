package shop.mtcoding.mall.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;


// 요즘 트랜드는 DAO 를 repository로 부른다.
@Repository // 컴포턴트 스캔
public class ProductRepository {

    @Autowired // DBconnection -> 스프링에 만들어진거를 땡겨온다
    private EntityManager em;

    @Transactional // 자원누유를 막는 어노테이션 close역할
    public void save(String name, int price, int qty){
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        // Query는 버퍼임
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }
}
