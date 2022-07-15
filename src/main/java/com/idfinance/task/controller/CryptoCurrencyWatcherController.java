package com.idfinance.task.controller;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.dto.PriceDto;
import com.idfinance.task.dto.UserDataDto;
import com.idfinance.task.dto.UserDto;
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
        return service.findAll();
    }

    @GetMapping("/currencies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PriceDto getActualPrice(@Valid @Positive @PathVariable Long id) {
        log.info("Finding price for currencies id={}", id);
        return service.findActualPrice(id);
    }

    @PostMapping("/notify")
    @ResponseStatus(HttpStatus.OK)
    public UserDto notify(@Valid @RequestBody UserDataDto data) {
        log.info("Adding new user userData={}", data.toString());
        return service.create(data);
    }
}
