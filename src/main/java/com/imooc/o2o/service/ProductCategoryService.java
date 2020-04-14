package com.imooc.o2o.service;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getProductCategoryId(Long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList);

    ProductCategoryExecution deleteProductCategory(Long productCategoryId, Long shopId);

}
