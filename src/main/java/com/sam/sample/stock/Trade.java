package com.sam.sample.stock;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Trade {

	static class Builder {
		private BuySellIndicator buySellIndicator;
		private int noOfShares;
		private Date timestamp;
		private double tradedPrice;

		Trade build() {
			return new Trade(this);
		}

		Builder buySellIndicator(final BuySellIndicator buySellIndicator) {
			this.buySellIndicator = buySellIndicator;
			return this;
		}

		Builder noOfShares(final int noOfShares) {
			this.noOfShares = noOfShares;
			return this;
		}

		Builder timestamp(final Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		Builder tradedPrice(final double tradedPrice) {
			this.tradedPrice = tradedPrice;
			return this;
		}
	}

	private BuySellIndicator buySellIndicator;
	private int noOfShares;
	private Date timestamp;

	private double tradedPrice;

	private Trade() {

	}

	private Trade(final Builder builder) {
		this.timestamp = builder.timestamp;
		this.noOfShares = builder.noOfShares;
		this.buySellIndicator = builder.buySellIndicator;
		this.tradedPrice = builder.tradedPrice;
	}

	public BuySellIndicator getBuySellIndicator() {
		return this.buySellIndicator;
	}

	public int getNoOfShares() {
		return this.noOfShares;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public double getTradedPrice() {
		return this.tradedPrice;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
