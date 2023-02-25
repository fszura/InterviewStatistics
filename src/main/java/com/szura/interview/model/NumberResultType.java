package com.szura.interview.model;

import lombok.Getter;

public enum NumberResultType {

    PHONE_NUMBER("^\\d{9}$"),
    ID("^[A-Za-z]{2}\\d{5}$"),
    PASSPORT("^[A-Z]{1}\\d{8}$");

    NumberResultType(String regex) {
        this.regex = regex;
    }

    @Getter
    private String regex;


}
