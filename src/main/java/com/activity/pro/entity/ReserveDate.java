package com.activity.pro.entity;

import com.activity.pro.func.domain.request.ActiAdmSaveReqDto;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reserve_date")
@Setter
public class ReserveDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_date_id")
    private Long reserveDateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @Column(nullable = false)
    private LocalDateTime reserveDate;

    @Builder
    public ReserveDate(Activity activity, LocalDateTime reserveDate) {
        this.activity = activity;
        this.reserveDate = reserveDate;
    }
    public static ReserveDate of(Activity activity, LocalDateTime reserveDate) {
        return ReserveDate.builder()
                .activity(activity)
                .reserveDate(reserveDate)
                .build();
    }

    public static ReserveDate ReserveDateFromSaveDto(Long actiId,LocalDateTime date){
        Activity acti = new Activity();
        acti.setActivityId(actiId);

        return ReserveDate
                .builder()
                        .reserveDate(date)
                        .activity(acti)
                .build();
    }


}