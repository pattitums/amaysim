package com.amaysim;

public class main {

	public static void main(String[] args) {
		//create items
		Item ult_small = new Item("ult_small", "Unlimited 1GB", 24.90);
		Item ult_medium = new Item("ult_medium", "Unlimited 2GB", 29.90);
		Item ult_large = new Item("ult_large", "Unlimited 5GB", 44.90);
		Item data_1gb = new Item("1gb", "1 GB Data-pack", 9.90);
		
		PricingRules pricingRules = new PricingRules();
		ShoppingCart cart = new ShoppingCart(pricingRules);
		
		/* Scenario 1
		 * Items:
		 * 3 x Unlimited 1 GB
		 * 1 x Unlimited 5 GB
		 * Expected Total:
		 * $94.70
		 * Expected Cart Items:
		 * 3 x Unlimited 1 GB
		 */
		System.out.println("SCENARIO 1:");
		cart.add(ult_small, 3);
		cart.add(ult_large);
		cart.checkout();
		System.out.println(cart.toString());
		System.out.println("======================");
		
		/* Scenario 2
		 * Items:
		 * 2 x Unlimited 1 GB
		 * 4 x Unlimited 5 GB
		 * Expected Total:
		 * $209.40
		 * Expected Cart Items:
		 * 2 x Unlimited 1 GB
		 * 4 x Unlimited 5 GB
		 */
		System.out.println("SCENARIO 2:");
		pricingRules = new PricingRules();
		cart = new ShoppingCart(pricingRules);
		cart.add(ult_small, 2);
		cart.add(ult_large, 4);
		cart.checkout();
		System.out.println(cart.toString());
		System.out.println("======================");
		
		/* Scenario 3
		 * Items:
		 * 1 x Unlimited 1 GB
		 * 2 x Unlimited 2 GB
		 * Expected Total:
		 * $84.70
		 * Expected Cart Items:
		 * 1 x Unlimited 1 GB
		 * 2 X Unlimited 2 GB
		 * 2 X 1 GB Data-pack
		 */
		System.out.println("SCENARIO 3:");
		pricingRules = new PricingRules();
		cart = new ShoppingCart(pricingRules);
		cart.add(ult_small, 1);
		cart.add(ult_medium, 2);
		cart.checkout();
		System.out.println(cart.toString());
		System.out.println("======================");
		
		/* Scenario 4
		 * Items:
		 * 1 x Unlimited 1 GB
		 * 1 x 1 GB Data-pack
		 * 'I<3AMAYSIM' Promo Applied
		 * Expected Total:
		 * $31.32
		 * Expected Cart Items:
		 * 1 x Unlimited 1 GB
		 * 1 X 1 GB Data-pack
		 */
		System.out.println("SCENARIO 4:");
		pricingRules = new PricingRules();
		cart = new ShoppingCart(pricingRules);
		cart.add(ult_small, 1);
		cart.add(data_1gb, 1, "I<3AMAYSIM");
		cart.checkout();
		System.out.println(cart.toString());
	}

}
