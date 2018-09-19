package com.gportal.zeuz.api.model.request;

import com.gportal.zeuz.api.model.annotiations.JsonField;

public class AllocateRequest
        extends SimpleRestRequest
            implements RestRequestInterface {
    @JsonField(fieldName = "gameProfileId", displayType = JsonField.DisplayType.TEXT)
    private String gameProfileId;

    @JsonField(fieldName = "serverProfileId", displayType = JsonField.DisplayType.TEXT)
    private String serverProfileId;

    public String getGameProfileId() {
        return gameProfileId;
    }

    public void setGameProfileId(String gameProfileId) {
        this.gameProfileId = gameProfileId;
    }

    public String getServerProfileId() {
        return serverProfileId;
    }

    public void setServerProfileId(String serverProfileId) {
        this.serverProfileId = serverProfileId;
    }

    @Override
    protected String fetchFields() {
        return super.fetchFields();
    }

    @Override
    public String getJsonRequest() {
        return super.fetchFields();
    }
}
