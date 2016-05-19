package com.sam.sample.stock;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class TradeBuilderTest {

	@Test
	public void testBuySellIndicatorPopulated() {
		final BuySellIndicator buySellIndicator = BuySellIndicator.BUY;
		final Trade trade = new Trade.Builder().buySellIndicator(buySellIndicator).build();
		assertEquals("Trade buy sell indicator not as expected", trade.getBuySellIndicator(), buySellIndicator);
	}

	@Test
	public void testNoOfSharesPopulated() {
		final int noOfShares = 20;
		final Trade trade = new Trade.Builder().noOfShares(noOfShares).build();
		assertEquals("Trade no of shares not as expected", trade.getNoOfShares(), noOfShares);
	}

	@Test
	public void testTimestampPopulated() {
		final Date timestamp = new Date();
		final Trade trade = new Trade.Builder().timestamp(timestamp).build();
		assertEquals("Trade timestamp not as expected", trade.getTimestamp(), timestamp);
	}

	@Test
	public void testTradedPricePopulated() {
		final double tradedPrice = 34.95;
		final Trade trade = new Trade.Builder().tradedPrice(tradedPrice).build();
		assertEquals("Traded price not as expected", trade.getTradedPrice(), tradedPrice, 0);
	}
}
