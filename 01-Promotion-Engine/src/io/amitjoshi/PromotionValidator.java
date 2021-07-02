package io.amitjoshi;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class PromotionValidator {

	public  void validateOrder(Order order, List<PromotionStrategy> promotions) {
		
		//	1.	Calculate total price
		int actualPrice = order.getProducts()
								.parallelStream()
								.mapToInt(o -> o.getSkuCode().getValue() * o.getOrderQty())
								.sum();
		
		//	2.	Calculate price after applying promotions.
		int discountPrice =  promotions.parallelStream()
									.mapToInt(p -> getDiscountPrice(order, p))
									.sum();
		order.setActualPrice(actualPrice);
		order.setDiscountPrice(discountPrice);
		order.setTotalDiscount(actualPrice - discountPrice);
	}
	
	public  Integer getDiscountPrice(Order order, PromotionStrategy promotionStrategy) {
		int discountPrice = 0;
		
		//	1.	Get the products required for applying promotion.
		List<SkuCode> listOfPromoSkuCodes = promotionStrategy
											.getProducts()
											.entrySet()
											.parallelStream()
											.map(e -> e.getKey())
											.collect(Collectors.toList());
		
		//	2.	Get the Products in Order specific to promotion.
		List<Product> productsEligibleForPromotion = order.getProducts()
														.parallelStream()
														.filter(p -> listOfPromoSkuCodes.contains(p.getSkuCode()))
														.collect(Collectors.toList());
			
		//	3.	Validate if Promotion Strategy is for single or multiple Products.
		boolean isPromotionForMultiple = listOfPromoSkuCodes.size() > 1 ? true : false;
		
		if(isPromotionForMultiple) {
			discountPrice = calculateDiscountPrice(promotionStrategy, listOfPromoSkuCodes, productsEligibleForPromotion);
		}else {
			SkuCode promotionSkuCode = listOfPromoSkuCodes.get(0);
			Product productEligibleForPromotion = productsEligibleForPromotion.get(0);
			discountPrice = calculateDiscountPrice(promotionStrategy, promotionSkuCode, productEligibleForPromotion);			
		}
		
		System.out.println("For promotionStrategy: "+ promotionStrategy.getProducts() +", productsEligibleForPromotion: "+productsEligibleForPromotion+" , Returing discountPrice: "+discountPrice);
		return discountPrice;
	}
	
	private int calculateDiscountPrice(PromotionStrategy promotionStrategy, SkuCode promotionSkuCode, Product productEligibleForPromotion) {
		int discountPrice = 0;
		int mod = productEligibleForPromotion.getOrderQty() / promotionStrategy.getProducts().get(promotionSkuCode);
		if(mod == 0) {
			discountPrice = productEligibleForPromotion.getOrderQty() * promotionSkuCode.getValue();
		}else {
			discountPrice = (mod * promotionStrategy.getPrice()) + ( (productEligibleForPromotion.getOrderQty() - (mod*promotionStrategy.getProducts().get(promotionSkuCode))) * promotionSkuCode.getValue());
		}
		
		return discountPrice;
	}
	
	private int calculateDiscountPrice(PromotionStrategy promotionStrategy, List<SkuCode> listOfPromoSkuCodes, List<Product> productsEligibleForPromotion) {
		int discountPrice = 0;
	
		List<Product> validApps = productsEligibleForPromotion
								.parallelStream()
								.filter(pr -> pr.getOrderQty() >= promotionStrategy.getProducts().get(pr.getSkuCode()))
								.collect(Collectors.toList());

		boolean isAllowed = validApps.size() == promotionStrategy.getProducts().size();
		if(isAllowed) {
			System.out.println("This block of code needs to be enhanced !!");
			discountPrice = promotionStrategy.getPrice();
		}else {			
			discountPrice =	productsEligibleForPromotion
								.parallelStream()
								.mapToInt(p -> p.getSkuCode().getValue() * p.getOrderQty())
								.sum();
		}		
		
		return discountPrice;
	}
}
