package com.example.devicemanagementsystem.Models;

public class Department {
    private String obejctId;
    private String name;

    public Department(String obejctId, String name) {
        this.obejctId = obejctId;
        this.name = name;
    }

    public String getObejctId() {
        return obejctId;
    }

    public void setObejctId(String obejctId) {
        this.obejctId = obejctId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
