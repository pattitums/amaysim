package com.amaysim;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class ShoppingCart {
	private PricingRules pricingRules;
	private String promoCode = null;
	private Map<Item,Integer> items = new HashMap<Item,Integer>();
	private Map<Item,Integer> checkOutItems = new HashMap<Item,Integer>();

	public Double total = 0.0; //without promos or deals
	
	public ShoppingCart(PricingRules pricingRules) {
		this.pricingRules = pricingRules;
	}
	
	public void add(Item item, int quantity, String promoCode){
		if (promoCode != null) {
			this.promoCode = promoCode;
		}
		int totalQty = quantity == 0 ? 1: quantity;
		if (items.containsKey(item)){
			items.put(item, items.get(item)+totalQty);
		}
		else {
			items.put(item, totalQty);
		}
		total += computeTotalCost(item, totalQty);
	}
	
	private Double computeTotalCost(Item item, Integer quantity) {
		pricingRules.setQuantityPurchased(quantity);
		return pricingRules.applyPricing(item);
	}
	
	public void add(Item item) {
		add(item, 1, null);
	}
	
	public void add(Item item, int quantity) {
		add(item, quantity, null);
	}
	
	public void checkout(){
		Double runningTotal = 0.0;
		checkOutItems.putAll(items);
		for (Item item : items.keySet()) {
			runningTotal += computeTotalNetCost(item);
		}
		
		//promo handling
		if (promoCode != null) {
			Integer discount = Integer.valueOf(Util.fetchPromoCode(promoCode));
			if (discount!=null) {
				runningTotal=runningTotal*(100-discount)/100; 
			}
		}
		total = runningTotal;
	}
	
	private Double computeTotalNetCost(Item item) {
		Double totalCost = 0.0;
		Integer totalQty = items.get(item);
		pricingRules.setQuantityPurchased(totalQty);
		
		System.out.println("Item:"+item.getProductCode()+" Qty:"+totalQty);
		
		String deal = Util.fetchDeals(item.getProductCode());
		if (deal!=null) {
			//parse deals
			String[] dealDetails = deal.split(",");
			if (dealDetails.length > 0) {
				//Bulk pricing
				if (dealDetails[0].equals("BULK")) {
					totalCost=pricingRules.applyBulkDiscount(item, Double.valueOf(dealDetails[1]));
				}
				//Buy 1 take item free
				if (dealDetails[0].equals("FREE")) {
					totalCost=pricingRules.applyPricing(item);
					Item freeItem = new Item(dealDetails[1], dealDetails[2], Double.valueOf(dealDetails[3])); //free stuff
					checkOutItems.put(freeItem, totalQty);
				}
				//Buy 3 for 2
				if (dealDetails[0].equals("B342")) {
					totalCost=pricingRules.applyThreeForTwoRule(item);
				}
			}
		}
		else {
			totalCost=pricingRules.applyPricing(item);
		}
		return totalCost;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("####0.00");
		String out = "Total Cost: "+df.format(total)
				+"\nCart Items: \n";

		for (Map.Entry<Item, Integer> entry : checkOutItems.entrySet()) {
		    Item key = entry.getKey();
		    Integer value = entry.getValue();
		    out+=key+" Qty:"+value+"\n";
		}
		return out;
	}
}
