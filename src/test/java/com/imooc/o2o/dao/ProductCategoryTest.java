package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

//    @Test
    public void testQueryProductCategory() {

        List<ProductCategory> productCategories = productCategoryDao.queryProductCategory(19L);

        for (ProductCategory list : productCategories) {
            System.out.println("商品类别："+list.getProductCategoryName());
        }

        System.out.println("共记录："+productCategories.size());
    }

//    @Test
    public void testProductCategory() {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("商品店铺1");
        productCategory1.setPriority(5);
        productCategory1.setShopId(19L);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品店铺2");
        productCategory2.setPriority(7);
        productCategory2.setShopId(19L);
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(productCategory1);
        productCategories.add(productCategory2);
        int i = productCategoryDao.batchInsertProductCategory(productCategories);
        if (i > 0) {
            System.out.println("成功---");
        }
    }

    @Test
    public void testDeleteProductCategory(){
        Long shopId = 19L;
        int i = productCategoryDao.deleteProductCategory(45L, shopId);
        if (i > 0) {
            System.out.println("删除成功");
        }


    }

}
