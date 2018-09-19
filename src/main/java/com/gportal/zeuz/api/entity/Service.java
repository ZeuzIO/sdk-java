package com.gportal.zeuz.api.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Service {
    private String primaryIp;
    private String externalIp;
    private String gamePort;
    private String queryPort;
    private List<Port> ports = new ArrayList<>();
    private String hardwareId;
    private String serverGroupId;
    private String serverGroupName;
    private String hardwareType;
    private String hardwareStatus;
    private String provider;
    private String serverGroupStatus;
    private String hardwareProfileId;
    private List<HardwareIp> hardwareIpList = new ArrayList<>();
    private String profileId;
    private String profileStatus;
    private String profileVersion;
    private String profileName;
    private String serviceId;
    private String createdAt;
    private String updatedAt;
    private Integer version;
    private boolean manualAllocated;
    private boolean allocated;
    private boolean reserved;
    private String lastQuery;
    private int restartRetries = 0;
    private int failedCount = 0;
    private boolean ccuReserved;
    private int state;

    public String getPrimaryIp() {
        return primaryIp;
    }

    public void setPrimaryIp(String primaryIp) {
        this.primaryIp = primaryIp;
    }

    public String getExternalIp() {
        return externalIp;
    }

    public void setExternalIp(String externalIp) {
        this.externalIp = externalIp;
    }

    public String getGamePort() {
        return gamePort;
    }

    public void setGamePort(String gamePort) {
        this.gamePort = gamePort;
    }

    public String getQueryPort() {
        return queryPort;
    }

    public void setQueryPort(String queryPort) {
        this.queryPort = queryPort;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public String getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
    }

    public String getServerGroupId() {
        return serverGroupId;
    }

    public void setServerGroupId(String serverGroupId) {
        this.serverGroupId = serverGroupId;
    }

    public String getServerGroupName() {
        return serverGroupName;
    }

    public void setServerGroupName(String serverGroupName) {
        this.serverGroupName = serverGroupName;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

    public String getHardwareStatus() {
        return hardwareStatus;
    }

    public void setHardwareStatus(String hardwareStatus) {
        this.hardwareStatus = hardwareStatus;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getServerGroupStatus() {
        return serverGroupStatus;
    }

    public void setServerGroupStatus(String serverGroupStatus) {
        this.serverGroupStatus = serverGroupStatus;
    }

    public String getHardwareProfileId() {
        return hardwareProfileId;
    }

    public void setHardwareProfileId(String hardwareProfileId) {
        this.hardwareProfileId = hardwareProfileId;
    }

    public List<HardwareIp> getHardwareIpList() {
        return hardwareIpList;
    }

    public void setHardwareIpList(List<HardwareIp> hardwareIpList) {
        this.hardwareIpList = hardwareIpList;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }

    public String getProfileVersion() {
        return profileVersion;
    }

    public void setProfileVersion(String profileVersion) {
        this.profileVersion = profileVersion;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isManualAllocated() {
        return manualAllocated;
    }

    public void setManualAllocated(boolean manualAllocated) {
        this.manualAllocated = manualAllocated;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public String getLastQuery() {
        return lastQuery;
    }

    public void setLastQuery(String lastQuery) {
        this.lastQuery = lastQuery;
    }

    public int getRestartRetries() {
        return restartRetries;
    }

    public void setRestartRetries(int restartRetries) {
        this.restartRetries = restartRetries;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public boolean isCcuReserved() {
        return ccuReserved;
    }

    public void setCcuReserved(boolean ccuReserved) {
        this.ccuReserved = ccuReserved;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static Service parseFromJSON(JSONObject jsonObject) {
        Service service = new Service();
        
        if(jsonObject.has("primaryIp")) {
            service.setPrimaryIp(jsonObject.getString("primaryIp"));
        }

        if(jsonObject.has("externalIp")) {
            service.setExternalIp(jsonObject.getString("externalIp"));
        }

        if(jsonObject.has("gamePort")) {
            service.setGamePort(jsonObject.getString("gamePort"));
        }

        if(jsonObject.has("queryPort")) {
            service.setQueryPort(jsonObject.getString("queryPort"));
        }

        if(jsonObject.has("ports")) {
            JSONArray portsJSON = jsonObject.getJSONArray("ports");

            if(portsJSON.length() > 0) {
                for(int i = 0; i < portsJSON.length(); i++) {
                    JSONObject portJSON = portsJSON.getJSONObject(i);
                    Port port = Port.parseFromJSON(portJSON);

                    service.getPorts().add(port);
                }
            }
        }

        if(jsonObject.has("hardwareId")) {
            service.setHardwareId(jsonObject.getString("hardwareId"));
        }

        if(jsonObject.has("serverGroupId")) {
            service.setServerGroupId(jsonObject.getString("serverGroupId"));
        }

        if(jsonObject.has("serverGroupName")) {
            service.setServerGroupName(jsonObject.getString("serverGroupName"));
        }

        if(jsonObject.has("hardwareType")) {
            service.setHardwareType(jsonObject.getString("hardwareType"));
        }

        if(jsonObject.has("hardwareStatus")) {
            service.setHardwareStatus(jsonObject.getString("hardwareStatus"));
        }

        if(jsonObject.has("provider")) {
            service.setProvider(jsonObject.getString("provider"));
        }

        if(jsonObject.has("serverGroupStatus")) {
            service.setServerGroupStatus(jsonObject.getString("serverGroupStatus"));
        }

        if(jsonObject.has("hardwareProfileId")) {
            service.setHardwareProfileId(jsonObject.getString("hardwareProfileId"));
        }

        if(jsonObject.has("hardwareIpList")) {
            JSONArray hardwareIpListJSON = jsonObject.getJSONArray("hardwareIpList");

            if(hardwareIpListJSON.length() > 0) {
                for (int i = 0; i < hardwareIpListJSON.length(); i++) {
                    JSONObject hardwareIpJSON = hardwareIpListJSON.getJSONObject(i);

                    HardwareIp hardwareIp = HardwareIp.parseFromJSON(hardwareIpJSON);
                    service.getHardwareIpList().add(hardwareIp);
                }
            }
            service.setServerGroupStatus(jsonObject.getString("serverGroupStatus"));
        }

        if(jsonObject.has("profileId")) {
            service.setProfileId(jsonObject.getString("profileId"));
        }

        if(jsonObject.has("profileStatus")) {
            service.setProfileStatus(jsonObject.getString("profileStatus"));
        }

        if(jsonObject.has("profileVersion")) {
            service.setProfileVersion(jsonObject.getString("profileVersion"));
        }

        if(jsonObject.has("profileName")) {
            service.setProfileName(jsonObject.getString("profileName"));
        }

        if(jsonObject.has("serviceId")) {
            service.setServiceId(jsonObject.getString("serviceId"));
        }

        if(jsonObject.has("createdAt")) {
            service.setCreatedAt(jsonObject.getString("createdAt"));
        }

        if(jsonObject.has("updatedAt")) {
            service.setUpdatedAt(jsonObject.getString("updatedAt"));
        }

        if(jsonObject.has("version")) {
            service.setVersion(jsonObject.getInt("version"));
        }

        if(jsonObject.has("manualAllocated")) {
            service.setManualAllocated(jsonObject.getBoolean("manualAllocated"));
        }

        if(jsonObject.has("allocated")) {
            service.setAllocated(jsonObject.getBoolean("allocated"));
        }

        if(jsonObject.has("reserved")) {
            service.setReserved(jsonObject.getBoolean("reserved"));
        }

        if(jsonObject.has("lastQuery")) {
            service.setLastQuery(jsonObject.getString("lastQuery"));
        }

        if(jsonObject.has("restartRetries")) {
            service.setRestartRetries(jsonObject.getInt("restartRetries"));
        }

        if(jsonObject.has("failedCount")) {
            service.setFailedCount(jsonObject.getInt("failedCount"));
        }

        if(jsonObject.has("ccuReserved")) {
            service.setCcuReserved(jsonObject.getBoolean("ccuReserved"));
        }

        if(jsonObject.has("state")) {
            service.setState(jsonObject.getInt("state"));
        }


        return service;
    }
}
