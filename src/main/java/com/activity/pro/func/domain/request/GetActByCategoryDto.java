package com.activity.pro.func.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetActByCategoryDto {

    private String mainCategory;
    private List<String> subCategory;
    private Integer participants;
    private String date;

}
