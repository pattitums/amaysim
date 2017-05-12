package com.amaysim;

public class PricingRules {
	private Integer quantityPurchased;
	
	public Integer getQuantityPurchased() {
		return quantityPurchased;
	}

	public void setQuantityPurchased(Integer quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}

	public Double applyPricing(Item saleItem) {
		this.quantityPurchased = this.quantityPurchased == 0 ? 1: this.quantityPurchased;
		return saleItem.getUnitPrice()*this.quantityPurchased;
	}
	
	public Double applyThreeForTwoRule(Item saleItem) {
		Double salePrice = applyPricing(saleItem);
		if(this.quantityPurchased %3 == 0) {
			salePrice = (saleItem.getUnitPrice()/3*2)*this.quantityPurchased;
		}
		return salePrice;
	}
	
	public Double applyBulkDiscount(Item saleItem, Double bulkPrice) {
		if (this.quantityPurchased > 3) {
			return bulkPrice*this.quantityPurchased;
		}
		return applyPricing(saleItem);
	}
}
