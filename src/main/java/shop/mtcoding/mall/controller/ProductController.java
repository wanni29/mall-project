package shop.mtcoding.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController{

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/write")
    public String writePage() {
        return "write";
    }

    @PostMapping ("/product") // 우리가 파싱안하고 스프링에서 알아서 파싱을 해준다
    public void write(String name, int price, int qty){
        System.out.println("name: "+name);
        System.out.println("price: "+price);
        System.out.println("qty: "+qty);
    }

}
