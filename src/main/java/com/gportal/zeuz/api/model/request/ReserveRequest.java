package com.gportal.zeuz.api.model.request;

import com.gportal.zeuz.api.model.annotiations.JsonField;

public class ReserveRequest
        extends SimpleRestRequest
            implements RestRequestInterface {
    @JsonField(fieldName = "serviceId", displayType = JsonField.DisplayType.TEXT)
    private String serviceId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    protected String fetchFields() {
        return super.fetchFields();
    }

    @Override
    public String getJsonRequest() {
        return super.fetchFields();
    }
}
