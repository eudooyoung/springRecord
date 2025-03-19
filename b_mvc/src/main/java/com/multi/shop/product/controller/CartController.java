package com.multi.shop.product.controller;

import com.multi.shop.product.model.dto.ProductDTO;
import com.multi.shop.product.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody // 안 붙이면 화면 이동 됨
    @PostMapping("/add")
    public String addProductToCard(@RequestParam("productId") int productId, HttpSession session) {

        try{
            ArrayList<ProductDTO> basket = (ArrayList<ProductDTO>) session.getAttribute("basket");

            if (basket == null) {
                basket = new ArrayList<>();
                session.setAttribute("basket", basket);
            }

            ProductDTO productDTO = productService.selectProduct2(productId);
            basket.add(productDTO);
            return String.valueOf(basket.size());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
