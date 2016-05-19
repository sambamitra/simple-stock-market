package com.sam.sample.stock;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SimpleStockMarketApplication.class)
public class StockServiceTest {

	private Stock stock;

	@Autowired
	private StockService stockService;

	private Trade trade;

	@Test
	public void testCalculateDividendForCommonTypetock() {
		// Given
		setupStockData("TEA", StockType.COMMON, 0, 0, 100, new ArrayList<>());
		double price = 50.35;
		// When
		double dividend = this.stockService.calculateDividend(this.stock, price);
		// Then
		assertEquals("Dividend not as expected", 0, dividend, 0);
	}

	@Test
	public void testCalculateDividendForPreferredTypeStock() {
		// Given
		setupStockData("GIN", StockType.PREFERRED, 8, 2, 100, new ArrayList<>());
		double price = 24.75;
		// When
		double dividend = this.stockService.calculateDividend(this.stock, price);
		// Then
		assertEquals("Dividend not as expected", 8.08, dividend, 0.01);
	}

	@Test
	public void testCalculateGBCEAllShareIndex() {
		// Given
		Date tradeTime = new Date();
		List<Stock> stocks = new LinkedList<>();

		setupStockData("TEA", StockType.COMMON, 0, 0, 100, new ArrayList<>());
		// 1st trade happened 10 minutes ago
		setupTrade(new Date(tradeTime.getTime() - 10 * 60 * 1000l), 10, BuySellIndicator.SELL, 25);
		this.stockService.recordTrade(this.stock, this.trade);
		// 2nd trade happens now
		setupTrade(tradeTime, 5, BuySellIndicator.SELL, 15);
		this.stockService.recordTrade(this.stock, this.trade);
		stocks.add(this.stock);

		setupStockData("GIN", StockType.PREFERRED, 8, 2, 100, new ArrayList<>());
		// 1st trade happened 4 minutes ago
		setupTrade(new Date(tradeTime.getTime() - 4 * 60 * 1000l), 10, BuySellIndicator.BUY, 20);
		this.stockService.recordTrade(this.stock, this.trade);
		// 2nd trade happened 1 minute ago
		setupTrade(new Date(tradeTime.getTime() - 1 * 60 * 1000l), 50, BuySellIndicator.SELL, 30);
		this.stockService.recordTrade(this.stock, this.trade);
		// 3rd trade happens now
		setupTrade(tradeTime, 10, BuySellIndicator.SELL, 25);
		this.stockService.recordTrade(this.stock, this.trade);
		stocks.add(this.stock);

		// When
		double gbceAllShareIndex = this.stockService.calculateGBCEAllShareIndex(stocks);
		// Then
		assertEquals("There should be 2 stocks which have been traded", 2, stocks.size());
		assertEquals("There should be 2 recorded trades for the 1st stock (TEA)", 2, stocks.get(0).getTrades().size());
		assertEquals("There should be 3 recorded trades for the 2nd stock (GIN)", 3, stocks.get(1).getTrades().size());
		assertEquals("Stock price for the 1st stock (TEA) not as expected", 21.67,
				this.stockService.calculateVolumeWeightedStockPrice(stocks.get(0)), 0.01);
		assertEquals("Stock price for the 2nd stock (GIN) not as expected", 27.86,
				this.stockService.calculateVolumeWeightedStockPrice(stocks.get(1)), 0.01);
		assertEquals("GBCE All Share Index not as expected", 24.57, gbceAllShareIndex, 0.01);
	}

	@Test
	public void testCalculatePERatio() {
		// Given
		setupStockData("ALE", StockType.COMMON, 23, 0, 60, new ArrayList<>());
		double price = 16.75;
		// Given
		double dividend = this.stockService.calculateDividend(this.stock, price);
		double peRatio = this.stockService.calculatePERatio(price, dividend);
		// Then
		assertEquals("P/E ratio not as expected", 12.2, peRatio, 0.01);
	}

	@Test
	public void testCalculatePERatioFor0Dividend() {
		// Given
		setupStockData("ALE", StockType.COMMON, 0, 0, 100, new ArrayList<>());
		double price = 25;
		// When
		double dividend = this.stockService.calculateDividend(this.stock, price);
		double peRatio = this.stockService.calculatePERatio(price, dividend);
		// Then
		assertEquals("P/E ratio not as expected", 0, peRatio, 0);
	}

	@Test
	public void testCalculateVolumeWeightedStockPrice() {
		// Given
		Date tradeTime = new Date();
		setupStockData("TEA", StockType.COMMON, 0, 0, 100, new ArrayList<>());
		// 1st trade happened 10 minutes ago
		setupTrade(new Date(tradeTime.getTime() - 10 * 60 * 1000l), 10, BuySellIndicator.SELL, 25);
		this.stockService.recordTrade(this.stock, this.trade);
		// 2nd trade happens now
		setupTrade(tradeTime, 5, BuySellIndicator.SELL, 15);
		this.stockService.recordTrade(this.stock, this.trade);
		// When
		double volumeWeightedStockPrice = this.stockService.calculateVolumeWeightedStockPrice(this.stock);
		// Then
		assertEquals("There should be 2 recorded trades for the stock", 2, this.stock.getTrades().size());
		assertEquals("Volume weighted stock price not as expected", 21.67, volumeWeightedStockPrice, 0.01);
	}

	@Test
	public void testRecordTrade() {
		// Given
		setupStockData("ALE", StockType.COMMON, 0, 0, 100, new ArrayList<>());
		setupTrade(new Date(), 5, BuySellIndicator.SELL, 10);
		// When
		this.stockService.recordTrade(this.stock, this.trade);
		// Then
		assertEquals("One trade should be present", 1, this.stock.getTrades().size());
	}

	@Test
	public void volumeWeightedStockPriceShouldBeForTradesInLast15Minutes() {
		// Given
		Date tradeTime = new Date();
		setupStockData("TEA", StockType.COMMON, 0, 0, 100, new ArrayList<>());
		// 1st trade happened 20 minuted ago
		setupTrade(new Date(tradeTime.getTime() - 20 * 60 * 1000l), 15, BuySellIndicator.BUY, 30);
		this.stockService.recordTrade(this.stock, this.trade);
		// 2nd trade happened 12 minutes ago
		setupTrade(new Date(tradeTime.getTime() - 12 * 60 * 1000l), 10, BuySellIndicator.SELL, 25);
		this.stockService.recordTrade(this.stock, this.trade);
		// 3rd trade happens now
		setupTrade(tradeTime, 5, BuySellIndicator.SELL, 15);
		this.stockService.recordTrade(this.stock, this.trade);
		// When
		double volumeWeightedStockPrice = this.stockService.calculateVolumeWeightedStockPrice(this.stock);
		// Then
		assertEquals("There should be 3 recorded trades for the stock", 3, this.stock.getTrades().size());
		assertEquals("Volume weighted stock price should be based on trades in past 15 minutes", 21.67,
				volumeWeightedStockPrice, 0.01);
	}

	private void setupStockData(final String stockSymbol, final StockType type, final double lastDividend,
			final double fixedDividend, final int parValue, List<Trade> trades) {
		this.stock = new Stock.Builder().symbol(stockSymbol).type(type).lastDividend(lastDividend)
				.fixedDividend(fixedDividend).parValue(parValue).trades(trades).build();
	}

	private void setupTrade(final Date timestamp, final int noOfShares, final BuySellIndicator buySellIndicator,
			final double tradedPrice) {
		this.trade = new Trade.Builder().timestamp(timestamp).noOfShares(noOfShares).buySellIndicator(buySellIndicator)
				.tradedPrice(tradedPrice).build();
	}
}
