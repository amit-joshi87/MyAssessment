package io.amitjoshi;

import java.util.List;

public class Order {
	private String orderId;
	private List<Product> products;
	private Integer actualPrice;
	private Integer discountPrice;
	private Integer totalDiscount;

	public Order(String orderId, List<Product> products) {
		super();
		this.orderId = orderId;
		this.products = products;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Integer actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Integer totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", products=" + products + ", actualPrice=" + actualPrice
				+ ", discountPrice=" + discountPrice + ", totalDiscount=" + totalDiscount + "]";
	}	
}
