package com.idfinance.task.service.mapper;

import com.idfinance.task.dto.CurrencyDto;
import com.idfinance.task.entity.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    //    @Mapping(target = "valueList", ignore = true)
    CurrencyDto toDto(Currency entity);
}
