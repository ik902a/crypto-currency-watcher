package com.idfinance.task.controller;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.dto.PriceDto;
import com.idfinance.task.entity.Currency;
import com.idfinance.task.service.CryptoCurrencyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class CryptoCurrencyWatcherController {

    private CryptoCurrencyService service;

    @GetMapping("/currencies")
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyDto> getAllCurrencies() {
        log.info("Finding all currencies");
        List<CurrencyDto> response = service.find();
        return response;
    }

    @GetMapping("/currencies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PriceDto getAllCurrencies(@Positive @PathVariable Long id) {
        log.info("Finding price for currencies id={}", id);
        PriceDto response = service.findActualPrice(id);
        return response;
    }
}
