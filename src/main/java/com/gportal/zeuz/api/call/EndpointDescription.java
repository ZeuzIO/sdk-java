package com.gportal.zeuz.api.call;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EndpointDescription {
    String Description();
}
