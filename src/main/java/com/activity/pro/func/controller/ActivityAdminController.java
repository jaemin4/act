package com.activity.pro.func.controller;


import com.activity.pro.entity.Activity;
import com.activity.pro.func.domain.request.ActiAdmSaveReqDto;
import com.activity.pro.func.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/activities")
@RequiredArgsConstructor
public class ActivityAdminController {

    private final ActivityService activityService;

    //관리자 액티비티 추가
    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody ActiAdmSaveReqDto actiAdmSaveReqDto) {
        Activity activity =  Activity.fromDto(actiAdmSaveReqDto);

        return ResponseEntity.ok(activityService.createActivity(activity));
    }

    //관리자 액티비티 수정
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(
            @PathVariable Long id, @RequestBody Activity updatedActivity) {
        return ResponseEntity.ok(activityService.updateActivity(id, updatedActivity));
    }

    //관리자 액티비티 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
