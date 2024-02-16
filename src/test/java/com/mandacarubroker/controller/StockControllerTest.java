package com.mandacarubroker.controller;


import com.mandacarubroker.domain.stock.RequestStockDTO;
import com.mandacarubroker.domain.stock.Stock;
import com.mandacarubroker.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class StockControllerTest {

    private StockService stockService;
    private StockController stockController;

    @BeforeEach
    void setUp() {
        stockService = mock(StockService.class);
        stockController = new StockController(stockService);
    }

    @Test
    void testGetAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("1","aaa1", "Stock 1", 1000));
        stocks.add(new Stock("2","bbb2", "Stock 2", 2000));

        when(stockService.getAllStocks()).thenReturn(stocks);

        ResponseEntity<List<Stock>> response = stockController.getAllStocks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stocks, response.getBody());
    }

    @Test
    void testGetStockById() {
        Stock stock = new Stock("1","aaa1", "Stock 1", 1000);
        when(stockService.getStockById("1")).thenReturn(Optional.of(stock));

        ResponseEntity<Stock> response = stockController.getStockById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stock, response.getBody());
    }

    @Test
    void testCreateStock() {
        RequestStockDTO requestData = new RequestStockDTO("aaa1","Stock 1", 10);
        Stock createdStock = new Stock("1","aaa1", "Stock 1", 10);

        when(stockService.createStock(requestData)).thenReturn(createdStock);

        ResponseEntity<Stock> response = stockController.createStock(requestData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdStock, response.getBody());
    }

    @Test
    void testUpdateStock() {
        String id = "1";
        Stock updatedStock = new Stock("1","aaa1", "Apple", 20);

        when(stockService.updateStock(id, updatedStock)).thenReturn(Optional.of(updatedStock));

        ResponseEntity<Stock> response = stockController.updateStock(id, updatedStock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedStock, response.getBody());
    }

    @Test
    void testDeleteStock() {
        String id = "1";

        ResponseEntity<Void> response = stockController.deleteStock(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(stockService, times(1)).deleteStock(id);
    }
}
