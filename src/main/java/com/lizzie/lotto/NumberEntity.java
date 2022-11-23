package com.lizzie.lotto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberEntity {
    public NumberEntity(int times, int no1, int no2, int no3, int no4, int no5, int no6, int bonus) {
        this.times = times;
        this.no1 = no1;
        this.no2 = no2;
        this.no3 = no3;
        this.no4 = no4;
        this.no5 = no5;
        this.no6 = no6;
        this.bonus = bonus;
    }

    int times;
    int no1;
    int no2;
    int no3;
    int no4;
    int no5;
    int no6;
    int bonus;
}
