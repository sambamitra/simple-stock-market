package com.sam.sample.stock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StockBuilderTest {

	@Test
	public void testFixedDividendPopulated() {
		final double fixedDividend = 2;
		final Stock stock = new Stock.Builder().fixedDividend(fixedDividend).build();
		assertEquals("Stock fixed dividebd not as expected", stock.getFixedDividend(), fixedDividend, 0);
	}

	@Test
	public void testLastDividendPopulated() {
		final double lastDividend = 23;
		final Stock stock = new Stock.Builder().lastDividend(lastDividend).build();
		assertEquals("Stock last dividend not as expected", stock.getLastDividend(), lastDividend, 0);
	}

	@Test
	public void testParValuePopulated() {
		final int parValue = 60;
		final Stock stock = new Stock.Builder().parValue(parValue).build();
		assertEquals("Stock par value not as expected", stock.getParValue(), parValue);
	}

	@Test
	public void testStockSymbolPopulated() {
		final String stockSymbol = "PEA";
		final Stock stock = new Stock.Builder().symbol(stockSymbol).build();
		assertEquals("Stock symbol not as expected", stock.getSymbol(), stockSymbol);
	}

	@Test
	public void testStockTypePopulated() {
		final StockType stockType = StockType.COMMON;
		final Stock stock = new Stock.Builder().type(stockType).build();
		assertEquals("Stock type not as expected", stock.getType(), stockType);
	}
}
