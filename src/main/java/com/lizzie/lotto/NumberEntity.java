package com.lizzie.lotto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberEntity {
    public NumberEntity(String times, String no1, String no2, String no3, String no4, String no5, String no6, String bonus) {
        this.times = times;
        this.no1 = no1;
        this.no2 = no2;
        this.no3 = no3;
        this.no4 = no4;
        this.no5 = no5;
        this.no6 = no6;
        this.bonus = bonus;
    }

    String times;
    String no1;
    String no2;
    String no3;
    String no4;
    String no5;
    String no6;
    String bonus;
}
