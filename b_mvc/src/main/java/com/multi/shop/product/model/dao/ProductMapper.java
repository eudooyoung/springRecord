package com.multi.shop.product.model.dao;

import com.multi.shop.product.model.dto.AttachmentDTO;
import com.multi.shop.product.model.dto.CompanyDTO;
import com.multi.shop.product.model.dto.ProductDTO;
import com.multi.shop.product.model.dto.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    // 제품 목록 조회
    List<ProductDTO> selectList();

    // 회사 목록 조회
    List<CompanyDTO> selectCompanyList();

    // 제품 삽입
    int insertProduct(ProductDTO newProduct);

    // 첨부 파일 추가
    int insertAttachment(AttachmentDTO attachmentDTO);

    // 제품 상세 조회
    List<ProductDTO> selectProduct(@Param("pid") int pid);

    // 제품 수정
    int updateProduct(ProductDTO newProduct);

    // 첨부 파일 삭제
    int deleteAttachment(@Param("productId") int productId);

    //  검색
    List<ProductDTO> selectSearchList(SearchCriteria condition);

    // 제품 상세 조회 단일
    ProductDTO selectProduct2(@Param("productId") int productId);
}
