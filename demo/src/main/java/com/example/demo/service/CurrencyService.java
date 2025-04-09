
package com.example.demo.service;

import com.example.demo.entity.Currency;
import com.example.demo.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getCurrencyById(Long id) {
        return currencyRepository.findById(id).orElse(null);
    }

    public Currency getCurrencyByCode(String code) {
        return currencyRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Currency not found with code: " + code));
    }

    public Currency addCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency updateCurrency(Long id, Currency currencyDetails) {
        Currency existingCurrency = currencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Currency not found with id: " + id));

        existingCurrency.setCode(currencyDetails.getCode());
        existingCurrency.setName(currencyDetails.getName());
        existingCurrency.setSymbol(currencyDetails.getSymbol());

        return currencyRepository.save(existingCurrency);
    }

    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }
}