package com.gportal.zeuz.api.call.entity;

import com.gportal.zeuz.api.call.Endpoint;
import com.gportal.zeuz.api.model.response.RestResponseInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiResponse<T> {
    private int responseCode;
    private String rawResponse;
    private Endpoint endpoint;
    private RestResponseInterface restResponseInterface;

    public boolean isModel() {
        return this.restResponseInterface != null;
    }

    public RestResponseInterface getRestResponseInterface() {
        return restResponseInterface;
    }

    public void setRestResponseInterface(RestResponseInterface restResponseInterface) {
        this.restResponseInterface = restResponseInterface;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }

    public ApiResponse(int responseCode, String rawResponse, Endpoint endpoint) {
        this.responseCode = responseCode;
        this.rawResponse = rawResponse;
        this.endpoint = endpoint;


        if(endpoint.getResponseClass() != null) {
            try {
                RestResponseInterface restResponseInterface = endpoint.parseResponse(new JSONObject(rawResponse));

                this.setRestResponseInterface(restResponseInterface);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isValidJSON() {
        try {
            new JSONObject(this.getRawResponse());
        }
        catch (JSONException ex) {
            try {
                new JSONArray(this.getRawResponse());
            }
            catch (JSONException ex1) {
                return false;
            }
        }

        return true;
    }

    public boolean isOK() {
        return this.getResponseCode() == 200;
    }

    /**
     * trys to convert the string response into json,
     * if its not possible it returns null
     *
     * @author zeuz.io
     * @return JsonObject
     */
    public JSONObject convertToJson() {
        if(isValidJSON()) {
            return
                    new JSONObject(this.rawResponse);
        }

        return null;
    }
}
