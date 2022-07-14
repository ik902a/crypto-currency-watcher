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
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CryptoCurrencyService {
    private static final String RESOURCE_NOT_FOUND_BY_ID = "Resource not found by id";
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

    public UserDto create(UserDataDto data) {
        log.info("Creating new user name={}", data.getUsername());
        User user = fillUser(data);
        User createdUser = userRepository.save(user);
        return userMapper.toDto(createdUser);
    }

    private User fillUser(UserDataDto data) {
        Currency currency = currencyRepository.findBySymbol(data.getSymbol());
        Price price = priceRepository.findByCurrency(currency);
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPrice(price);
        return user;
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
