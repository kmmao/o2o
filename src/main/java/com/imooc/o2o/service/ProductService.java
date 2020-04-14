package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.exceptions.ProductOperationException;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.util.List;

public interface ProductService {

    /**
     * 添加图片信息以及图片处理
     * @param product
     * @param thumbnail       缩略图
     * @param productImgList  详情图
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;


    /**
     * 通过商品id查询唯一的商品信息
     * @param productId
     * @return
     */
    Product getProductById(Long productId);


    /**
     * 修改商品信息以及图片处理
     * @param product
     * @param thumbnail
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;


    /**
     * 分页查询商品，可输入的条件有：商品名（模糊），商品状态，商品Id，商品类别；
     * @param productCondition
     * @param pageIndex  从第几行开始取数据
     * @param pageSize  返回的条数
     * @return
     *  必须指定parameter的唯一标识，根据唯一标识去取参数才能取到正确的值，不然不知道取哪一个。
     */

    ProductExecution getProductList(Product productCondition,int pageIndex,int pageSize);


}
