package com.neu.fac.pojo;

import java.util.List;

//设备实例
public class EquipmentEntity {
    private String name;
    private String id;
    private String type;
    private String number;
    private String size;
    private String description;
    private String euipmentStatus;
    private String leaseStatus;
    private List<String> types;
    //设备类型
    private String factory;
    private String currentFac;

    public EquipmentEntity() {
        this.euipmentStatus = "闲置中";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEuipmentStatus() {
        return euipmentStatus;
    }

    public void setEuipmentStatus(String euipmentStatus) {
        this.euipmentStatus = euipmentStatus;
    }

    public String getLeaseStatus() {
        return leaseStatus;
    }

    public void setLeaseStatus(String leaseStatus) {
        this.leaseStatus = leaseStatus;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getCurrentFac() {
        return currentFac;
    }

    public void setCurrentFac(String currentFac) {
        this.currentFac = currentFac;
    }
}
