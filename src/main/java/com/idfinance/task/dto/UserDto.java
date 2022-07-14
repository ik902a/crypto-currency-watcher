package com.idfinance.task.dto;

import com.idfinance.task.entity.Price;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private Price price;
}
