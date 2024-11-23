package com.activity.pro.func.service;

import com.activity.pro.entity.*;
import com.activity.pro.func.domain.request.GetActByCategoryDto;
import com.activity.pro.func.domain.specification.ActivitySpecification;
import com.activity.pro.func.repository.*;
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
    private final SubCategoryRepository subCategoryRepository;
    private final ReserveDateRepository reserveDateRepository;
    private final ReserveTimeRepository reserveTimeRepository;
    private final ReserveParticipantsRepository reserveParticipantsRepository;


    @Override
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public SubCategory createSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public ReserveDate createReserveDate(ReserveDate reserveDate) {
        return reserveDateRepository.save(reserveDate);
    }

    @Override
    public ReserveTime createReserveTime(ReserveTime reserveTime) {
        return reserveTimeRepository.save(reserveTime);
    }

    @Override
    public ReserveParticipants createReserveParticipants(ReserveParticipants reserveParticipants) {
        return reserveParticipantsRepository.save(reserveParticipants);
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

    // DELETE1
    @Override
    public void deleteActivity(Long activityId) {
        if (activityRepository.existsById(activityId)) {
            activityRepository.deleteById(activityId);
        } else {
            throw new IllegalArgumentException("Activity not found with id: " + activityId);
        }
    }



}
