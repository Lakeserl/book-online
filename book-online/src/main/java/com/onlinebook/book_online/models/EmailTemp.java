package com.onlinebook.book_online.models;

import lombok.Getter;

@Getter
public enum EmailTemp {
    ACTIVATE_ACCOUNT("activate_account")
    ;


    private final String name;
    EmailTemp(String name) {
        this.name = name;
    }
}
