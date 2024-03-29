package com.imooc.o2o.dto;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.enums.ShopStateEnum;

import java.util.List;

public class ProductExecution {

	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 店铺数量
	private int count;

	// 操作的shop（增删改店铺的时侯用到）
	private Product product;

	// shop列表（查询店铺列表的时候用到）
	private List<Product> productList;

	public ProductExecution() {

	}

	// 店铺操作失败的时候使用的构造器
	public ProductExecution(ProductStateEnum productStateEnum) {
		this.state = productStateEnum.getState();
		this.stateInfo = productStateEnum.getStateInfo();
	}

	// 店铺操作成功的时候使用的构造器
	public ProductExecution(ProductStateEnum productStateEnum, Product product) {
		this.state = productStateEnum.getState();
		this.stateInfo = productStateEnum.getStateInfo();
		this.product = product;
	}

	// 店铺操作成功的时候使用的构造器
	public ProductExecution(ProductStateEnum productStateEnum, List<Product> productList) {
		this.state = productStateEnum.getState();
		this.stateInfo = productStateEnum.getStateInfo();
		this.productList = productList;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}
