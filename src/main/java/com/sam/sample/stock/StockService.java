package com.sam.sample.stock;

import java.util.List;

public interface StockService {
	double calculateDividend(final Stock stock, final double price);

	double calculateGBCEAllShareIndex(final List<Stock> stocks);

	double calculatePERatio(final double price, final double dividend);

	double calculateVolumeWeightedStockPrice(final Stock stock);

	void recordTrade(final Stock stock, final Trade trade);
}
