package com.gportal.zeuz.api.model.response;

import com.gportal.zeuz.api.call.exception.ResponseException;
import com.gportal.zeuz.api.entity.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.ws.Response;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ServiceListResponse
        extends SimpleRestResponse
            implements RestResponseInterface {

    private SimpleRestResponse simpleRestResponse;
    private List<Service> serviceList = new ArrayList<>();

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public SimpleRestResponse getSimpleRestResponse() {
        return simpleRestResponse;
    }

    public void setSimpleRestResponse(SimpleRestResponse simpleRestResponse) {
        this.simpleRestResponse = simpleRestResponse;
    }

    public ServiceListResponse() {

    }
    /**
     * for direct initialization
     * @param jsonObject
     * @throws ResponseException
     */
    public ServiceListResponse(JSONObject jsonObject) throws ResponseException {
        super(jsonObject);

        this._parseFromJSON(jsonObject);
    }

    @Override
    public void _parseFromJSON(JSONObject jsonObject) throws ResponseException {
        super._parseFromJSON(jsonObject);

        this.setSimpleRestResponse( simpleRestResponse);

        if(jsonObject.has("services")) {
            JSONArray servicesJSON = jsonObject.getJSONArray("services");

            if(servicesJSON.length() > 0) {
                for(int i = 0; i < servicesJSON.length(); i++) {
                    JSONObject serviceJSON = servicesJSON.getJSONObject(i);

                    Service service = Service.parseFromJSON(serviceJSON);
                    this.getServiceList().add(service);
                }
            }
        }
    }
}
