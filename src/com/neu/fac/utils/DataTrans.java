package com.neu.fac.utils;

import com.neu.fac.TableMoudle.FacTable;
import com.neu.fac.pojo.*;
import javafx.scene.control.TableView;

public class DataTrans {
    private static UserEntity UserDate;
    private static EquipmentEntity equipmentData;
    private static ProductEntity productEntity;
    private static TableView<FacTable> table;
    private static EquipmentTypeEntity equipmentTypeEntity;
    private static ProductTypeEntity productTypeEntity;
    private static OrderEntity orderEntity;
    private static DataTrans dataTrans = new DataTrans();
    private static BidEntity bidEntity;
    private static TableView<OrderEntity> otable;
    private DataTrans(){

    }
    public static DataTrans getInstance(){
        return dataTrans;
    }
    public static UserEntity getUserDate() {
        return UserDate;
    }

    public static void setUserDate(UserEntity userDate) {
        UserDate = userDate;
    }

    public static EquipmentEntity getEquipmentData() {
        return equipmentData;
    }

    public static void setEquipmentData(EquipmentEntity equipmentData) {
        DataTrans.equipmentData = equipmentData;
    }

    public static DataTrans getDataTrans() {
        return dataTrans;
    }

    public static void setDataTrans(DataTrans dataTrans) {
        DataTrans.dataTrans = dataTrans;
    }

    public static ProductEntity getProductEntity() {
        return productEntity;
    }

    public static void setProductEntity(ProductEntity productEntity) {
        DataTrans.productEntity = productEntity;
    }

    public static TableView<FacTable> getTable() {
        return table;
    }

    public static void setTable(TableView<FacTable> table) {
        DataTrans.table = table;
    }

    public static EquipmentTypeEntity getEquipmentTypeEntity() {
        return equipmentTypeEntity;
    }

    public static void setEquipmentTypeEntity(EquipmentTypeEntity equipmentTypeEntity) {
        DataTrans.equipmentTypeEntity = equipmentTypeEntity;
    }

    public static ProductTypeEntity getProductTypeEntity() {
        return productTypeEntity;
    }

    public static void setProductTypeEntity(ProductTypeEntity productTypeEntity) {
        DataTrans.productTypeEntity = productTypeEntity;
    }

    public static OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public static void setOrderEntity(OrderEntity orderEntity) {
        DataTrans.orderEntity = orderEntity;
    }

    public static BidEntity getBidEntity() {
        return bidEntity;
    }

    public static void setBidEntity(BidEntity bidEntity) {
        DataTrans.bidEntity = bidEntity;
    }

    public static TableView<OrderEntity> getOtable() {
        return otable;
    }

    public static void setOtable(TableView<OrderEntity> otable) {
        DataTrans.otable = otable;
    }

    public static void setFacTable(TableView<OrderEntity> orderTable1) {
    }
}
