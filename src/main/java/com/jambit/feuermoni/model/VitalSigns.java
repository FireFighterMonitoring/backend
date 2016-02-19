package com.jambit.feuermoni.model;


import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Embeddable
@Getter
@Setter
@ToString
public class VitalSigns {

    @Min(-1)
    private int heartRate;

    @Min(-1)
    private int stepCount;
}
