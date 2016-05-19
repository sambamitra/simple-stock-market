package com.sam.sample.stock;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

	// Traded time in milliseconds
	private static final long TRADE_TIME = 15 * 60 * 1000l;

	/**
	 * Calculate the dividend yield for a stock
	 */
	@Override
	public double calculateDividend(final Stock stock, final double price) {
		double dividend = 0;
		final StockType stockType = stock.getType();

		switch (stockType) {
		case COMMON:
			dividend = stock.getLastDividend() / price;
			break;
		case PREFERRED:
			dividend = (stock.getFixedDividend() * stock.getParValue()) / price;
			break;
		default:
			break;
		}

		return dividend;
	}

	/**
	 * Calculate the GBCE All Share Index for all stocks
	 */
	@Override
	public double calculateGBCEAllShareIndex(List<Stock> stocks) {
		double gbceAllShareIndex = 0;
		double stockPrice = 1;
		for (Stock stock : stocks) {
			stockPrice *= calculateVolumeWeightedStockPrice(stock);
		}
		gbceAllShareIndex = Math.pow(stockPrice, 1.0 / stocks.size());
		return gbceAllShareIndex;
	}

	/**
	 * Calculate the P/E ratio for a stock
	 */
	@Override
	public double calculatePERatio(final double price, final double dividend) {
		double peRatio = 0;
		if (dividend != 0) {
			peRatio = price / dividend;
		}
		return peRatio;
	}

	/**
	 * Calculate the volume weighted stock price for a stock based on trades in
	 * the past 15 minutes
	 */
	@Override
	public double calculateVolumeWeightedStockPrice(final Stock stock) {
		List<Trade> trades = stock.getTrades();
		double volumeWeightedStockPrice = 0;
		double tradedPriceTotal = 0;
		double quantityTotal = 0;
		Date now = new Date();
		for (Trade trade : trades) {
			// Only calculate the volume weighted stock price if it has been
			// traded within the last 15 minutes
			if ((now.getTime() - trade.getTimestamp().getTime()) <= TRADE_TIME) {
				tradedPriceTotal += (trade.getTradedPrice() * trade.getNoOfShares());
				quantityTotal += trade.getNoOfShares();
			}
		}
		if (quantityTotal != 0) {
			volumeWeightedStockPrice = tradedPriceTotal / quantityTotal;
		}
		return volumeWeightedStockPrice;
	}

	@Override
	public void recordTrade(final Stock stock, final Trade trade) {
		stock.getTrades().add(trade);
	}

}
