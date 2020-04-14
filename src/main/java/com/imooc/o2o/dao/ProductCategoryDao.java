package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface ProductCategoryDao {

    /**
     * 按id获取商品类别
     * @param productCategoryId
     * @return
     */
    List<ProductCategory> queryProductCategory(Long productCategoryId);

    /**
     * 批量插入
     * @param productCategory
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategory);

    /**
     * 按店铺类别id和商品类别id删除
     * @param productCategoryId
     * @param shopId
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") Long productCategoryId,@Param("shopId") Long shopId);

}
