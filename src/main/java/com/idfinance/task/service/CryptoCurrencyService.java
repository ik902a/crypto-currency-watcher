package com.idfinance.task.service;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.dto.PriceDto;
import com.idfinance.task.entity.Currency;
import com.idfinance.task.entity.Price;
import com.idfinance.task.exception.CryptoCurrencyException;
import com.idfinance.task.repository.CurrencyRepository;
import com.idfinance.task.repository.PriceRepository;
import com.idfinance.task.service.mapper.CurrencyMapper;
import com.idfinance.task.service.mapper.PriceMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CryptoCurrencyService {
    private static final String RESOURCE_NOT_FOUND_BY_ID = "Resource not found by id";
    private final CurrencyRepository currencyRepository;
    private final PriceRepository priceRepository;
    private final CurrencyMapper currencyMapper;
    private final PriceMapper priceMapper;

    @Transactional
    public List<CurrencyDto> findAll() {
        log.info("Finding Currency in Service");
        List<Currency> currencyList = currencyRepository.findAll();
        List<CurrencyDto> currencyDtoList = currencyList.stream()
                .map(currency -> currencyMapper.toDto(currency))
                .collect(Collectors.toList());
        return currencyDtoList;
    }

    @Transactional
    public PriceDto findActualPrice(Long id) {
        log.info("Finding actual price for Currency id={}", id);
        Price price = priceRepository.findActualPrice(id).orElseThrow(
                () -> new CryptoCurrencyException(RESOURCE_NOT_FOUND_BY_ID));
        return priceMapper.toDto(price);
    }

    // Method for my testing
    @Transactional
    public List<PriceDto> findAllPrice() {
        log.info("Finding Prices in Service");
        List<Price> priceList = priceRepository.findAll();
        List<PriceDto> priceDtoList = priceList.stream()
                .map(price -> priceMapper.toDto(price))
                .collect(Collectors.toList());
        return priceDtoList;
    }
}
