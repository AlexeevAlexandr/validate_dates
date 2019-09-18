package com.validate.validateService;

import com.validate.constant.ValidateType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidateServiceImpl implements ValidateService{

    /**
     * @param validateType - used to indicate how to check, may have three degrees:
     *                   PP - 'fromDate' and 'toDate' can be in past;
     *                   PF - 'fromDate' can be in past, toDate must be in future;
     *                   FF - 'fromDate' and 'toDate' must be in future;
     * @return - true or throws exception if dates are not valid
     */
    @Override
    public boolean validate(LocalDate fromDate, LocalDate toDate, ValidateType validateType){
        if (fromDate.isAfter(toDate)){
            throw new IllegalArgumentException("'fromDate' can't be after 'toDate'");
        }

        switch (validateType){

            //   - обе даты могут быть в прошлом, в том числе быть одной и той же датой.
            //   Пример - поиск сущностей по дате. Можно искать в прошлом, в том числе по 1 дню.
            case PP: {
                return true;
            }

            // - fromDate может быть в прошлом, toDate должен быть в будующем, в том числе до указанной даты включительно.
            //   Пример - контракт подписан давно, но внесен в систему только сейчас и действует до указанной даты включительно.
            case PF: {
                if (toDate.isAfter(LocalDate.now())) {
                    throw new IllegalArgumentException("'toDate' can't be in past");
                } else {
                    return true;
                }
            }

            // - обе даты должны быть в будующем, НЕ могут обозначать один и тот же день.
            //  Пример - создание скидки.
            //  Нельзя создать скидку, применимую в прошлом и ее срок действия не может быть с сегодня до сегодня.
            case FF: {
                if (fromDate.isAfter(LocalDate.now())) {
                    throw new IllegalArgumentException("'fromDate' can't be in past");
                } else if (fromDate.equals(toDate)) {
                    throw new IllegalArgumentException("'fromDate' and 'toDate' can't be equals");
                }
                return true;
            }
        }
        return true;
    }
}