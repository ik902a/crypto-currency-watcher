package com.idfinance.task.mapper;

import com.idfinance.task.dto.PriceDto;
import com.idfinance.task.entity.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceDto toDto(Price entity);
}
