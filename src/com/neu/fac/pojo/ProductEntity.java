package com.neu.fac.pojo;

import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.List;

public class ProductEntity {
    //产品信息
    private String name;
    private String number;
    private String size;
    private String description;
    //产品类别
    private String id;
    private String type;
    //所有产品类别
    private List<ProductTypeEntity> types;

    private SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ProductTypeEntity> getTypes() {
        return types;
    }

    public void setTypes(List<ProductTypeEntity> types) {
        this.types = types;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }
}
