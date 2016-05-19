package com.sam.sample.stock;

public enum StockType {
	COMMON("Common"), PREFERRED("Preferred");

	private final String stockType;

	private StockType(final String stockType) {
		this.stockType = stockType;
	}

	public String getStockType() {
		return this.stockType;
	}

}
