package com.gportal.zeuz.api.model.response;

import com.gportal.zeuz.api.call.exception.ResponseException;
import org.json.JSONObject;

public class SimpleRestResponse {
    private boolean success;
    private String error;
    private RequestInformation requestInformation;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RequestInformation getRequestInformation() {
        return requestInformation;
    }

    public void setRequestInformation(RequestInformation requestInformation) {
        this.requestInformation = requestInformation;
    }

    public SimpleRestResponse() {}

    public SimpleRestResponse(JSONObject jsonObject) throws ResponseException {
        this._parseFromJSON(jsonObject);
    }

    protected void _parseFromJSON(JSONObject jsonObject) throws ResponseException {
        if(jsonObject.has("success")) {
            this.setSuccess(jsonObject.getBoolean("success"));
        }

        if(jsonObject.has("error")) {
            this.setError(jsonObject.getString("error"));
        }

        if(jsonObject.has("request")) {
            JSONObject requestJSON = jsonObject.getJSONObject("request");

            RequestInformation requestInformation = new RequestInformation(requestJSON);
            this.setRequestInformation(requestInformation);
        }
    }
}
