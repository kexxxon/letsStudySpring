package com.cStates.coffee.mapper;

import com.cStates.coffee.dto.CoffeePatchDto;
import com.cStates.coffee.dto.CoffeePostDto;
import com.cStates.coffee.dto.CoffeeResponseDto;
import com.cStates.coffee.entity.Coffee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);
}
