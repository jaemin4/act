package com.activity.pro.func.domain.specification;


import com.activity.pro.entity.Activity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ActivitySpecification {

    public static Specification<Activity> equalMainCategory(String mainCategory){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("mainCategory"), mainCategory));
    }

    public static Specification<Activity> containSubCategory(List<String> subCategory) {
        return (root, query, criteriaBuilder) -> {
            if (subCategory == null || subCategory.isEmpty()) {
                return criteriaBuilder.conjunction(); // always true 조건
            }
            return root.get("subCategory").in(subCategory);
        };
    }

//    public static Specification<Activity> equalParticipants(int participant){
//        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("participant"), participant));
//    }
//
//    public static Specification<Activity> equalDate(String date){
//        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("date"), date));
//    }
}
