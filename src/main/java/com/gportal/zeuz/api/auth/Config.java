package com.gportal.zeuz.api.auth;

import com.gportal.zeuz.api.call.exception.ApiException;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class Config {
    private static final String configFileName = "config.ini";

    private Wini wini;
    private static Config instance;

    public static Config getInstance() throws IOException {
        if(instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public static void setInstance(Config instance) {
        Config.instance = instance;
    }

    public static String getConfigFileName() {
        return configFileName;
    }

    public Wini getWini() {
        return wini;
    }

    private Config() throws IOException {
        File fileWini = new File(configFileName);

        if(!fileWini.exists()) {
            System.out.println("Creating new wini file!");
            fileWini.createNewFile();
        }

        this.wini = new Wini(fileWini);
    }

    public Config put(String section, String field, String value) {
        this.getWini().put(section, field, value);

        return this;
    }

    public String fetch(String section, String field) {
        return this.getWini().get(section, field);
    }

    public void store() {
        try {
            System.out.println("Storing changes into " + getConfigFileName());

            this.getWini().store();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
