package com.nocaffeine.ssgclone.specialprice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
public class SpecialPrice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Timestamp startAt;

    @NotNull
    private Timestamp endAt;
}
