package com.activity.pro.func.domain.request;




import com.activity.pro.func.domain.sub.AvailableDates;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@NoArgsConstructor
@Getter
@ToString

public class ActiAdmSaveReqDto {

    // 액티비티 이름
    private String name;

    // 액티비티 대표사진
    private List<String> activityPhotos;

    // 내용
    private String description;

    // 액티비티 상태
    private String state;

    // 액티비티 가격
    private Integer price;

    // 메인 카테고리
    private String mainCategory;

    // 서브 카테고리 이름
    private List<String> subCategory;

    // 액티비티 위치
    private String address;

    // 위도
    private double latitude;

    // 경도
    private double longitude;

    // 사용 가능 날짜
    private List<AvailableDates> availableDates;

}
