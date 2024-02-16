package com.mandacarubroker.service;

import com.mandacarubroker.domain.stock.RequestStockDTO;
import com.mandacarubroker.domain.stock.Stock;
import com.mandacarubroker.domain.stock.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class StockServiceTest {
    private StockRepository stockRepository;
    private StockService stockService;

    @BeforeEach
    void setUp() {
        stockRepository = mock(StockRepository.class);
        stockService = new StockService(stockRepository);
    }

    @Test
    void testGetAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("1", "APP1", "Apple", 150.50));
        stocks.add(new Stock("2", "GOO2", "Google", 2500.0));

        when(stockRepository.findAll()).thenReturn(stocks);

        List<Stock> result = stockService.getAllStocks();

        assertEquals(stocks.size(), result.size());
        assertEquals(stocks.get(0), result.get(0));
        assertEquals(stocks.get(1), result.get(1));
    }

    @Test
    void testGetStockById() {
        Stock stock = new Stock("1", "APP1", "Apple", 150.50);

        when(stockRepository.findById("1")).thenReturn(Optional.of(stock));

        Optional<Stock> result = stockService.getStockById("1");

        assertTrue(result.isPresent());
        assertEquals(stock, result.get());
    }

    @Test
    void testCreateStock() {
        RequestStockDTO requestData = new RequestStockDTO("APP1", "Apple", 150.50);
        Stock createdStock = new Stock("1", "APP1", "Apple", 150.50);

        when(stockRepository.save(any())).thenReturn(createdStock);

        Stock result = stockService.createStock(requestData);

        assertEquals(createdStock, result);
    }

    @Test
    void testUpdateStock() {
        String id = "1";
        Stock updatedStock = new Stock("1", "APP1", "Apple", 160.0);

        when(stockRepository.findById(id)).thenReturn(Optional.of(new Stock("1", "APP1", "Apple", 150.50)));
        when(stockRepository.save(any())).thenReturn(updatedStock);

        Optional<Stock> result = stockService.updateStock(id, updatedStock);

        assertTrue(result.isPresent());
        assertEquals(updatedStock, result.get());
    }

    @Test
    void testDeleteStock() {
        String id = "1";

        assertDoesNotThrow(() -> stockService.deleteStock(id));

        verify(stockRepository, times(1)).deleteById(id);
    }
}
