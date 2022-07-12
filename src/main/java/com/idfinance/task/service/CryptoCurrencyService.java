package com.idfinance.task.service;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.entity.Currency;
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
    private CryptoCurrencyRepository repository;
//    private ModelMapper modelMapper;

    @Transactional
    public List<Currency> find() {
        log.info("Finding Currency in Service");
        List<Currency> currencyList = repository.findAll();
//        List<CurrencyDto> currencyDtoList = currencyList.stream()
//                .map(giftCertificate -> modelMapper.map(giftCertificate, CurrencyDto.class))
//                .collect(Collectors.toList());
        return currencyList;
    }

}
