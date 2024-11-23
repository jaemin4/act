package com.activity.pro.func.repository;

import com.activity.pro.entity.Activity;
import com.activity.pro.func.domain.request.GetActByCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long>, JpaSpecificationExecutor<Activity> {

}
