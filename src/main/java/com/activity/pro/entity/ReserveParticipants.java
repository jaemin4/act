package com.activity.pro.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reserve_participants")
public class ReserveParticipants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_participants_id")
    private Long reserveParticipantsId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserve_time_id", nullable = false)
    private ReserveTime reserveTime;

    @Column(nullable = false)
    private Integer maxParticipants;

    @Builder
    public ReserveParticipants(ReserveTime reserveTime, Integer maxParticipants) {
        this.reserveTime = reserveTime;
        this.maxParticipants = maxParticipants;
    }

    public static ReserveParticipants of(ReserveTime reserveTime, Integer maxParticipants) {
        return ReserveParticipants.builder()
                .reserveTime(reserveTime)
                .maxParticipants(maxParticipants)
                .build();
    }

    public static ReserveParticipants reserveParticipantsFromSaveDto(Integer maxParticipants,Long reserveTimeId){
        ReserveTime reserveTime = new ReserveTime();
        reserveTime.setReserveTimeId(reserveTimeId);

        return ReserveParticipants.builder()
                .reserveTime(reserveTime)
                .maxParticipants(maxParticipants)
                .build();
    }


}

