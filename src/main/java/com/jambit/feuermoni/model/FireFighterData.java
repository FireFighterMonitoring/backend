package com.jambit.feuermoni.model;


import java.time.Instant;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.jambit.feuermoni.validation.ValidFireFighterData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Getter
@Setter
@ToString
@ValidFireFighterData
public class FireFighterData {

    public enum Status {
        CONNECTED, OK, NO_DATA, DISCONNECTED
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank
    private String ffId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    // in UTC
    private Instant timestamp;

    @Embedded
    @Valid
    private VitalSigns vitalSigns;
}
