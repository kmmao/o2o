package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCategoryServiceTest extends BaseTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void getProductCategoryId(){
        List<ProductCategory> productCategoryId = productCategoryService.getProductCategoryId(19L);

        for (ProductCategory list : productCategoryId) {
            System.out.println("--商品类别--："+list.getProductCategoryName());
        }

        System.out.println("--共记录--："+productCategoryId.size());
    }

//    @Test
    public void testProductCategory() {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("商品店铺5");
        productCategory1.setPriority(9);
        productCategory1.setCreateTime(new Date());
        productCategory1.setShopId(19L);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品店铺6");
        productCategory2.setPriority(10);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(19L);
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(productCategory1);
        productCategories.add(productCategory2);
        ProductCategoryExecution pc = productCategoryService.batchAddProductCategory(productCategories);
        if (pc.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
            System.out.println("创建成功---");
        }

    }

}
