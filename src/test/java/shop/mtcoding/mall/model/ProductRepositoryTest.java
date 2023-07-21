package shop.mtcoding.mall.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.util.List;
// Tomcat -> DS -> Controller -> (Repository -> DB)
// 확인 하고자 하는것  Repository -> DB
// 성에 쌀창고만 짓고 테스트하는거야, 필요한것만 메모리에 띄워서 테스트

@Import({
        ProductRepository.class,
        SellerRepository.class
}) // TEST에다가 ProductRepository 이거를 끌고 오겠다는 말이네
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Test
    public void findByIdDTO_test() {

        // given
        productRepository.save("바나나", 5000, 50);

        // when
        ProductDTO productDTO = productRepository.findByIdDTO(1);
        System.out.println("====================");
        System.out.println(productDTO.getId());
        System.out.println(productDTO.getName());
        System.out.println(productDTO.getPrice());
        System.out.println(productDTO.getQty());
        System.out.println(productDTO.getDes());
        System.out.println("====================");

        // then
    }

    @Test
    public void findByIdJoinSeller_test() {
        // given
        sellerRepository.save("홍길동", "ssar@name.com");
        productRepository.saveWithFK("바나나", 5000, 50, 1);


        // when
        Product product = productRepository.findByIdJoinSeller(1);

        // then
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());
        System.out.println("-----------" + product.getSeller().getId());
        System.out.println("-----------" + product.getSeller().getName());
        System.out.println("-----------" + product.getSeller().getEmail());


    }

    @Test
    public void findById_test() {

        // given (테스트를 하기 위해서 필요한 데이터 만들기)
        productRepository.save("바나나", 5000, 50);

        // when (테스트 진행)
        Product product = productRepository.findById(1);

        // then (테스트 확인)
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());

    }

    @Test
    public void findByIdJoinSeller2_test() {

        // given (테스트를 하기 위해서 필요한 데이터 만들기)
        productRepository.save("바나나", 5000, 50);

        // when (테스트 진행)
        Product product = productRepository.findById(1);

        // then (테스트 확인)
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());

    }

    @Test
    public void findAll_test() {

        // given
        productRepository.save("바나나", 5000, 50);
        productRepository.save("딸기", 5000, 50);

        // when
        List<Product> entityList = productRepository.findAll();

        // then
        entityList.forEach(entity -> {
            System.out.println(entity);
        });

        for (Product entity : entityList) {
            System.out.println("====================");
            System.out.println(entity.getId());
            System.out.println(entity.getName());
            System.out.println(entity.getPrice());
            System.out.println(entity.getQty());
            System.out.println("====================");
        }

        // 연필 만들기 상상해보기
        // 흑심을 만들고 잘써지는지 확인 -> 나무를 감써서 잘써지는지 확인 -> 디자인하고 -> 최종확인
        // 이러한 과정을 단위 테스트라고 한다. (DB 부분을 , DAO 부분만을 테스트 하는것이다.)
    }

}
