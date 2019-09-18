package com.validate.constant;

public enum ValidateType {
    PP("'fromDate' and 'toDate' can be in past"),
    PF("'fromDate' can be in past, toDate must be in future"),
    FF("'fromDate' and 'toDate' must be in future");

    private String name;

    ValidateType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
