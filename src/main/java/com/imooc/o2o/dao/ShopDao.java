package com.imooc.o2o.dao;

import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

	/**
	 * 分页查询店铺，可输入的条件有：店铺名（模糊），店铺状态，店铺类别，区域Id，owner；
	 * @param shopCondition
	 * @param woeIndex  从第几行开始取数据
	 * @param pageSize  返回的条数
	 * @return
	 *  必须指定parameter的唯一标识，根据唯一标识去取参数才能取到正确的值，不然不知道取哪一个。
	 */

    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
							 @Param("rowIndex") int woeIndex, @Param("pageSize") int pageSize);

	/**
	 * 返回queryShopList总数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);

    /**
     * 店铺查询
     */
    Shop queryByShopId(Long shopId);

    /**
     * 添加店铺
     *
     * @param shop
     * @return
     */
    int insertShop(Shop shop);


    /**
     * 更新
     */


    int updateShop(Shop shop);

}
