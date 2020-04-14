package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductImg;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testABatchInsertProductImg(){
//        ProductImg productImg = new ProductImg();
//        productImg.setImgAddr("测试1");
//        productImg.setImgDesc("测试1");
//        productImg.setPriority(1);
//        productImg.setCreateTime(new Date());
//        productImg.setProductId(1L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("测试2");
        productImg2.setImgDesc("测试3");
        productImg2.setPriority(2);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(1L);
        ProductImg productImg3 = new ProductImg();
        productImg3.setImgAddr("测试3");
        productImg3.setImgDesc("测试3");
        productImg3.setPriority(3);
        productImg3.setCreateTime(new Date());
        productImg3.setProductId(1L);
        ArrayList<ProductImg> productImgs = new ArrayList<>();
//        productImgs.add(productImg);
        productImgs.add(productImg2);
        productImgs.add(productImg3);
        int i = productImgDao.batchInsertProductImg(productImgs);
        if (i > 0) {
            System.out.println("插入成功！");
        }

    }

}
