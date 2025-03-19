package com.multi.shop.product.controller;

import com.multi.shop.product.model.dto.CompanyDTO;
import com.multi.shop.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @Controller
// 주로 뷰(View)를 반환하는 경우 사용
// @ResponseBody를 명시적으로 붙이지 않으면 뷰리졸버를 통해 뷰 템플릿(Thymeleaf, JSP 등)을 찾음
// @Controller는 뷰를 반환하는 것이 기본 동작이므로, 데이터(json 등)를 반환하려면 @ResponseBody를 붙여야 함

// @RestController
// @Controller + @ResponseBody가 포함된 개념
// JSON, XML과 같은 데이터를 반환할 때 사용
// HTTP 메시지 컨버터(MessageConverter)를 통해 json 변환

@RestController // @ResponseBody json등 데이터 형식으로 리턴해줄 수 있음
@RequestMapping("/product")
public class CompanyListController {

    private final ProductService productService;

    public CompanyListController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/companylist")
    public ResponseEntity<List<CompanyDTO>> getCompanyList() {
        try{
            List<CompanyDTO> companyDTOList = productService.selectCompanyList();
            // 성공적으로 조회시 상태코드 200과 함께 디에터 전달
            return ResponseEntity.ok(companyDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            // 실패시 500 에러 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
