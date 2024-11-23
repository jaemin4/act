package com.activity.pro.func.service;

import com.activity.pro.entity.Activity;
import com.activity.pro.func.domain.request.GetActByCategoryDto;
import com.activity.pro.func.domain.specification.ActivitySpecification;
import com.activity.pro.func.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public List<Activity> getActivityByCategory(GetActByCategoryDto param) {
        Specification<Activity> spec = (root, query, criteriaBuilder) -> null;
        log.warn(param.getMainCategory());
        if (param.getMainCategory() != null) {
            spec = spec.and(ActivitySpecification.equalMainCategory(param.getMainCategory()));
        }
        if (param.getSubCategory() != null) {
            spec = spec.and(ActivitySpecification.containSubCategory(param.getSubCategory()));
        }

        return activityRepository.findAll(spec);
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
