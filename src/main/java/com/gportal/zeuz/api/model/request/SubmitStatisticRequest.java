package com.gportal.zeuz.api.model.request;

import com.gportal.zeuz.api.model.annotiations.JsonField;

import java.util.ArrayList;
import java.util.List;

public class SubmitStatisticRequest
        extends SimpleRestRequest
        implements RestRequestInterface {
    @Override
    protected String fetchFields() {
        return super.fetchFields();
    }

    @Override
    public String getJsonRequest() {
        return super.fetchFields();
    }

    @JsonField(fieldName = "hostname", displayType = JsonField.DisplayType.TEXT)
    private String hostname;

    @JsonField(fieldName = "maxPlayerCount", displayType = JsonField.DisplayType.NUMBER)
    private int maxPlayerCount;

    @JsonField(fieldName = "playerCount", displayType = JsonField.DisplayType.NUMBER)
    private int playerCount;

    @JsonField(fieldName = "serviceId", displayType = JsonField.DisplayType.TEXT)
    private String serviceId;

    @JsonField(fieldName = "map", displayType = JsonField.DisplayType.TEXT)
    private String map;

    @JsonField(fieldName = "botCount", displayType = JsonField.DisplayType.NUMBER)
    private int botCount = 0;

    @JsonField(fieldName = "attempts", displayType = JsonField.DisplayType.NUMBER)
    private int attempts = 0;

    @JsonField(fieldName = "latency", displayType = JsonField.DisplayType.LIST)
    private List<Integer> latency = new ArrayList<Integer>();

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getMaxPlayerCount() {
        return maxPlayerCount;
    }

    public void setMaxPlayerCount(int maxPlayerCount) {
        this.maxPlayerCount = maxPlayerCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getBotCount() {
        return botCount;
    }

    public void setBotCount(int botCount) {
        this.botCount = botCount;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public List<Integer> getLatency() {
        return latency;
    }

    public void setLatency(List<Integer> latency) {
        this.latency = latency;
    }
}
