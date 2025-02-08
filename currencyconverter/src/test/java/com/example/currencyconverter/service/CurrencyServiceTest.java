package com.example.currencyconverter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyServiceTest {

    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetConversionRate_ValidCurrencies() {
        
        System.out.println("Executing testGetConversionRate_ValidCurrencies...");

        
        Map<String, Object> mockResponse = new HashMap<>();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85); 
        rates.put("GBP", 0.75);
        mockResponse.put("conversion_rates", rates);

        
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        
        Double rate = currencyService.getConversionRate("USD", "EUR");

        
        assertNotNull(rate, "Rate should not be null");
        assertEquals(0.85, rate, 0.01, "Expected rate doesn't match the mocked response");

        
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Map.class));
    }

    @Test
    void testGetConversionRate_InvalidTargetCurrency() {
        
        System.out.println("Executing testGetConversionRate_InvalidTargetCurrency...");

        
        Map<String, Object> mockResponse = new HashMap<>();
        Map<String, Double> rates = new HashMap<>();
        rates.put("GBP", 0.75); 
        mockResponse.put("conversion_rates", rates);

        
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                currencyService.getConversionRate("USD", "EUR"));

        
        assertEquals("Invalid target currency: EUR", exception.getMessage());

        
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Map.class));
    }

    @Test
    void testGetConversionRate_ApiUnavailable() {
        
        System.out.println("Executing testGetConversionRate_ApiUnavailable...");

        
        when(restTemplate.getForObject(anyString(), eq(Map.class)))
                .thenThrow(new RestClientException("API unavailable"));

        
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                currencyService.getConversionRate("USD", "EUR"));

        
        assertEquals("External API is unavailable. Please try again later.", exception.getMessage());

        
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Map.class));
    }
}
