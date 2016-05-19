package com.sam.sample.stock;

public enum BuySellIndicator {
	BUY("Buy"), SELL("Sell");

	private final String buySellIndicator;

	private BuySellIndicator(final String buySellIndicator) {
		this.buySellIndicator = buySellIndicator;
	}

	public String getBuySellIndicator() {
		return this.buySellIndicator;
	}

}
