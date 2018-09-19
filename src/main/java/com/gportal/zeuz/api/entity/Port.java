package com.gportal.zeuz.api.entity;

import org.json.JSONObject;

public class Port {
    private int id;
    private String type;
    private int port;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static Port parseFromJSON(JSONObject jsonObject) {
        Port port = new Port();

        if(jsonObject.has("id")) {
            port.setId(jsonObject.getInt("id"));
        }

        if(jsonObject.has("type")) {
            port.setType(jsonObject.getString("type"));
        }

        if(jsonObject.has("port")) {
            port.setPort(jsonObject.getInt("port"));
        }

        return port;
    }
}
