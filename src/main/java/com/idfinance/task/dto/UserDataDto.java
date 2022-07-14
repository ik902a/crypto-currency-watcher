package com.idfinance.task.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserDataDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    private String username;
    @NotBlank
    @Pattern(regexp = "^[(BTC)?(ETH)?(SOL)?]$")
    private String symbol;

}
