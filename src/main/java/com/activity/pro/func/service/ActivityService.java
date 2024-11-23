package com.activity.pro.func.service;

import com.activity.pro.entity.*;
import com.activity.pro.func.domain.request.GetActByCategoryDto;

import java.util.List;
import java.util.Optional;

public interface ActivityService {

    Activity createActivity(Activity activity);

    SubCategory createSubCategory(SubCategory subCategory);

    ReserveDate createReserveDate(ReserveDate reserveDate);

    ReserveTime createReserveTime(ReserveTime reserveTime);

    ReserveParticipants createReserveParticipants(ReserveParticipants reserveParticipants);

    Optional<Activity> getActivityById(Long activityId);


    List<Activity> getAllActivities();

    List<Activity> getActivityByCategory(GetActByCategoryDto param);

    Activity updateActivity(Long activityId, Activity updatedActivity);

    void deleteActivity(Long activityId);



}
