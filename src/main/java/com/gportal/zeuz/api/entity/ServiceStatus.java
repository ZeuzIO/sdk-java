package com.gportal.zeuz.api.entity;

public enum ServiceStatus {
    LOADING(1337),
    PREINSTALL(0),
    INSTALL(1),
    SUSPENDED(2),
    STOPPED(3),
    STOPPING(4),
    STARTING(5),
    RUNNING(6),
    UPDATING(7),
    REMOVING (98),
    MAINTENANCE (99),
    UNKNOWN(100);

    private int id;

    public static ServiceStatus findByName(String name) {
        if(values().length > 0) {
            for(ServiceStatus serviceStatus : values()) {
                if(serviceStatus.name().equalsIgnoreCase(name)) {
                    return serviceStatus;
                }
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    ServiceStatus(int id) {
        this.id = id;
    }
}
