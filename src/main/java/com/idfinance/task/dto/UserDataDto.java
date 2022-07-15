package com.idfinance.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class UserDataDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    private String username;
    @NotBlank
    @Pattern(regexp = "^[\\p{Upper}]{3}$")
    private String symbol;
}
