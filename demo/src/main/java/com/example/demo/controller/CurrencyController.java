package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Currency;
import com.example.demo.service.CurrencyService;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
        Currency currency = currencyService.getCurrencyById(id);
        return currency != null ? ResponseEntity.ok(currency) : ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Currency> getCurrencyByCode(@PathVariable String code) {
        Currency currency = currencyService.getCurrencyByCode(code);
        return ResponseEntity.ok(currency);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Currency addCurrency(@RequestBody Currency currency) {
        return currencyService.addCurrency(currency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable Long id, @RequestBody Currency currencyDetails) {
        Currency updatedCurrency = currencyService.updateCurrency(id, currencyDetails);
        return ResponseEntity.ok(updatedCurrency);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCurrency(@PathVariable Long id) {
        currencyService.deleteCurrency(id);
    }
}
