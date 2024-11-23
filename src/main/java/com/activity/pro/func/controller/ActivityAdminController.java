package com.activity.pro.func.controller;


import com.activity.pro.entity.*;
import com.activity.pro.func.domain.request.ActiAdmSaveReqDto;
import com.activity.pro.func.domain.sub.AvailableDates;
import com.activity.pro.func.service.ActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ActivityAdminController {

    private final ActivityService activityService;

    //관리자 액티비티 추가
    @PostMapping("/api/admin/activities")
    public ResponseEntity<Activity> createActivity(@RequestBody ActiAdmSaveReqDto actiAdmSaveReqDto) {
        System.out.println("Received DTO: " + actiAdmSaveReqDto);

        Activity activityEntity = Activity.activityFromSaveDto(actiAdmSaveReqDto);
        Activity resultActivity = activityService.createActivity(activityEntity);

        if(resultActivity != null){
            log.info("데이터 확인 : {}" , resultActivity.getActivityId().toString());
            SubCategory subCategoryEntity = SubCategory.subCategoryFromDto(actiAdmSaveReqDto);
            SubCategory resultSubCategory = activityService.createSubCategory(subCategoryEntity);

            if(resultSubCategory != null){
                log.info("subCategory 확인 : {}" , resultSubCategory.getSubCategoryId());
                for (AvailableDates availableDates : actiAdmSaveReqDto.getAvailableDates()) {
                    ReserveDate reserveDate = ReserveDate.ReserveDateFromSaveDto(resultActivity.getActivityId(),
                            LocalDate.parse(availableDates.getDate()).atStartOfDay()

                    );

                    ReserveDate resultReserveDate = activityService.createReserveDate(reserveDate);

                    if (resultReserveDate != null) {
                        for (String time : availableDates.getTimes()) {
                            // 시간 파싱
                            LocalTime localTime = LocalTime.parse(time); // String -> LocalTime
                            LocalDateTime reserveTimeDateTime = LocalDate.parse(availableDates.getDate())
                                    .atStartOfDay()
                                    .toLocalDate()
                                    .atTime(localTime);

                            // ReserveTime 생성
                            ReserveTime reserveTime = ReserveTime.ReserveTimeFromSaveDto(
                                    resultReserveDate.getReserveDateId(),
                                    reserveTimeDateTime
                            );

                            ReserveTime resultReserveTime = activityService.createReserveTime(reserveTime);

                            if (resultReserveTime != null) {
                                // 시간에 맞는 maxParticipants 처리
                                Integer maxParticipants = availableDates.getMaxParticipants();
                                ReserveParticipants reserveParticipants = ReserveParticipants.reserveParticipantsFromSaveDto(
                                        maxParticipants,
                                        resultReserveTime.getReserveTimeId()
                                );

                                ReserveParticipants resultReserveParticipants = activityService.createReserveParticipants(reserveParticipants);

                                if (resultReserveParticipants != null) {
                                    log.info("참가자 등록 성공: 시간={}, 참가자={}", time, maxParticipants);
                                } else {
                                    log.error("참가자 등록 실패: 시간={}", time);
                                }
                            } else {
                                log.error("ReserveTime 생성 실패: 시간={}", time);
                            }
                        }
                    }
                }

            }

        }

        return null;
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
