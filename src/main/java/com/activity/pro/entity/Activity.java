package com.activity.pro.entity;

import com.activity.pro.func.domain.request.ActiAdmSaveReqDto;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

//Activity
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long activityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_member_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private BusinessMember businessMember;

    @Column(nullable = false)
    private String name;

    @Column
    private String activityPhoto;

    @Column
    private String description;

    @Column
    private String state;

    @Column
    private Integer price;

    @Column
    private String mainCategory;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private String address;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Builder
    public Activity(BusinessMember businessMember, String name, String activityPhoto, String description, String state, Integer price, String mainCategory, LocalDateTime createdAt, LocalDateTime updatedAt, String address, Double latitude, Double longitude) {
        this.businessMember = businessMember;
        this.name = name;
        this.activityPhoto = activityPhoto;
        this.description = description;
        this.state = state;
        this.price = price;
        this.mainCategory = mainCategory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void AdminUpdateActivity(String name, String activityPhoto, String description, String state, Integer price, String mainCategory, String address, Double latitude, Double longitude) {
        this.name = name;
        this.activityPhoto = activityPhoto;
        this.description = description;
        this.state = state;
        this.price = price;
        this.mainCategory = mainCategory;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public static Activity activityFromSaveDto(ActiAdmSaveReqDto dto){
        BusinessMember businessMember = new BusinessMember();
        businessMember.setBusinessMemberId(Long.valueOf(1));

        return Activity.builder()
                .businessMember(businessMember)
                .name(dto.getName())
                .activityPhoto(String.join(",", dto.getActivityPhotos()))
                .description(dto.getDescription())
                .state(dto.getState())
                .price(dto.getPrice())
                .mainCategory(dto.getMainCategory())
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();

    }





}