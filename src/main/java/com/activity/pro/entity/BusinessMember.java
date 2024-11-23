package com.activity.pro.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "business_member")
public class BusinessMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_member_id")
    private Long businessMemberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long businessNumber;

    @Column(nullable = false)
    private String address;

    @Column
    private LocalDateTime startDate;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Builder
    public BusinessMember(String name, Long businessNumber, String address, LocalDateTime startDate, String phoneNumber, String email) {
        this.name = name;
        this.businessNumber = businessNumber;
        this.address = address;
        this.startDate = startDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static BusinessMember of(String name, Long businessNumber, String address, LocalDateTime startDate, String phoneNumber, String email) {
        return BusinessMember.builder()
                .name(name)
                .businessNumber(businessNumber)
                .address(address)
                .startDate(startDate)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }

    public void businessUpdateBusinessMember(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}