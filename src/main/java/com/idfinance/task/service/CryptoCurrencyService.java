package com.idfinance.task.service;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.dto.PriceDto;
import com.idfinance.task.dto.UserDataDto;
import com.idfinance.task.dto.UserDto;
import com.idfinance.task.entity.Currency;
import com.idfinance.task.entity.Price;
import com.idfinance.task.entity.User;
import com.idfinance.task.exception.CryptoCurrencyException;
import com.idfinance.task.repository.CurrencyRepository;
import com.idfinance.task.repository.PriceRepository;
import com.idfinance.task.repository.UserRepository;
import com.idfinance.task.service.mapper.CurrencyMapper;
import com.idfinance.task.service.mapper.PriceMapper;
import com.idfinance.task.service.mapper.UserMapper;
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
    // Method for my testing
//    @Transactional
//    public List<PriceDto> findAllPrice() {
//        log.info("Finding Prices in Service");
//        List<Price> priceList = priceRepository.findAll();
//        return priceList.stream()
//                .map(priceMapper::toDto)
//                .collect(Collectors.toList());
//    }
    private static final String RESOURCE_NOT_FOUND_BY_ID = "Resource not found by id";
    private static final String RESOURCE_NOT_FOUND_BY_SYMBOL = "Resource not found by symbol";
    private static final String RESOURCE_NOT_FOUND_BY_CURRENCY = "Resource not found by currency";
    private final CurrencyRepository currencyRepository;
    private final PriceRepository priceRepository;
    private final UserRepository userRepository;
    private final CurrencyMapper currencyMapper;
    private final PriceMapper priceMapper;
    private final UserMapper userMapper;

    @Transactional
    public List<CurrencyDto> findAll() {
        log.info("Finding Currency in Service");
        List<Currency> currencyList = currencyRepository.findAll();
        return currencyList.stream()
                .map(currencyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PriceDto findActualPrice(Long id) {
        log.info("Finding actual Price for Currency id={}", id);
        Price price = priceRepository.findActualPrice(id)
                .orElseThrow(() -> new CryptoCurrencyException(RESOURCE_NOT_FOUND_BY_ID));
        return priceMapper.toDto(price);
    }

    @Transactional
    public UserDto create(UserDataDto data) {
        log.info("Creating new User name={}", data.getUsername());
        User user = fillUser(data);
        User createdUser = userRepository.save(user);
        return userMapper.toDto(createdUser);
    }

    private User fillUser(UserDataDto data) {
        Currency currency = currencyRepository.findBySymbol(data.getSymbol())
                .orElseThrow(() -> new CryptoCurrencyException(RESOURCE_NOT_FOUND_BY_SYMBOL));
        Price price = priceRepository.findActualPrice(currency.getId())
                .orElseThrow(() -> new CryptoCurrencyException(RESOURCE_NOT_FOUND_BY_CURRENCY));
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPrice(price);
        return user;
    }
}
