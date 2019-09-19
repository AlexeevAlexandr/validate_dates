package com.validate;

import com.validate.constant.ValidateType;
import com.validate.entity.Contract;
import com.validate.entity.Discount;
import com.validate.validateService.ValidateService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Tests {

    @Test(expected = IllegalArgumentException.class)
    public void DiscountCanNotHaveFromDateInPast() {
        Discount discount = new Discount();
        LocalDate fromDate = LocalDate.of(2000,12,30);
        discount.setFromDate(fromDate);
    }

    @Test
    public void ContractCanHaveFromDateInPast() {
        Contract contract = new Contract();
        LocalDate fromDate = LocalDate.of(2000,12,30);
        contract.setFromDate(fromDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromDateCanNotBeMoreThanToDate() {
        Discount discount = new Discount();
        LocalDate fromDate = LocalDate.now().plusDays(7);
        LocalDate toDate = LocalDate.now();
        discount.setFromDate(fromDate);
        discount.setToDate(toDate);
        ValidateService.validate(discount.getFromDate(), discount.getToDate(), ValidateType.PP);
    }

    @Test
    public void fromDateIsLesThanToDate() {
        Discount discount = new Discount();
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.now().plusDays(7);
        discount.setFromDate(fromDate);
        discount.setToDate(toDate);
        Assert.assertTrue(ValidateService.validate(discount.getFromDate(), discount.getToDate(), ValidateType.PP));
    }

    @Test
    public void fromDateAndToDateCanBeInPast() {
        Contract contract = new Contract();
        LocalDate fromDate = LocalDate.of(2000,12,30);
        LocalDate toDate = LocalDate.of(2000,12,31);
        contract.setFromDate(fromDate);
        contract.setToDate(toDate);
        Assert.assertTrue(ValidateService.validate(contract.getFromDate(), contract.getToDate(), ValidateType.PP));
    }

    @Test
    public void fromDateAndToDateCanBeEquals() {
        Contract contract = new Contract();
        LocalDate fromDate = LocalDate.of(2000,12,30);
        LocalDate toDate = LocalDate.of(2000,12,30);
        contract.setFromDate(fromDate);
        contract.setToDate(toDate);
        Assert.assertTrue(ValidateService.validate(contract.getFromDate(), contract.getToDate(), ValidateType.PP));
    }

    @Test
    public void fromDateCanBeInPastToDateMustBeInFutureTrue(){
        Contract contract = new Contract();
        LocalDate fromDate = LocalDate.of(2000,12,30);
        LocalDate toDate = LocalDate.now().plusDays(7);
        contract.setFromDate(fromDate);
        contract.setToDate(toDate);
        Assert.assertTrue(ValidateService.validate(contract.getFromDate(), contract.getToDate(), ValidateType.PF));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromDateCanBeInPastToDateMustBeInFutureFalse(){
        Contract contract = new Contract();
        LocalDate fromDate = LocalDate.of(2000,12,30);
        LocalDate toDate = LocalDate.of(2000,12,31);
        contract.setFromDate(fromDate);
        contract.setToDate(toDate);
        ValidateService.validate(contract.getFromDate(), contract.getToDate(), ValidateType.PF);
    }

    @Test
    public void fromDateAndToDateMustBeInFutureTrue(){
        Contract contract = new Contract();
        LocalDate fromDate = LocalDate.now().plusDays(7);
        LocalDate toDate = LocalDate.now().plusDays(14);
        contract.setFromDate(fromDate);
        contract.setToDate(toDate);
        Assert.assertTrue(ValidateService.validate(contract.getFromDate(), contract.getToDate(), ValidateType.FF));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromDateAndToDateMustBeInFutureFalse(){
        Contract contract = new Contract();
        LocalDate fromDate = LocalDate.of(2000,12,30);
        LocalDate toDate = LocalDate.now().plusDays(14);
        contract.setFromDate(fromDate);
        contract.setToDate(toDate);
        Assert.assertFalse(ValidateService.validate(contract.getFromDate(), contract.getToDate(), ValidateType.FF));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromDateAndToDateCanNotBeEquals(){
        Contract contract = new Contract();
        LocalDate fromDate = LocalDate.now().plusDays(14);
        LocalDate toDate = LocalDate.now().plusDays(14);
        contract.setFromDate(fromDate);
        contract.setToDate(toDate);
        Assert.assertFalse(ValidateService.validate(contract.getFromDate(), contract.getToDate(), ValidateType.FF));
    }
}
