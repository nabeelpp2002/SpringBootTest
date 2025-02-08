package com.example.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/c6fa4e2576417c21b5a99c70/latest/";

    public Double getConversionRate(String base, String target) {
        try {
            
            String url = API_URL + base;

            
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            
            Map<String, Double> rates = (Map<String, Double>) response.get("conversion_rates");

            
            if (rates == null || !rates.containsKey(target)) {
                throw new IllegalArgumentException("Invalid target currency: " + target);
            }

            
            return rates.get(target);

        } catch (RestClientException e) {
            
            throw new RuntimeException("External API is unavailable. Please try again later.");
        }
    }
}
