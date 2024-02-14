package com.mandacarubroker.controller;


import com.mandacarubroker.domain.stock.RequestStockDTO;
import com.mandacarubroker.domain.stock.Stock;
import com.mandacarubroker.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getAllStocks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable String id) {
        Stock stock = stockService.getStockById(id).orElse(null);
        if (stock != null) {
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody RequestStockDTO data) {
        Stock createdStock = stockService.createStock(data);
        return ResponseEntity.ok(createdStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable String id, @RequestBody Stock updatedStock) {
        Optional<Stock> updatedStockOptional = stockService.updateStock(id, updatedStock);

        return updatedStockOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable String id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

}
