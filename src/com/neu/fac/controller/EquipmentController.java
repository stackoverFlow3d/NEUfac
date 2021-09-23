package com.neu.fac.controller;

import com.neu.fac.dao.EquipmentDao;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.pojo.UserEntity;

import java.io.IOException;
import java.util.List;

public class EquipmentController {
    private final EquipmentDao equipmentDao = EquipmentDao.getInstance();
    private static EquipmentController equipmentController = new EquipmentController();

    private EquipmentController(){

    }

    public static EquipmentController getInstance(){
        return equipmentController;
    }

    //新建
    public boolean newEquipment(EquipmentEntity equipment){
        return equipmentDao.saveEquipment(equipment);
    }
    //删除
    public boolean removeEquipment(String id) throws IOException {
        return equipmentDao.removeEquipment(id);
    }

    public boolean removeSelfProduct(String id) throws IOException {
        return equipmentDao.removeSelfEquipment(id);
    }
    //修改
    public boolean modifyEquipment(String id,EquipmentEntity equipment) throws IOException {
        return equipmentDao.modifyProduct(id,equipment);
    }
    public boolean modifySelfEquipment(String id,EquipmentEntity equipment) throws IOException {
        return equipmentDao.modifySelfProduct(id,equipment);
    }
    //检索
    public EquipmentEntity searchEquipment(String name){
        if(name.equals("")){

            return new EquipmentEntity();
        }
        return equipmentDao.searchEquipment(name);
    }

    public List<EquipmentEntity> getEquipmentList(){
        return equipmentDao.findEquipmentList();
    }


    public boolean distanceController(EquipmentEntity equipmentEntity) throws IOException {
        return equipmentDao.distanceController(equipmentEntity);
    }

    public List<EquipmentEntity> findMangerList(UserEntity manager){
        return equipmentDao.findMangerList(manager);
    }

    //获取工厂租用设备列表
    public List<EquipmentEntity> findBorrwList(UserEntity manager){
        return equipmentDao.findBrorrowList(manager);
    }

    public boolean borrowEquipment(EquipmentEntity borrower, UserEntity userDate) throws IOException {
        return equipmentDao.borrowEquipment(borrower,userDate);
    }

    public void updata(String old,String newType) throws IOException {
        equipmentDao.updateEquip(old,newType);
    }

    public List<String> getEquipStringList(String facName) {
        return equipmentDao.getEquipStringList(facName);
    }
}
