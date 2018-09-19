package com.gportal.zeuz.api.model.response;

import com.gportal.zeuz.api.call.exception.ResponseException;
import com.gportal.zeuz.api.entity.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProvideResponse
        extends SimpleRestResponse
            implements RestResponseInterface {

    private SimpleRestResponse simpleRestResponse;
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public SimpleRestResponse getSimpleRestResponse() {
        return simpleRestResponse;
    }

    public void setSimpleRestResponse(SimpleRestResponse simpleRestResponse) {
        this.simpleRestResponse = simpleRestResponse;
    }

    public ProvideResponse() {

    }
    /**
     * for direct initialization
     * @param jsonObject
     * @throws ResponseException
     */
    public ProvideResponse(JSONObject jsonObject) throws ResponseException {
        super(jsonObject);

        this._parseFromJSON(jsonObject);
    }

    @Override
    public void _parseFromJSON(JSONObject jsonObject) throws ResponseException {
        super._parseFromJSON(jsonObject);

        this.setSimpleRestResponse( simpleRestResponse);

        if(jsonObject.has("service")) {
            JSONObject serviceJSON = jsonObject.getJSONObject("service");

            Service service = Service.parseFromJSON(serviceJSON);
            this.setService(service);
        }
    }
}
