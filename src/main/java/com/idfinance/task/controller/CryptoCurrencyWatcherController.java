package com.idfinance.task.controller;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.dto.PriceDto;
import com.idfinance.task.dto.UserDataDto;
import com.idfinance.task.dto.UserDto;
import com.idfinance.task.entity.Currency;
import com.idfinance.task.service.CryptoCurrencyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Validated
@RestController
public class CryptoCurrencyWatcherController {
    private CryptoCurrencyService service;

    @GetMapping("/currencies")
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyDto> getAllCurrencies() {
        log.info("Finding all currencies");
        List<CurrencyDto> response = service.findAll();
        return response;
    }

    @GetMapping("/currencies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PriceDto getAllCurrencies(@Valid @Positive(message = "Should be positive number") @PathVariable Long id) {
        log.info("Finding price for currencies id={}", id);
        PriceDto response = service.findActualPrice(id);
        return response;
    }

    // Method for my testing
    @GetMapping("/prices")
    @ResponseStatus(HttpStatus.OK)
    public List<PriceDto> getAllPrices() {
        log.info("Finding all prices");
        List<PriceDto> response = service.findAllPrice();
        return response;
    }

    @GetMapping("/currencies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto notify(@Valid @RequestBody UserDataDto data) {
        log.info("Finding price for currencies id={}", data.toString());
        UserDto response = service.create(data);
        return response;
    }
}
