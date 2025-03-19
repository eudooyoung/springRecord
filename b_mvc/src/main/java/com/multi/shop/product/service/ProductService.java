package com.multi.shop.product.service;


import com.multi.shop.product.model.dto.CompanyDTO;
import com.multi.shop.product.model.dto.ProductDTO;
import com.multi.shop.product.model.dto.SearchCriteria;

import java.util.List;

public interface ProductService {

    List<ProductDTO> selectList();

    List<CompanyDTO> selectCompanyList();

    int insertProduct(ProductDTO newProduct);

    List<ProductDTO> selectProduct(int pid);

    int updateProduct(ProductDTO newProduct);

    List<ProductDTO> selectSearchList(SearchCriteria condition);

    ProductDTO selectProduct2(int productId);
}
