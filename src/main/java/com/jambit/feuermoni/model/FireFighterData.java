package com.jambit.feuermoni.model;


import java.time.Instant;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class FireFighterData {

    public enum Status {
        OK, NO_DATA
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    private String ffId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    // in UTC
    private Instant timestamp;

    @Embedded
    private VitalSigns vitalSigns;
}
