package com.neu.fac.controller;

import com.neu.fac.dao.EquipmentTypeDao;
import com.neu.fac.pojo.EquipmentTypeEntity;
import com.neu.fac.pojo.ProductTypeEntity;
import com.neu.fac.utils.DataFileName;
import com.neu.fac.utils.DataUtils;

import java.io.IOException;
import java.util.List;

public class EquipmentTypeController {
    private final EquipmentTypeDao equipmentTypeDao = EquipmentTypeDao.getInstance();
    private static EquipmentTypeController equipmentTypeController = new EquipmentTypeController();

    public static EquipmentTypeController getInstance() {
        return equipmentTypeController;

    }

    public List<EquipmentTypeEntity> getEquipmentTpyeList() {
        return equipmentTypeDao.findEquipmentTpyeList();
    }

    //检索
    public EquipmentTypeEntity searchEquipmentType(String type){
        if(type.equals("")){
            return new EquipmentTypeEntity();
        }
        return equipmentTypeDao.searchEquipmentType(type);
    }

    public boolean addType(EquipmentTypeEntity equipmentType) {
        return equipmentTypeDao.saveEquipmentType(equipmentType);
    }

    public List<String> getTpyeStringList() {
        return equipmentTypeDao.getTpyeStringList();
    }

    public boolean removeType(String id) throws IOException {
        return equipmentTypeDao.removeType(id);
    }

    public boolean modifyEquiptype(String id, EquipmentTypeEntity equipmentTypeEntity) throws IOException {
        return equipmentTypeDao.modifyEquiptype(id,equipmentTypeEntity);
    }
}