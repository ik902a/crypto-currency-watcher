package com.idfinance.task.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceData {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("price_usd")
    private BigDecimal priceUsd;
}
