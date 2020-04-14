package com.imooc.o2o.dto;

import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.enums.ShopStateEnum;

import java.util.List;

public class ProductCategoryExecution {

	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private List<ProductCategory> ProductCategoryList;

	public ProductCategoryExecution() {

	}

	// 店铺操作失败的时候使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum) {
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
	}


	// 店铺操作成功的时候使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum, List<ProductCategory> ProductCategoryList) {
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
		this.ProductCategoryList = ProductCategoryList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public List<ProductCategory> getProductCategoryList() {
		return ProductCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		ProductCategoryList = productCategoryList;
	}
}
