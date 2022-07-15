package com.idfinance.task.service.mapper;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.entity.Currency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    CurrencyDto toDto(Currency entity);
}
