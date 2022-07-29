package com.idfinance.task.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfinance.task.entity.Currency;
import com.idfinance.task.entity.Price;
import com.idfinance.task.entity.PriceData;
import com.idfinance.task.repository.CurrencyRepository;
import com.idfinance.task.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UpdateService {
    private static final String TICKER_URL = "https://api.coinlore.net/api/ticker/?id=";
    private final CheckService checkService;
    private final CurrencyRepository currencyRepository;
    private final PriceRepository priceRepository;
    private final CloseableHttpClient httpClient;
    private final ObjectMapper mapper;

    @Scheduled(fixedDelay = 60000)
    private void getActualPrice() {
        List<Currency> currencyList = currencyRepository.findAll();
        for (Currency currency : currencyList) {
            HttpGet getMethod = new HttpGet(TICKER_URL + currency.getId().toString());
            List<PriceData> dataList = null;
            try {
                HttpResponse response = httpClient.execute(getMethod);
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    log.warn("Getting an actual price is unsuccessful");
                } else {
                    InputStream inputStream = entity.getContent();
                    String result = convertStreamToString(inputStream);
                    inputStream.close();
                    if (!result.isBlank()) {
                        dataList = Arrays.asList(mapper.readValue(result, PriceData[].class));
                    }
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            if (dataList != null) {
                dataList.forEach(this::createActualPrice);
            }
        }
    }

    @Transactional
    private void createActualPrice(PriceData data) {
        Price price = fillPrice(data);
        Price createdPrice = priceRepository.save(price);
        checkService.checkPercent(createdPrice);
    }

    private Price fillPrice(PriceData data) {
        Price price = new Price();
        Currency currency = new Currency();
        currency.setId(data.getId());
        price.setCurrency(currency);
        price.setPrice(data.getPriceUsd());
        price.setUpdated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return price;
    }

    private String convertStreamToString(InputStream input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder result = new StringBuilder();
        String line;
        try (input) {
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result.toString();
    }
}
