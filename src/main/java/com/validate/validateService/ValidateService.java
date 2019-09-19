package com.validate.validateService;

import com.validate.constant.ValidateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class ValidateService {

    /**
     * @param validateType - used to indicate how to check, may have three degrees:
     *                   PP - 'fromDate' and 'toDate' can be in past;
     *                   PF - 'fromDate' can be in past, toDate must be in future;
     *                   FF - 'fromDate' and 'toDate' must be in future;
     * @return - true or throws exception if dates are not valid
     */
    public static boolean validate(LocalDate fromDate, LocalDate toDate, ValidateType validateType){
        log.info(".....................Try validate .........................");
        if (fromDate.isAfter(toDate)){
            log.error("..........'fromDate' can't be after 'toDate'...........");
            throw new IllegalArgumentException("'fromDate' can't be after 'toDate'");
        }

        switch (validateType){

            //   - обе даты могут быть в прошлом, в том числе быть одной и той же датой.
            //   Пример - поиск сущностей по дате. Можно искать в прошлом, в том числе по 1 дню.
            case PP: {
                log.info(".....................Validation successful.........................");
                return true;
            }

            // - fromDate может быть в прошлом, toDate должен быть в будующем, в том числе до указанной даты включительно.
            //   Пример - контракт подписан давно, но внесен в систему только сейчас и действует до указанной даты включительно.
            case PF: {
                if (toDate.isBefore(LocalDate.now())) {
                    log.error("..........'toDate' can't be in past'...........");
                    throw new IllegalArgumentException("'toDate' can't be in past");
                } else {
                    log.info(".....................Validation successful.........................");
                    return true;
                }
            }

            // - обе даты должны быть в будующем, НЕ могут обозначать один и тот же день.
            //  Пример - создание скидки.
            //  Нельзя создать скидку, применимую в прошлом и ее срок действия не может быть с сегодня до сегодня.
            case FF: {
                if (fromDate.isBefore(LocalDate.now())) {
                    log.error("..........'fromDate' can't be in past...........");
                    throw new IllegalArgumentException("'fromDate' can't be in past");
                } else if (fromDate.equals(toDate)) {
                    log.error("..........'fromDate' and 'toDate' can't be equals...........");
                    throw new IllegalArgumentException("'fromDate' and 'toDate' can't be equals");
                }
                log.info(".....................Validation successful.........................");
                return true;
            }
        }
        return true;
    }
}