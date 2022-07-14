package com.idfinance.task.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfinance.task.entity.Currency;
import com.idfinance.task.entity.Price;
import com.idfinance.task.entity.PriceData;
import com.idfinance.task.exception.CryptoCurrencyException;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UpdateService {
    private static final String TICKER_URL = "https://api.coinlore.net/api/ticker/?id=_";
    private static final String UNDERSCORE = "_";
    private static final String UNSUCCESSFUL_UPDATE = "Getting an actual price is unsuccessful";
    private final CurrencyRepository currencyRepository;
    private final PriceRepository priceRepository;
    private final CloseableHttpClient httpClient;
    private final ObjectMapper mapper;

    @Scheduled(fixedDelay = 60000)
    private void getActualPrice() {
        List<Currency> currencyList = currencyRepository.findAll();

        for (Currency curency : currencyList) {
            HttpGet getMethod = new HttpGet(TICKER_URL.replace(UNDERSCORE, curency.getId().toString()));
            List<PriceData> dataList = null;
        try {
            HttpResponse response = httpClient.execute(getMethod);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);
                System.out.println("RESPONSE: " + result);//TODO delete line
                instream.close();
                dataList = Arrays.asList(mapper.readValue(result, PriceData[].class));
                log.info("New Price update - {}", dataList.get(0)); //TODO delete line
            } else {
                log.warn(UNSUCCESSFUL_UPDATE);
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        dataList.stream()
                .forEach(data -> createActualPrice(data));
        }
    }

    private void createActualPrice(PriceData data) {
        Price price = new Price();
        Currency currency = new Currency();
        currency.setId(data.getId());
        price.setCurrency(currency);
        price.setPrice(data.getPriceUsd());
        price.setUpdated(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        log.info("New Date update - {}", price.getUpdated()); //TODO delete line
        priceRepository.save(price);
        log.info("Was added new price - {}", price.toString());
    }

    private String convertStreamToString(InputStream input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder result = new StringBuilder();
        String line = null;
        try (input) {
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
//        } finally {//TODO delete line
//            try {
//                input.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return result.toString();
    }
}
