package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;

//    @Test
    public void testAddProduct() throws ProductOperationException, FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        ProductCategory pc = new ProductCategory();
        shop.setShopId(19L);
        pc.setProductCategoryId(52L);

        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品");
        product.setPriority(34);
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        File file = new File("D:\\src\\renwu.jpg");
        InputStream inputStream = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder("renwu.jpg", inputStream);

        File file1 = new File("D:\\src\\renwu.jpg");
        InputStream is1 = new FileInputStream(file1);
        File file2 = new File("D:\\src\\renwu.jpg");
        InputStream is2 = new FileInputStream(file2);
        ArrayList<ImageHolder> imageHolders = new ArrayList<>();
        imageHolders.add(new ImageHolder(file1.getName(), is1));
        imageHolders.add(new ImageHolder(file2.getName(), is2));

        ProductExecution productExecution = productService.addProduct(product, imageHolder, imageHolders);
        if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
            System.out.println("添加成功！");
        }
    }

    @Test
    public void testUpdateProduct() throws ProductOperationException, FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        ProductCategory pc = new ProductCategory();
        shop.setShopId(19L);
        pc.setProductCategoryId(52L);
        product.setProductId(44L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("正式商品");
        product.setProductDesc("正式商品");
        File file = new File("D:\\src\\meinv.jpg");
        InputStream inputStream = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder("meinv.jpg", inputStream);

        File file1 = new File("D:\\src\\meinv.jpg");
        InputStream is1 = new FileInputStream(file1);
        File file2 = new File("D:\\src\\meinv.jpg");
        InputStream is2 = new FileInputStream(file2);
        ArrayList<ImageHolder> imageHolders = new ArrayList<>();
        imageHolders.add(new ImageHolder(file1.getName(), is1));
        imageHolders.add(new ImageHolder(file2.getName(), is2));

        ProductExecution productExecution = productService.modifyProduct(product, imageHolder, imageHolders);
        if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
            System.out.println("添加成功！");
        }
    }


}
