package io.amitjoshi;

public enum SkuCode {
	SKU_CODE_A(50), SKU_CODE_B(30), SKU_CODE_C(20), SKU_CODE_D(15);

	private final int price;

	SkuCode(int price) {
		this.price = price;
	}

	public int getValue() {
		return price;
	}
}
