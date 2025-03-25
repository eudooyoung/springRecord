package com.multi.restproduct.product.dao;

import com.multi.restproduct.common.paging.SelectCriteria;
import com.multi.restproduct.product.dto.in.RequestProductDto;
import com.multi.restproduct.product.dto.out.ProductAllDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    int selectProductTotal();

    List<ProductAllDto> selectProductListWithPaging(SelectCriteria selectCriteria);

    int insertProduct(RequestProductDto resProductDto);

    ProductAllDto findProductbyId(String s);

    int updateProduct(RequestProductDto resProductDto);

    int selectProductTotalForAdmin();

    List<ProductAllDto> selectProductListWithPagingForAdmin(SelectCriteria selectCriteria);

    Optional<ProductAllDto> selectProductInfoForAdmin(long productCode);

    Optional<ProductAllDto> selectProductInfo(long productCode);

    int searchProductTotal(String search);

    List<ProductAllDto> searchProductWithPaging(@Param("selectCriteria")SelectCriteria selectCriteria, @Param("search")String search);
}
