package com.idfinance.task.controller;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.entity.Currency;
import com.idfinance.task.service.CryptoCurrencyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class CryptoCurrencyWatcherController {

    private CryptoCurrencyService service;

    @GetMapping("/currencies")
    @ResponseStatus(HttpStatus.OK)
    public List<Currency> getAllCurrencies() {
        log.info("Finding all currencies");
//        List<CurrencyDto> response = service.find();
//        PageGiftCertificateResponse response = PageGiftCertificateResponse.valueOf(pageDto);

        List<Currency> response = service.find();

        return response;
    }
}
