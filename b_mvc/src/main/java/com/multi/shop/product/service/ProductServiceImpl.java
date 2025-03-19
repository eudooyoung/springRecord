package com.multi.shop.product.service;

import com.multi.shop.product.model.dao.ProductMapper;
import com.multi.shop.product.model.dto.AttachmentDTO;
import com.multi.shop.product.model.dto.CompanyDTO;
import com.multi.shop.product.model.dto.ProductDTO;
import com.multi.shop.product.model.dto.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> selectList() {
        return productMapper.selectList();
    }

    @Override
    public List<ProductDTO> selectProduct(int pid) {
        return productMapper.selectProduct(pid);
    }

    @Override
    public List<CompanyDTO> selectCompanyList() {
        return productMapper.selectCompanyList();
    }

    @Override
    public int insertProduct(ProductDTO newProduct) {
        int result = productMapper.insertProduct(newProduct);
        List<AttachmentDTO> fileList = newProduct.getAttachments();
        int productId = newProduct.getId();

        for (AttachmentDTO file : fileList) {
            file.setRefProductNo(productId);
            productMapper.insertAttachment(file);
        }

        return result;
    }


    @Override
    public int updateProduct(ProductDTO updateProduct) {
        int result = productMapper.updateProduct(updateProduct);
        List<AttachmentDTO> fileList = updateProduct.getAttachments();
        int productId = updateProduct.getId();

        if(!fileList.isEmpty()) {
            productMapper.deleteAttachment(productId);

            for (AttachmentDTO file : fileList) {
                file.setRefProductNo(productId);
                productMapper.insertAttachment(file);
            }
        }

        return result;
    }

    @Override
    public List<ProductDTO> selectSearchList(SearchCriteria condition) {
        return productMapper.selectSearchList(condition);
    }

    @Override
    public ProductDTO selectProduct2(int productId) {
        return productMapper.selectProduct2(productId);
    }
}
