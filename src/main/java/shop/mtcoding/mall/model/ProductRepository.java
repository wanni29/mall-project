package shop.mtcoding.mall.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


// 요즘 트랜드는 DAO 를 repository로 부른다.
@Repository // 컴포턴트 스캔
public class ProductRepository {

    @Autowired // DBconnection -> 스프링에 만들어진거를 땡겨온다
    private EntityManager em; // 얘가 디비랑 연결되는 역할

    @Transactional // 자원누유를 막는 어노테이션 close역할
    public void save(String name, int price, int qty) {
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        // Query는 버퍼임
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }

    public List<Product> findAll() {
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        List<Product> productList = query.getResultList();
        return productList;
    }

    public Product findById(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class);
        query.setParameter("id", id);
        Product product = (Product) query.getSingleResult(); //  query.getSingleResult(); -> 상위 오브젝트니깐, 다운캐스팅해야됨
        return product;
    }

    public Product findById2(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id");
        query.setParameter("id", id);
        // 이 모든 과정이 -> Product.class 이걸로 퉁!
        // row가 1건
        // 1, 바나나, 1000, 50
        Object[] object = (Object[]) query.getSingleResult();
        int id2 = (int) object[0];
        String name2 = (String) object[1];
        int price2 = (int) object[2];
        int qty2 = (int) object[3];

        Product product = new Product();
        product.setId(id2);
        product.setName(name2);
        product.setPrice(price2);
        product.setQty(qty2);
        return product;
    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from product_tb where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


    @Transactional
    public void update(String name, int id, int price, int qty) {
        Query query = em.createNativeQuery("update product_tb set name = :name,  price = :price, qty = :qty  where id = :id", Product.class);
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }
}

