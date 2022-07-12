package com.idfinance.task.service;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.entity.Currency;
import com.idfinance.task.mapper.CurrencyMapper;
import com.idfinance.task.repository.CryptoCurrencyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CryptoCurrencyService {
    private final CryptoCurrencyRepository repository;
    private final CurrencyMapper mapper;

    @Transactional
    public List<CurrencyDto> find() {
        log.info("Finding Currency in Service");
        List<Currency> currencyList = repository.findAll();
        List<CurrencyDto> currencyDtoList = currencyList.stream()
                .map(currency -> mapper.toDto(currency))
                .collect(Collectors.toList());
        return currencyDtoList;
    }

}
