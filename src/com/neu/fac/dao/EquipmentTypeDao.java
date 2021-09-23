package com.neu.fac.dao;

import com.neu.fac.controller.EquipmentTypeController;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.pojo.EquipmentTypeEntity;
import com.neu.fac.pojo.ProductTypeEntity;
import com.neu.fac.utils.DataFileName;
import com.neu.fac.utils.DataUtils;
import com.neu.fac.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentTypeDao {
    private static EquipmentTypeDao equipmentTypeDao = new EquipmentTypeDao();
    private EquipmentTypeDao(){

    }
    public static EquipmentTypeDao getInstance() {
        return equipmentTypeDao;
    }
    public boolean saveEquipmentType(EquipmentTypeEntity equipmentType){
        equipmentType.setId(getMaxId());
        String json = JsonUtils.objectToJson(equipmentType);
        try{
            DataUtils.writeData(DataFileName.EquipmentType.getFileName(),json);
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //系统分配一个ID
    private String getMaxId(){
        List<EquipmentTypeEntity> equipmentTypeEntityList = findEquipmentTpyeList();
        int max = 0;
        if(equipmentTypeEntityList ==null|| equipmentTypeEntityList.size()==0){
            return "1";
        }
        for(EquipmentTypeEntity equipmentType : equipmentTypeEntityList){
            if(Integer.parseInt(equipmentType.getId())>max){
                max = Integer.parseInt(equipmentType.getId());
            }
        }
        return String.valueOf(max+1);
    }

    public List<EquipmentTypeEntity> findEquipmentTpyeList() {
        String json = DataUtils.readData(DataFileName.EquipmentType.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<EquipmentTypeEntity> equipmentTypesListEntity = JsonUtils.jsonToList(json, EquipmentTypeEntity.class);
            return equipmentTypesListEntity;
        }
        return null;
    }
    public EquipmentTypeEntity searchEquipmentType(String type) {
        List<EquipmentTypeEntity> equipmentTypes = equipmentTypeDao.findEquipmentTpyeList();
        for (EquipmentTypeEntity equipmentTypeEntity:equipmentTypes){
            if(equipmentTypeEntity.getType().equals(type)){
                return equipmentTypeEntity;
            }
        }
        return null;
    }

    public List<String> getTpyeStringList() {
        List<String> tpyeStringList = new ArrayList<String>();
        List<EquipmentTypeEntity> equipmentTypes = equipmentTypeDao.findEquipmentTpyeList();
        for (EquipmentTypeEntity equipmentTypeEntity:equipmentTypes){
            tpyeStringList.add(equipmentTypeEntity.getType());
        }
        return tpyeStringList;
    }

    public boolean removeType(String id) throws IOException {
        List<EquipmentTypeEntity> equipmentTypeEntityList = findEquipmentTpyeList();
        for(EquipmentTypeEntity productType :equipmentTypeEntityList){
            if (productType.getId().equals(id)){
                equipmentTypeEntityList.remove(productType);
                DataUtils.deleteData(DataFileName.EquipmentType.getFileName());
                for(EquipmentTypeEntity u2:equipmentTypeEntityList){
                    saveEquipmentType(u2);
                }
                return true;
            }
        }
        return false;
    }
    public boolean modifyEquiptype(String id, EquipmentTypeEntity equipmentTypeEntity) throws IOException {
        List<EquipmentTypeEntity> productTypeEntities = equipmentTypeDao.findEquipmentTpyeList();
        for (EquipmentTypeEntity p1:productTypeEntities){
            if(p1.getId().equals(id)){
                p1.setType(equipmentTypeEntity.getType());
                DataUtils.deleteData(DataFileName.EquipmentType.getFileName());
                for(EquipmentTypeEntity u2:productTypeEntities){
                    saveEquipmentType(u2);
                }
                return true;
            }
        }
        return false;
    }
}
