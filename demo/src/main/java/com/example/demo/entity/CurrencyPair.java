package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "currency_pairs")
public class CurrencyPair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "base_currency_name", nullable = false)
    private String baseCurrencyName;

    @Column(name = "quote_currency_name", nullable = false)
    private String quoteCurrencyName;

    @Column(name = "rate", nullable = false)
    private Double exchangeRate;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    public CurrencyPair() {}

    public CurrencyPair(String baseCurrencyName, String quoteCurrencyName, Double exchangeRate) {
        this.baseCurrencyName = baseCurrencyName;
        this.quoteCurrencyName = quoteCurrencyName;
        this.exchangeRate = exchangeRate;
        this.lastUpdated = new Date();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBaseCurrencyName() { return baseCurrencyName; }
    public void setBaseCurrencyName(String baseCurrencyName) { this.baseCurrencyName = baseCurrencyName; }

    public String getQuoteCurrencyName() { return quoteCurrencyName; }
    public void setQuoteCurrencyName(String quoteCurrencyName) { this.quoteCurrencyName = quoteCurrencyName; }

    public Double getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(Double exchangeRate) { this.exchangeRate = exchangeRate; }

    public Date getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }
}