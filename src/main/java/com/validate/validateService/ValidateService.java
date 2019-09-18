package com.validate.validateService;

import com.validate.constant.ValidateType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface ValidateService {

    boolean validate(LocalDate fromDate, LocalDate toDate, ValidateType validateType);
}
