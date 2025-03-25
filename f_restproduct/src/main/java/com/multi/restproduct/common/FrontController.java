package com.multi.restproduct.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FrontController {

    @GetMapping({"main" ,"/"})
    public String main(Model model) {

        // 메인 레이아웃 페이지로 이동
        return "main";
    }

    @GetMapping("products/list")
    public void productList() {

    }
    @GetMapping("products/{productCode}")
    public String productDetail(@PathVariable("productCode") String productCode, Model model) {
        model.addAttribute("productCode", productCode);
        return "products/detail";
    }

    @GetMapping("products/register")
    public void addProduct() {

    }

    @GetMapping("products/update/{productCode}")
    public String updateProduct(@PathVariable("productCode") String productCode, Model model) {
        model.addAttribute("productCode", productCode);
        System.out.println("Received productCode: " + productCode);


        return "products/update";
    }


}
