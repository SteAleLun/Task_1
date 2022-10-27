package com.example.task_1.model;

import com.example.task_1.config.EnumNamePattern;

@EnumNamePattern(regexp = "ACTIVE|INACTIVE")
public enum Status {
    ACTIVE,
    INACTIVE;

    Status(){}
}
