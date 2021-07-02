package io.amitjoshi;

public class Product {
	private SkuCode skuCode;
	private Integer orderQty;

	public static Product createProduct(SkuCode skuCode, Integer totalOrderCount) {
		return new Product(skuCode, totalOrderCount);
	}

	public Product(SkuCode skuCode, Integer orderQty) {
		super();
		this.skuCode = skuCode;
		this.orderQty = orderQty;
	}

	public SkuCode getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(SkuCode skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	@Override
	public String toString() {
		return "Product [skuCode=" + skuCode + ", orderQty=" + orderQty + "]";
	}	

}
