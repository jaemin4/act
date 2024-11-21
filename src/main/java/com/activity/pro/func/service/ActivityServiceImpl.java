package com.activity.pro.func.service;

import com.activity.pro.entity.Activity;
import com.activity.pro.func.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    // CREATE
    @Override
    public Activity createActivity(Activity activity) {
        // 새로운 Activity 생성
        Activity newActivity = Activity.of(
                activity.getBusinessMember(),
                activity.getName(),
                activity.getActivityPhoto(),
                activity.getDescription(),
                activity.getState(),
                activity.getPrice(),
                activity.getMainCategory(),
                LocalDateTime.now(), // 생성 시간
                LocalDateTime.now(), // 업데이트 시간
                activity.getAddress(),
                activity.getLatitude(),
                activity.getLongitude()
        );

        // 저장 및 반환
        return activityRepository.save(newActivity);
    }

    // READ (단일 조회)
    @Override
    public Optional<Activity> getActivityById(Long activityId) {
        return activityRepository.findById(activityId);
    }

    // READ (전체 조회)
    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    // UPDATE
    @Override
    public Activity updateActivity(Long activityId, Activity updatedActivity) {
        return activityRepository.findById(activityId).map(activity -> {
            activity.AdminUpdateActivity(
                    updatedActivity.getName(),
                    updatedActivity.getActivityPhoto(),
                    updatedActivity.getDescription(),
                    updatedActivity.getState(),
                    updatedActivity.getPrice(),
                    updatedActivity.getMainCategory(),
                    updatedActivity.getAddress(),
                    updatedActivity.getLatitude(),
                    updatedActivity.getLongitude()
            );
            return activityRepository.save(activity);
        }).orElseThrow(() -> new IllegalArgumentException("Activity not found with id: " + activityId));
    }

    // DELETE
    @Override
    public void deleteActivity(Long activityId) {
        if (activityRepository.existsById(activityId)) {
            activityRepository.deleteById(activityId);
        } else {
            throw new IllegalArgumentException("Activity not found with id: " + activityId);
        }
    }
}
