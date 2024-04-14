package com.nocaffeine.ssgclone.review.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.order.domain.OrderProduct;
import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Orders order;

    @NotNull
    private String memberUuid;

    @NotNull
    @Column(length = 500)
    private String content;

    @NotNull
    private int rate;

    @Builder
    public Review(Long id, Product product, Orders order, String memberUuid, String content, int rate) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.memberUuid = memberUuid;
        this.content = content;
        this.rate = rate;
    }
}
