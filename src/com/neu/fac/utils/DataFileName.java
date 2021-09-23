package com.neu.fac.utils;

public enum DataFileName {
    USER("user"),
    Product("product"),
    ProductType("productType"),
    Equipment("eqipment"),
    EquipmentType("equipmentType"),
    Order("order"),
    Bid("bid"),
    Work("work"),
    Power("power")
    ;
    private final String fileName;

    DataFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

