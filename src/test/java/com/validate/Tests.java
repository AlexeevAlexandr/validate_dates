package com.validate;

import com.validate.constant.ValidateType;
import com.validate.entity.Discount;
import com.validate.validateService.ValidateService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Tests {

    @Autowired
    private ValidateService validateService;

    // try to set past fromDate to Discount
    @Test(expected = IllegalArgumentException.class)
    public void test1() {
        Discount discount = new Discount();
        LocalDate fromDate = LocalDate.of(2000,12,30);
        discount.setFromDate(fromDate);
    }

    // try to set value into 'fromDate' that is more than 'toDate'
    @Test(expected = IllegalArgumentException.class)
    public void test2() {
        Discount discount = new Discount();
        LocalDate fromDate = LocalDate.of(2000,12,31);
        LocalDate toDate = LocalDate.of(2000,12,30);
        validateService.validate(fromDate, toDate, ValidateType.PP);
    }


}
