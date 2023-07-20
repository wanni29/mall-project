package shop.mtcoding.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.mall.model.Product;
import shop.mtcoding.mall.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    // 리퀘스트 가방을 새로 만들 필요가 있을까? -> 코드가 중복되기때문에?? 만들면 안된다.
    @PostMapping("/product/delete")
    public String delete(int id) {
        productRepository.deleteById(id);
         return "redirect:/";
    }

    @PostMapping("/product/update")
    public String update(String name, int id, int price, int qty) {
        productRepository.update(name, id, price, qty);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        System.out.println("id : " + id);
        Product product = productRepository.findById(id);
        request.setAttribute("p", product); // 뷰에서 꺼내 쓸수있다. // EL 표현식
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());
        return "detail";
    }



    @GetMapping("/")
    public String home(HttpServletRequest request) {
        List<Product> productList = productRepository.findAll();
        request.setAttribute("productList", productList);
        return "home";
    }



    @GetMapping("/write")
    public String writePage() {
        return "write";
    }

    @PostMapping("/product") // 우리가 파싱안하고 스프링에서 알아서 파싱을 해준다
    public void write(String name, int price, int qty, HttpServletResponse response) throws IOException {
        System.out.println("name: " + name);
        System.out.println("price: " + price);
        System.out.println("qty: " + qty);


        // 컨트롤러의 메서드를 재호출하는 방법
        productRepository.save(name, price, qty);
        response.sendRedirect("/");
        // return "redirect:/";
    }

}
