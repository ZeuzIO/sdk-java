package com.gportal.zeuz.api.model.response;

import com.gportal.zeuz.api.call.exception.ResponseException;
import com.gportal.zeuz.api.entity.Service;
import org.json.JSONObject;

public class ServiceInfoResponse
        extends SimpleRestResponse
        implements RestResponseInterface {
    private Service service;


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceInfoResponse() {

    }

    public ServiceInfoResponse(JSONObject jsonObject) throws ResponseException {
        super(jsonObject);
    }

    @Override
    public void _parseFromJSON(JSONObject jsonObject) {
        setService(Service.parseFromJSON(jsonObject));
    }

    @Override
    public SimpleRestResponse getSimpleRestResponse() {
        return null;
    }
}
