package io.amitjoshi;

import java.util.Map;

public class PromotionStrategy {
	private String promotionCode;
	private Map<SkuCode, Integer> products;
	private Integer price;

	public static PromotionStrategy createPromotionStrategy(String promotionCode, Map<SkuCode, Integer> products,
			Integer price) {
		return new PromotionStrategy(promotionCode, products, price);
	}

	public PromotionStrategy(String promotionCode, Map<SkuCode, Integer> products, Integer price) {
		super();
		this.promotionCode = promotionCode;
		this.products = products;
		this.price = price;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public Map<SkuCode, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<SkuCode, Integer> products) {
		this.products = products;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
