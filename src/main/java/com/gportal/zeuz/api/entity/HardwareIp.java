package com.gportal.zeuz.api.entity;

import org.json.JSONObject;

public class HardwareIp {
    private int id;
    private String type;
    private String format;
    private String createdAt;
    private String updatedAt;
    private String ipAddress;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public static HardwareIp parseFromJSON(JSONObject jsonObject) {
        HardwareIp hardwareIp = new HardwareIp();

        if(jsonObject.has("id")) {
            hardwareIp.setId(jsonObject.getInt("id"));
        }

        if(jsonObject.has("type")) {
            hardwareIp.setType(jsonObject.getString("type"));
        }

        if(jsonObject.has("format")) {
            hardwareIp.setFormat(jsonObject.getString("format"));
        }

        if(jsonObject.has("address")) {
            hardwareIp.setAddress(jsonObject.getString("address"));
        }

        if(jsonObject.has("createdAt")) {
            hardwareIp.setCreatedAt(jsonObject.getString("createdAt"));
        }

        if(jsonObject.has("updatedAt")) {
            hardwareIp.setUpdatedAt(jsonObject.getString("updatedAt"));
        }

        if(jsonObject.has("ipAddress")) {
            hardwareIp.setIpAddress(jsonObject.getString("ipAddress"));
        }

        return hardwareIp;
    }
}
