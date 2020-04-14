package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategoryId(Long shopId) {
        return productCategoryDao.queryProductCategory(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            for (ProductCategory pc:productCategoryList) {
                pc.setCreateTime(new Date());
            }
            int i = productCategoryDao.batchInsertProductCategory(productCategoryList);
            try {
                if (i <= 0) {
                    throw new ProductCategoryOperationException("店铺类别添加失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error" + e.toString());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }


    /**
     * TODO先将商品里的productCategoryId设为空，再删除商品类别；
     * @param productCategoryId
     * @param shopId
     * @return
     */
    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(Long productCategoryId, Long shopId) {

        //先将tb_product里的商品与productCategoryId关联解除
        try {
            int i = productDao.updateProductCategoryToNull(productCategoryId);
            if (i < 0) {
                throw new ProductOperationException("更新商品失败！");
            }

        } catch (Exception e) {
            throw new ProductOperationException("deleteProductCategory error" + e.toString());
        }


        //在删除指定删除列表
        try {
            int i = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (i <= 0) {
                throw new ProductCategoryOperationException("删除店铺类别失败！");
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategory error" + e.toString());
        }
    }


}
