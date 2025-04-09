package com.example.demo.service;

import com.example.demo.entity.CurrencyPair;
import com.example.demo.repository.CurrencyPairRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyPairService {

    private final CurrencyPairRepository currencyPairRepository;

    public CurrencyPairService(CurrencyPairRepository currencyPairRepository) {
        this.currencyPairRepository = currencyPairRepository;
    }

    public List<CurrencyPair> getAllCurrencyPairs() {
        return currencyPairRepository.findAll();
    }

    public CurrencyPair getCurrencyPairById(Long id) {
        return currencyPairRepository.findById(id).orElse(null);
    }

    public CurrencyPair saveCurrencyPair(CurrencyPair currencyPair) {
        return currencyPairRepository.save(currencyPair);
    }

    public void deleteCurrencyPair(Long id) {
        currencyPairRepository.deleteById(id);
    }
}