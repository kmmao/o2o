package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    /**
     * 添加商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 通过productId获取商品
     * @param productId
     * @return
     */
    Product queryProductById(Long productId);

    /**
     * 更新商品信息
     * @param product
     * @return
     */
    int updateProduct(Product product);


    /**
     * 分页查询商品，可输入的条件有：商品名（模糊），商品状态，商品Id，商品类别；
     * @param productCondition
     * @param woeIndex  从第几行开始取数据
     * @param pageSize  返回的条数
     * @return
     *  必须指定parameter的唯一标识，根据唯一标识去取参数才能取到正确的值，不然不知道取哪一个。
     */

    List<Product> queryProductList(@Param("productCondition") Product productCondition,
                             @Param("rowIndex") int woeIndex, @Param("pageSize") int pageSize);

    /**
     * 返回queryShopList总数
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);


    /**
     * 删除商品类别之前将商品类别ID设置为空
     * @param productCategory
     * @return
     */
    int updateProductCategoryToNull(long productCategory);


}
