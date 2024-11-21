package com.activity.pro.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

// ActivitySubCategory Entity
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "activity_sub_category")
public class ActivitySubCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_sub_category_id")
    private Long activitySubCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @Builder
    public ActivitySubCategories(Activity activity, SubCategory subCategory) {
        this.activity = activity;
        this.subCategory = subCategory;
    }

    public static ActivitySubCategories of(Activity activity, SubCategory subCategory) {
        return ActivitySubCategories.builder()
                .activity(activity)
                .subCategory(subCategory)
                .build();
    }
}
