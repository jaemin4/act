package com.activity.pro.entity;

import com.activity.pro.func.domain.request.ActiAdmSaveReqDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sub_category")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Long subCategoryId;

    @Column(nullable = false)
    private String name;

    @Builder
    public SubCategory(String name) {
        this.name = name;
    }

    public static SubCategory subCategoryFromDto(ActiAdmSaveReqDto actiAdmSaveReqDto) {
        return SubCategory.builder()
                .name(String.join(",", actiAdmSaveReqDto.getSubCategory()))
                .build();
    }

}
