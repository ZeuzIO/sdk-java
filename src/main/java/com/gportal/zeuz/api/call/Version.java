package com.gportal.zeuz.api.call;

public enum Version {
    V2_LIVE("Version 2", 2);

    private String name;
    private int versionNumber;
    private String customPath = null;

    public String getName() {
        return name;
    }

    public String getCustomPath() { return customPath; }

    public String getUriPart() {
        return getCustomPath() == null
                ? String.valueOf(getVersionNumber())
                : getCustomPath();
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    Version(String name, int versionNumber) {
        this.name = name;
        this.versionNumber = versionNumber;
    }

    Version(String name, String customPath) {
        this.name = name;
        this.customPath = customPath;
    }
}
