package com.idfinance.task.controller;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.dto.PriceDto;
import com.idfinance.task.dto.UserDataDto;
import com.idfinance.task.dto.UserDto;
import com.idfinance.task.service.CryptoCurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class CryptoCurrencyWatcherControllerTest {
    @InjectMocks
    private CryptoCurrencyWatcherController controller;
    @Mock
    private CryptoCurrencyService service;

    @Test
    void testGetAllCurrencies() {
        List<CurrencyDto> expected = Arrays.asList(
                new CurrencyDto(90L, "BTC"),
                new CurrencyDto(80L, "ETM"),
                new CurrencyDto(48543L,"SOL"));

        when(service.findAll()).thenReturn(expected);

        List<CurrencyDto> actual = controller.getAllCurrencies();

        verify(service).findAll();
        assertThat(actual.size()).isEqualTo(3);
        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    public void testGetActualPrice() {
        PriceDto expected = new PriceDto(2L, new BigDecimal(56.55), LocalDateTime.parse("2022-06-12T08:13:16"));

        when(service.findActualPrice(anyLong())).thenReturn(expected);

        PriceDto actual = controller.getActualPrice(90L);

        verify(service).findActualPrice(90L);
        assertThat(actual).isNotNull().isEqualTo(expected);
    }

    @Test
    public void testNotify() {
        UserDto expected = new UserDto(1L, "gena");

        when(service.create(any(UserDataDto.class))).thenReturn(expected);

        UserDataDto userDataDto = new UserDataDto("gena", "BTC");
        UserDto actual = controller.notify(userDataDto);

        verify(service).create(userDataDto);
        assertThat(actual).isNotNull().isEqualTo(expected);
    }
}
