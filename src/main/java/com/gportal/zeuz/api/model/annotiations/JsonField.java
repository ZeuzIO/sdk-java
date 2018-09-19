package com.gportal.zeuz.api.model.annotiations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonField {
    enum DisplayType {
        TEXT,
        NUMBER,
        BOOLEAN,
        LIST
    }

    String fieldName();
    DisplayType displayType();
}
