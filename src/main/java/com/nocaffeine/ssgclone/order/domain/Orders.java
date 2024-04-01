package com.nocaffeine.ssgclone.order.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import com.nocaffeine.ssgclone.order.dto.UserOrderSaveDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Orders extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String uuid;

    @NotNull
    @Column(length = 255)
    private String region;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    @Column(length = 50)
    private String phoneNumber;

    @NotNull
    @Column(length = 100)
    private String email;

    @NotNull
    private int totalPrice;

//    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 50)
    private OrderStatus status;


    public enum OrderStatus {
        ORDERED, PREPARING, DELIVERING, DETERMINING, CANCEL
    }

    public static Orders toEntity(UserOrderSaveDto userOrderSaveDto) {
        return Orders.builder()
                .uuid(userOrderSaveDto.getUuid())
                .region(userOrderSaveDto.getRegion())
                .name(userOrderSaveDto.getName())
                .phoneNumber(userOrderSaveDto.getPhoneNumber())
                .email(userOrderSaveDto.getEmail())
                .totalPrice(userOrderSaveDto.getTotalPrice())
                .status(OrderStatus.ORDERED)
                .build();
    }

}
