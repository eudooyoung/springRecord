package com.multi.shop.product.controller;

import com.multi.shop.product.model.dto.ProductDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/product")
public class BasketController {

    @GetMapping("basket")
    public String viewBasket(HttpSession session, Model model) {
        ArrayList<ProductDTO> basket = (ArrayList<ProductDTO>) session.getAttribute("basket");

        if (basket == null) {
            basket = new ArrayList<>();
            // jps에서 세션 값 가져오는거 다시 확인
            session.setAttribute("basket", basket);
        }
        model.addAttribute("basket", basket);
        return "product/basket";
    }
}
