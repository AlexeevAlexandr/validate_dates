package com.validate.entity;

import java.time.LocalDate;

public class Discount {

    private LocalDate fromDate;
    private LocalDate toDate;

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        if (fromDate.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("'fromDate' for Discount entity can't be in past");
        }
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
