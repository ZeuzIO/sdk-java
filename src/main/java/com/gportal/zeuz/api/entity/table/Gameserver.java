package com.gportal.zeuz.api.entity.table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Gameserver {
    private SimpleStringProperty serviceId = new SimpleStringProperty();
    private SimpleStringProperty requested = new SimpleStringProperty();
    private SimpleStringProperty state = new SimpleStringProperty();

    public String getServiceId() {
        return serviceId.get();
    }

    public SimpleStringProperty serviceIdProperty() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId.set(serviceId);
    }

    public String getRequested() {
        return requested.get();
    }

    public SimpleStringProperty requestedProperty() {
        return requested;
    }

    public void setRequested(String requested) {
        this.requested.set(requested);
    }

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public Gameserver(String serviceId) {
        this.setServiceId(serviceId);
    }
}
