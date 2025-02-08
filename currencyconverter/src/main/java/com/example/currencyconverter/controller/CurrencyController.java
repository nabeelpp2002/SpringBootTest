package com.example.currencyconverter.controller;

import com.example.currencyconverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/convert")
    public Map<String, Object> convertCurrency(
            @RequestParam String base,
            @RequestParam String target,
            @RequestParam BigDecimal amount) {

        
        Double rate = currencyService.getConversionRate(base, target);

        
        BigDecimal convertedAmount = amount.multiply(BigDecimal.valueOf(rate));

        
        return Map.of(
                "base", base,
                "target", target,
                "amount", amount,
                "rate", rate,
                "convertedAmount", convertedAmount
        );
    }
}
