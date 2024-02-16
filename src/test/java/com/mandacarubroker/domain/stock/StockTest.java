package com.mandacarubroker.domain.stock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockTest {
    @Test
    void testConstructorAndGetters() {
        String id = "1";
        String symbol = "APP1";
        String companyName = "Apple";
        double price = 150.50;

        Stock stock = new Stock(id, symbol, companyName, price);

        assertEquals(id, stock.getId());
        assertEquals(symbol, stock.getSymbol());
        assertEquals(companyName, stock.getCompanyName());
        assertEquals(price, stock.getPrice());
    }
    @Test
    void testChangePriceWithIncrease() {
        double initialPrice = 100.0;
        double increaseAmount = 20.0;
        boolean increase = true;

        Stock stock = new Stock();
        stock.setPrice(initialPrice);
        double newPrice = stock.changePrice(increaseAmount, increase);

        assertEquals(initialPrice + increaseAmount, newPrice);
    }
    @Test
    void testChangePriceWithDecrease() {
        double initialPrice = 100.0;
        double decreaseAmount = 20.0;
        boolean increase = false;

        Stock stock = new Stock();
        stock.setPrice(initialPrice);
        double newPrice = stock.changePrice(decreaseAmount, increase);

        assertEquals(initialPrice - decreaseAmount, newPrice);
    }

}