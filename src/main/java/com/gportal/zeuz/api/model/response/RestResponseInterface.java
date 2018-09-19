package com.gportal.zeuz.api.model.response;

import com.gportal.zeuz.api.call.exception.ResponseException;
import org.json.JSONObject;

public interface RestResponseInterface <T>  {
    void _parseFromJSON(JSONObject jsonObject) throws ResponseException;
    SimpleRestResponse getSimpleRestResponse();
}
