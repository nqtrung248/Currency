package com.example.demo.controller;

import com.example.demo.entity.CurrencyPair;
import com.example.demo.service.CurrencyPairService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api/currency-pairs")
public class CurrencyPairController {

    private final CurrencyPairService currencyPairService;

    public CurrencyPairController(CurrencyPairService currencyPairService) {
        this.currencyPairService = currencyPairService;
    }

    @GetMapping
    public ResponseEntity<List<CurrencyPair>> getAllCurrencyPairs() {
        List<CurrencyPair> currencyPairs = currencyPairService.getAllCurrencyPairs();
        return ResponseEntity.ok(currencyPairs);
    }

    @GetMapping("/{id}")   
    public ResponseEntity<CurrencyPair> getCurrencyPairById(@PathVariable Long id) {
        CurrencyPair currencyPair = currencyPairService.getCurrencyPairById(id);
        return currencyPair != null ? ResponseEntity.ok(currencyPair) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CurrencyPair> createCurrencyPair(@RequestBody CurrencyPair currencyPair) {
        currencyPair.setLastUpdated(new Date());
        CurrencyPair savedPair = currencyPairService.saveCurrencyPair(currencyPair);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPair);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyPair> updateCurrencyPair(
            @PathVariable Long id, @RequestBody CurrencyPair updatedCurrencyPair) {
        CurrencyPair existingPair = currencyPairService.getCurrencyPairById(id);
        if (existingPair == null) {
            return ResponseEntity.notFound().build();
        }

        existingPair.setBaseCurrencyName(updatedCurrencyPair.getBaseCurrencyName());
        existingPair.setQuoteCurrencyName(updatedCurrencyPair.getQuoteCurrencyName());
        existingPair.setExchangeRate(updatedCurrencyPair.getExchangeRate());
        existingPair.setLastUpdated(new Date());

        CurrencyPair savedPair = currencyPairService.saveCurrencyPair(existingPair);
        return ResponseEntity.ok(savedPair);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrencyPair(@PathVariable Long id) {
        CurrencyPair existingPair = currencyPairService.getCurrencyPairById(id);
        if (existingPair == null) {
            return ResponseEntity.notFound().build();
        }

        currencyPairService.deleteCurrencyPair(id);
        return ResponseEntity.noContent().build();
    }
}            