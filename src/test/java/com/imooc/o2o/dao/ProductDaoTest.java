package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;

//    @Test
    public void testAInsertProduct() {
        Shop shop = new Shop();
        shop.setShopId(19L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(52L);

        Product product = new Product();
        product.setProductName("测试2");
        product.setPriority(23);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setProductCategory(pc);
        product.setShop(shop);
        product.setEnableStatus(0);
        int i = productDao.insertProduct(product);
        if (i > 0) {
            System.out.println("插入成功!");
        }
    }

//    @Test
    public void testUpdateProduct() {
        Shop shop = new Shop();
        shop.setShopId(19L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(53L);
        Product product = new Product();
        product.setShop(shop);
        product.setProductId(44L);

        product.setProductName("二手衣服");
        product.setProductDesc("很好看");
        product.setNormalPrice("400");
        product.setPromotionPrice("200");
        product.setPriority(100);
        product.setLastEditTime(new Date());
        product.setProductCategory(pc);
        int i = productDao.updateProduct(product);
        if (i > 0) {
            System.out.println("处理成功");
        }
    }

//    @Test
    public void testQueryProductById() {

        Product product = productDao.queryProductById(44L);
        for (ProductImg p:product.getProductImgList()) {
            System.out.println(p.getProductImgId());
        }

    }

//    @Test
    public void testQueryProductList() {
        Shop shop = new Shop();
        shop.setShopId(19L);
        Product product = new Product();
        product.setShop(shop);
        List<Product> productList = productDao.queryProductList(product, 0, 3);
        for (Product p:productList) {
            System.out.println(p.getProductName());
        }
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(52L);
        product.setProductCategory(productCategory);
        int i = productDao.queryProductCount(product);
        System.out.println("数量是："+i);
    }

    @Test
    public void testUpdate(){
        int i = productDao.updateProductCategoryToNull(37L);
        if (i > -1) {
            System.out.println("成功删除");
        }
    }

}
