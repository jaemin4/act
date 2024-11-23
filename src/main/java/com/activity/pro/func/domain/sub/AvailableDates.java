package com.activity.pro.func.domain.sub;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvailableDates {

    private String date;

    private List<String> times;

    private Integer maxParticipants;




}
