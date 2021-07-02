package io.amitjoshi;

import java.util.Arrays;
import java.util.HashMap;

public class PromotionEngineTest {
	public static void main(String[] args) {
		PromotionValidator promotionValidator =  new PromotionValidator();
		//	1.	Create Orders with Products.
		Order orderNo1 = new Order(
				"1", Arrays.asList(
						Product.createProduct(SkuCode.SKU_CODE_A,1),
						Product.createProduct(SkuCode.SKU_CODE_B,1),
						Product.createProduct(SkuCode.SKU_CODE_C,1)						
					)
				);
		
		Order orderNo2 = new Order(
				"2", Arrays.asList(
						Product.createProduct(SkuCode.SKU_CODE_A, 5),						
						Product.createProduct(SkuCode.SKU_CODE_B, 5),						
						Product.createProduct(SkuCode.SKU_CODE_C, 1)
					)
				);
		
		Order orderNo3 = new Order(
				"3", Arrays.asList(
						Product.createProduct(SkuCode.SKU_CODE_A, 3),
						Product.createProduct(SkuCode.SKU_CODE_B,5),
						Product.createProduct(SkuCode.SKU_CODE_C,1),
						Product.createProduct(SkuCode.SKU_CODE_D,1)
					)
				);
		
		//	2.	Create Promotion Strategy.
		PromotionStrategy promo1 = PromotionStrategy.createPromotionStrategy("p001", 
									new HashMap<SkuCode, Integer>() {
										{	put(SkuCode.SKU_CODE_A,3);	}
									}, 
									
									130);
		PromotionStrategy promo2 = PromotionStrategy.createPromotionStrategy("p002", 
									new HashMap<SkuCode, Integer>() {
										{	put(SkuCode.SKU_CODE_B,2);	}
									}, 
									
									45);
		PromotionStrategy promo3 = PromotionStrategy.createPromotionStrategy("p003", 
									new HashMap<SkuCode, Integer>() {
										{	put(SkuCode.SKU_CODE_C,1);	put(SkuCode.SKU_CODE_D,1);}
									}, 
									
									30);
		
		//	3.	Apply Promotion - Calculate original price & discount price.
		promotionValidator.validateOrder(orderNo1, Arrays.asList(promo1, promo2, promo3));
		System.out.println(orderNo1);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		promotionValidator.validateOrder(orderNo2, Arrays.asList(promo1, promo2, promo3));
		System.out.println(orderNo2);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		promotionValidator.validateOrder(orderNo3, Arrays.asList(promo1, promo2, promo3));
		System.out.println(orderNo3);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
