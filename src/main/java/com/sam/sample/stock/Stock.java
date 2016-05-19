package com.sam.sample.stock;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Stock {
	static class Builder {
		private double fixedDividend;
		private double lastDividend;
		private int parValue;
		private String symbol;
		private List<Trade> trades;
		private StockType type;

		Stock build() {
			return new Stock(this);
		}

		Builder fixedDividend(final double fixedDividend) {
			this.fixedDividend = fixedDividend;
			return this;
		}

		Builder lastDividend(final double lastDividend) {
			this.lastDividend = lastDividend;
			return this;
		}

		Builder parValue(final int parValue) {
			this.parValue = parValue;
			return this;
		}

		Builder symbol(final String symbol) {
			this.symbol = symbol;
			return this;
		}

		Builder trades(final List<Trade> trades) {
			this.trades = trades;
			return this;
		}

		Builder type(final StockType type) {
			this.type = type;
			return this;
		}
	}

	private double fixedDividend;
	private double lastDividend;
	private int parValue;
	private String symbol;
	private List<Trade> trades;

	private StockType type;

	private Stock() {

	}

	private Stock(final Builder builder) {
		this.symbol = builder.symbol;
		this.type = builder.type;
		this.lastDividend = builder.lastDividend;
		this.fixedDividend = builder.fixedDividend;
		this.parValue = builder.parValue;
		this.trades = builder.trades != null ? builder.trades : new LinkedList<>();
	}

	public double getFixedDividend() {
		return this.fixedDividend;
	}

	public double getLastDividend() {
		return this.lastDividend;
	}

	public int getParValue() {
		return this.parValue;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public List<Trade> getTrades() {
		return this.trades;
	}

	public StockType getType() {
		return this.type;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
