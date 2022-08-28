package com.cStates.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CoffeePostDto {
    @NotBlank(message = "한글 커피명은 공백이 아니어야 합니다.")
    private String korName;

    @NotBlank(message = "영문 커피명은 공백이 아니어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z]+(?: [a-zA-Z]+)*$")
    private String engName;

    @Range(min = 100, max = 50000)
    private int price;

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }

    public int getPrice() {
        return price;
    }
}