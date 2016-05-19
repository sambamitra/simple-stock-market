package com.sam.sample.stock;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleStockMarketApplication implements CommandLineRunner {

	private static Logger LOGGER = LoggerFactory.getLogger(SimpleStockMarketApplication.class);

	@Autowired
	private StockService stockService;

	public static void main(String[] args) {
		SpringApplication.run(SimpleStockMarketApplication.class, args);
	}

	@Bean
	public Map<String, Stock> stocks() {
		Map<String, Stock> stocks = new HashMap<>();
		stocks.put("TEA", new Stock.Builder().symbol("TEA").type(StockType.COMMON).lastDividend(0).fixedDividend(0)
				.parValue(100).build());
		stocks.put("POP", new Stock.Builder().symbol("POP").type(StockType.COMMON).lastDividend(8).fixedDividend(0)
				.parValue(100).build());
		stocks.put("ALE", new Stock.Builder().symbol("ALE").type(StockType.COMMON).lastDividend(23).fixedDividend(0)
				.parValue(60).build());
		stocks.put("GIN", new Stock.Builder().symbol("GIN").type(StockType.PREFERRED).lastDividend(8).fixedDividend(2)
				.parValue(100).build());
		stocks.put("JOE", new Stock.Builder().symbol("JOE").type(StockType.COMMON).lastDividend(13).fixedDividend(0)
				.parValue(200).build());
		return stocks;
	}

	@Override
	public void run(String... args) throws Exception {
		Map<String, Stock> stocks = stocks();
		LOGGER.info("Stocks created are : {}", stocks.keySet());
	}

}
