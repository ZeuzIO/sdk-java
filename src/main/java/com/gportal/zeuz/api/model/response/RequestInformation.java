package com.gportal.zeuz.api.model.response;

import com.gportal.zeuz.api.call.exception.ResponseException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestInformation {
    private String uuid;
    private Date date;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RequestInformation(JSONObject jsonObject)
    throws ResponseException {
        _parseFromJSON(jsonObject);
    }

    public void _parseFromJSON(JSONObject jsonObject)
        throws ResponseException {
        if(jsonObject.has("uuid")) {
            this.setUuid(jsonObject.getString("uuid"));
        }

        if(jsonObject.has("date")) {
            JSONObject dateObject = jsonObject.getJSONObject("date");

            if(dateObject.has("date")) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d h:mm:ss.SSS");
                    Date requestDate = simpleDateFormat.parse(dateObject.getString("date"));

                    this.setDate(requestDate);
                }
                catch (Exception exception) {
                    exception.printStackTrace();

                    throw
                        new ResponseException("Invalid Date");
                }
            }
        }
    }
}
