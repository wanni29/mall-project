package shop.mtcoding.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.mall.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home() {
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

        productRepository.save(name, price, qty);
        response.sendRedirect("/");

    }

}
