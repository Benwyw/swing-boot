package com.benwyw.model;

import lombok.Getter;

@Getter
public enum PanelEnum {
    LOGIN_PANEL("LoginPanel"),
    INFO_PANEL("InfoPanel");

    private final String name;

    PanelEnum(String name) {
        this.name = name;
    }
}
