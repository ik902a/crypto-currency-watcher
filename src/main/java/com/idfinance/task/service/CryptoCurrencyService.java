package com.idfinance.task.service;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.dto.PriceDto;
import com.idfinance.task.entity.Currency;
import com.idfinance.task.entity.Price;
import com.idfinance.task.mapper.CurrencyMapper;
import com.idfinance.task.mapper.PriceMapper;
import com.idfinance.task.repository.CurrencyRepository;
import com.idfinance.task.repository.PriceRepository;
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
    private final CurrencyRepository currencyRepository;
    private final PriceRepository priceRepository;
    private final CurrencyMapper currencyMapper;
    private final PriceMapper priceMapper;

    @Transactional
    public List<CurrencyDto> find() {
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
        Price price = priceRepository.findActualPrice(id);
        log.info("Actual price={}", id);
        return priceMapper.toDto(price);
    }

//    @Transactional(readOnly = true)
//    public RegistryFullModel get(UUID id) {
//        var domain = registryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//        return registryMapper.mapToFullModel(domain);
//    }
}
