package com.multi.restproduct.product.service;

import com.multi.restproduct.common.paging.SelectCriteria;
import com.multi.restproduct.common.util.FileUploadUtils;
import com.multi.restproduct.product.dao.ProductMapper;
import com.multi.restproduct.product.dto.in.RequestProductDto;
import com.multi.restproduct.product.dto.out.ProductAllDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductService {


    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    public final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public int selectProductTotal() {
        int result = productMapper.selectProductTotal();
        return result;
    }

    public Object selectProductListWithPaging(SelectCriteria selectCriteria) {
        List<ProductAllDto> productList = productMapper.selectProductListWithPaging(selectCriteria);
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).setProductImageUrl(IMAGE_URL + productList.get(i).getProductImageUrl());
        }
        return productList;
    }

    @Transactional
    public Object insertProduct(RequestProductDto resProductDto) {
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;
        try {
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, resProductDto.getProductImage());
            log.info("[ProductService] replaceFileName : " + replaceFileName);

            resProductDto.setProductImageUrl(replaceFileName);

            log.info("[ProductService] insert Image Name : " + replaceFileName);

            result = productMapper.insertProduct(resProductDto);

        } catch (IOException e) {
            log.info("[ProductService] IOException IMAGE_DIR : " + IMAGE_DIR);

            log.info("[ProductService] IOException deleteFile : " + replaceFileName);

            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[ProductService] result > 0 성공: " + result);
        return (result > 0) ? "상품 입력 성공" : "상품 입력 실패";
    }


    @Transactional
    public Object updateProduct(RequestProductDto resProductDto) {
        String replaceFileName = null;
        int result = 0;

        try {
            String oriImage = productMapper.findProductbyId(String.valueOf(resProductDto.getProductCode())).getProductImageUrl();
            log.info("[updateProduct] oriImage : " + oriImage);

            if (resProductDto.getProductImage() != null) {
                // 이미지 변경 진행
                String imageName = UUID.randomUUID().toString().replace("-", "");
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, resProductDto.getProductImage());

                log.info("[updateProduct] IMAGE_DIR!!" + IMAGE_DIR);
                log.info("[updateProduct] imageName!!" + imageName);

                log.info("[updateProduct] InsertFileName : " + replaceFileName);
                resProductDto.setProductImageUrl(replaceFileName);

                log.info("[updateProduct] deleteImage : " + oriImage);
                boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
                log.info("[update] isDelete : " + isDelete);
            } else {
                // 이미지 변경 없을 시
                resProductDto.setProductImageUrl(oriImage);
            }

            result = productMapper.updateProduct(resProductDto);

        } catch (IOException e) {
            log.info("[updateProduct] Exception!!");
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[ProductService] updateProduct End ===================================");
        log.info("[ProductService] result > 0 성공: " + result);

        return (result > 0) ? "상품 업데이트 성공" : "상품 업데이트 실패";
    }

    public int selectProductTotalForAdmin() {
        int result = productMapper.selectProductTotalForAdmin();
        return result;
    }

    public Object selectProductListWithPagingForAdmin(SelectCriteria selectCriteria) {
        List<ProductAllDto> productList = productMapper.selectProductListWithPagingForAdmin(selectCriteria);
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).setProductImageUrl(IMAGE_URL + productList.get(i).getProductImageUrl());
        }
        return productList;
    }

    public Optional<ProductAllDto> selectProductInfoForAdmin(long productCode) {
        Optional<ProductAllDto> product = productMapper.selectProductInfoForAdmin(productCode);
        if (!product.isEmpty()) {
            product.get().setProductImageUrl(IMAGE_URL + product.get().getProductImageUrl());
        }
        return product;
    }

    public Optional<ProductAllDto> selectProductInfo(long productCode) {
        Optional<ProductAllDto> product = productMapper.selectProductInfo(productCode);
        if (!product.isEmpty()) {
            product.get().setProductImageUrl(IMAGE_URL + product.get().getProductImageUrl());
        }
        return product;
    }

    public int searchProductTotal(String search) {
        int result = productMapper.searchProductTotal(search);
        return result;
    }


    public Object searchProductWithPaging(SelectCriteria selectCriteria, String search) {
        List<ProductAllDto> productList = productMapper.searchProductWithPaging(selectCriteria, search);
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).setProductImageUrl(IMAGE_URL + productList.get(i).getProductImageUrl());
        }
        return productList;
    }

}

