package shop.mtcoding.mall.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


// 요즘 트랜드는 DAO 를 repository로 부른다.
@Repository // 컴포턴트 스캔
public class ProductRepository {

    @Autowired // DBconnection -> 스프링에 만들어진거를 땡겨온다
    private EntityManager em;

    @Transactional
    public void insert(Product product) {
        em.persist(product);
    }
}
