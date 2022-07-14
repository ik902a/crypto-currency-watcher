package com.idfinance.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceData {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("price_usd")
    private BigDecimal priceUsd;

}
