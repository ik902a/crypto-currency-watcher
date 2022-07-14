package com.idfinance.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class PriceDto {
    private Long id;
    private BigDecimal price;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//            , timezone = "UTC")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updated;
    private CurrencyDto currency;
}
