package com.gportal.zeuz.api.model.response;

import com.gportal.zeuz.api.call.exception.ResponseException;
import com.gportal.zeuz.api.entity.Service;
import com.gportal.zeuz.api.entity.ServiceStatus;
import org.json.JSONObject;

public class ServiceStatusResponse
        extends SimpleRestResponse
        implements RestResponseInterface {
    private String status;
    private ServiceStatus serviceStatus;

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ServiceStatusResponse() {

    }

    public ServiceStatusResponse(JSONObject jsonObject) throws ResponseException {
        super(jsonObject);
    }

    @Override
    public void _parseFromJSON(JSONObject jsonObject) {
        if(jsonObject.has("status")) {
            this.setStatus(jsonObject.getString("status"));
            this.setServiceStatus(ServiceStatus.findByName(this.getStatus()));
        }
    }

    @Override
    public SimpleRestResponse getSimpleRestResponse() {
        return null;
    }
}
