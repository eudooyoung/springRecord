package com.multi.restproduct.product.controller;

import com.multi.restproduct.common.ResponseDto;
import com.multi.restproduct.common.paging.Pagination;
import com.multi.restproduct.common.paging.ResponseDtoWithPaging;
import com.multi.restproduct.common.paging.SelectCriteria;
import com.multi.restproduct.product.dto.in.RequestProductDto;
import com.multi.restproduct.product.dto.out.ProductAllDto;
import com.multi.restproduct.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<ResponseDto> selectProductListWithPaging(@RequestParam(name = "offset", defaultValue = "1") String offset) {

        int totalCount = productService.selectProductTotal();
        int limit = 10;
        int buttonAmount = 10;

        SelectCriteria selectCriteria = Pagination.getSelectCriteria(Integer.parseInt(offset), totalCount, limit, buttonAmount);

        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
        responseDtoWithPaging.setPageInfo(selectCriteria);
        responseDtoWithPaging.setData(productService.selectProductListWithPaging(selectCriteria));

        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
    }

    @PostMapping("/products")
    public ResponseEntity<ResponseDto> insertProduct(@ModelAttribute RequestProductDto productDto) {
        //log.info("[ProductController] Insert Product: {}", productDto);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.CREATED, "상품 입력 성공", productService.insertProduct(productDto)));
    }

    @PutMapping("/products")
    public ResponseEntity<ResponseDto> updateProduct(@ModelAttribute RequestProductDto productDto) {
        // log.info("[ProductController] Update Product: {}", productDto);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "상품 업데이트 성공", productService.updateProduct(productDto)));
    }

    //조회 관리자
    @GetMapping("/products-management")
    public ResponseEntity<ResponseDto> getProductListForAdmin(@RequestParam(name = "offset", defaultValue = "1") int offset) {
        SelectCriteria selectCriteria = getSelectCriteria(offset, productService.selectProductTotalForAdmin());
        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging(productService.selectProductListWithPagingForAdmin(selectCriteria), selectCriteria);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
    }

    private SelectCriteria getSelectCriteria(int offset, int totalCount) {
        int limit = 10;
        int buttonAmount = 10;
        return Pagination.getSelectCriteria(offset, totalCount, limit, buttonAmount);
    }

    /*Optional<MemberDto> member = memberService.selectMemberInfo(memberId);

            if(member.isEmpty()){
                return ResponseEntity.ok(new ResponseDto(HttpStatus.NO_CONTENT, "회원 정보를 찾을 수 없음", null));
            }
            return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "회원 조회 성공", member));*/

    @GetMapping("/products/{productCode}")
    public ResponseEntity<ResponseDto> selectProductInfo(@PathVariable("productCode") long productCode) {
        Optional<ProductAllDto> product = productService.selectProductInfo(productCode);

        if (product.isEmpty()) {
            return ResponseEntity.ok()
                    .body(new ResponseDto(HttpStatus.NO_CONTENT, "상품 정보를 찾을 수 없음", null));
        }
        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK, "상품 조회 성공", product));
    }

    @GetMapping("/products-management/{productCode}")
    public ResponseEntity<ResponseDto> selectProductInfoForAdmin(@PathVariable("productCode") long productCode) {
        Optional<ProductAllDto> product = productService.selectProductInfoForAdmin(productCode);

        if (product.isEmpty()) {
            return ResponseEntity.ok()
                    .body(new ResponseDto(HttpStatus.NO_CONTENT, "상품 정보를 찾을 수 없음", null));
        }
        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK, "상품 조회 성공", product));
    }

    @GetMapping("/products/search")
    public ResponseEntity<ResponseDto> searchProductWithPaging(@RequestParam(name = "query", required = false, defaultValue = "") String search,
                                                               @RequestParam(name = "offset", defaultValue = "1") int offset) {
        SelectCriteria selectCriteria = getSelectCriteria(offset, productService.searchProductTotal(search));
        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging(productService.searchProductWithPaging(selectCriteria, search), selectCriteria);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
    }
}
