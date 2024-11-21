package com.activity.pro.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_member_id", nullable = false)
    private GeneralMember generalMember;

    @Builder
    public Cart(GeneralMember generalMember) {
        this.generalMember = generalMember;
    }

    public static Cart of(GeneralMember generalMember) {
        return Cart.builder()
                .generalMember(generalMember)
                .build();
    }
}
