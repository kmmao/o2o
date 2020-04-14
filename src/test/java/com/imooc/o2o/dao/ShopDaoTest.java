package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{

	@Autowired
	private ShopDao shopDao;



//	@Test
	public void testQueryShopList(){
		Shop shopCondition = new Shop();
		ShopCategory shopCategory = new ShopCategory();
		ShopCategory shopCategory1 = new ShopCategory();
		shopCategory1.setShopCategoryId(4L);
		shopCategory.setParent(shopCategory1);
		shopCondition.setShopCategory(shopCategory);

		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 10);
		int i = shopDao.queryShopCount(shopCondition);
		System.out.println("共有数据："+shopList.size()+"条！");
		System.out.println("总数"+i);




	}

//	@Test
	public void testQueryByShopId(){
		Long shopId = 18L;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("店名："+shop.getShopName());
		System.out.println("地址："+shop.getShopAddr());
		System.out.println("区域："+shop.getArea().getAreaName());
	}
	
//	@Test
	public void testInsertShop() {
		
		Shop shop = new Shop();
		Area area = new Area();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		area.setAreaId(2);
		owner.setUserId(1L);
		shopCategory.setShopCategoryId(1L);
		
	    shop.setOwner(owner);
	    shop.setArea(area);
	    shop.setShopCategory(shopCategory);
		shop.setShopName("测试店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(5);
		shop.setAdvice("审核中");
		int i = shopDao.insertShop(shop);
		assertEquals(1,i);
		
	}
	
//	@Test
	public void testUpdateShop() {
		
		Shop shop = new Shop();
		shop.setShopId(8L);
	
		shop.setShopDesc("测试");
		shop.setShopAddr("测试");
		shop.setLastEditTime(new Date());
		int i = shopDao.updateShop(shop);
		assertEquals(1,i);
	}
	
}
