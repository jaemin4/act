package com.activity.pro.func.controller;


import com.activity.pro.entity.Activity;
import com.activity.pro.func.domain.request.GetActByCategoryDto;
import com.activity.pro.func.repository.ActivityRepository;
import com.activity.pro.func.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityAllController {

    private final ActivityService activityService;

    //액티비티 전체 조회
    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }


    //액티비티 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable Long id) {
        return activityService.getActivityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //액티비티 카테고리 조회
    @GetMapping("/category")
    public ResponseEntity<List<Activity>> getActivityByCategory(GetActByCategoryDto param){
        return ResponseEntity.ok(activityService.getActivityByCategory(param));
    }




}
