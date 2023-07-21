package shop.mtcoding.mall.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
// Tomcat -> DS -> Controller -> (Repository -> DB)
// 확인 하고자 하는것  Repository -> DB
// 성에 쌀창고만 짓고 테스트하는거야, 필요한것만 메모리에 띄워서 테스트

@Rollback(false) // 메서드 종료시마다 롤백안하기 -> save를 하면 값이 안날라 가겠네 ?
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 진짜 디비로 테스트
@Import(ProductRepository.class) // TEST에다가 ProductRepository 이거를 끌고 오겠다는 말이네
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void save_test() {
        // application.yml ->        ddl-auto: create 설정 -> 서버돌리고 종료후 ->ddl-auto: update 설정
        productRepository.save("바나나", 5000, 50);
    }

    @Test
    public void findById_test() {
        Product product = productRepository.findById(1);
        System.out.println(product.getId());
        System.out.println(product.getName());
    }


}
