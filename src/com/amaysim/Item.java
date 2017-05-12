package com.amaysim;

public class Item {
	private Double unitPrice;
	private String productCode;
	private String productName;
	
	public Item(String productCode, String productName, Double unitPrice) {
		this.productCode = productCode;
		this.productName = productName;
		this.unitPrice = unitPrice;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getDescription() {
		return productCode;
	}
	public void setDescription(String description) {
		this.productCode = description;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Item)) {
			return false;
		}
		Item item = (Item) o;
		return item.productCode.equals(productCode);
	}
	@Override
  	public int hashCode() {
		int result = 34;
		result = 31 * result + productCode.hashCode();
		return result;
  	}
	@Override
	public String toString() {
		return ("Item:" + productName);
	}
}
