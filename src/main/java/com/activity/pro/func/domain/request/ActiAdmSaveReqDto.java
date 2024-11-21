package com.activity.pro.func.domain.request;

import com.activity.pro.func.domain.sub.Location;
import com.activity.pro.func.domain.sub.SubCategory;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActiAdmSaveReqDto {

    //액티비티 이름
    private String name;

    //액티비티 대표사진
    private List<String> activityPhotos;

    //액티비티 상태
    private String state;

    //액티비티 가격
    private Integer price;

    //메인 카테고리
    private String mainCategory;

    //서브 카테고리
    private SubCategory subCategory;

    //액티비티 위치
    private Location location;









}
