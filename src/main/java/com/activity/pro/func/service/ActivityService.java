package com.activity.pro.func.service;

import com.activity.pro.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {

    Activity createActivity(Activity activity);

    Optional<Activity> getActivityById(Long activityId);

    List<Activity> getAllActivities();

    Activity updateActivity(Long activityId, Activity updatedActivity);

    void deleteActivity(Long activityId);

}