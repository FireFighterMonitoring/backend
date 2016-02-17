package com.jambit.feuermoni.model;


import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Embeddable
@Getter
@Setter
@ToString
public class VitalSigns {

    private int heartRate;

    private int stepCount;
}
