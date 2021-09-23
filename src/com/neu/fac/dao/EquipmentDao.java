package com.neu.fac.dao;

import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.utils.DataFileName;
import com.neu.fac.utils.DataUtils;
import com.neu.fac.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDao {

    private static EquipmentDao equipmentDao = new EquipmentDao();

    private EquipmentDao() {

    }
    public boolean saveEquipment(EquipmentEntity equipment){
        equipment.setId(getMaxId());

        String json = JsonUtils.objectToJson(equipment);
        try{
            DataUtils.writeData(DataFileName.Equipment.getFileName(),json);
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static EquipmentDao getInstance(){
        return equipmentDao;
    }
    //从文件读取用户信息存储至列表
    public List<EquipmentEntity> findEquipmentList(){
        String json = DataUtils.readData(DataFileName.Equipment.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<EquipmentEntity> equipmentList = JsonUtils.jsonToList(json, EquipmentEntity.class);
            return equipmentList;
        }
        return null;
    }
    //系统分配一个ID
    private String getMaxId(){
        List<EquipmentEntity> equipmentList = findEquipmentList();
        int max = 0;
        if(equipmentList==null||equipmentList.size()==0){
            return "1";
        }
        for(EquipmentEntity equipment:equipmentList){
            if(Integer.parseInt(equipment.getId())>max){
                max = Integer.parseInt(equipment.getId());
            }
        }
        return String.valueOf(max+1);
    }


    public boolean removeEquipment(String id) throws IOException {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        for(EquipmentEntity productType :equipmentEntities){
            if (productType.getId().equals(id)){
                if(productType.getLeaseStatus().equals("已租用")&&!productType.getFactory().equals("平台设备")){
                    return false;
                }
                equipmentEntities.remove(productType);
                DataUtils.deleteData(DataFileName.Equipment.getFileName());
                for(EquipmentEntity u2:equipmentEntities){
                    saveEquipment(u2);
                }
                return true;
            }
        }
        return false;
    }

    public boolean removeSelfEquipment(String id) throws IOException {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        for(EquipmentEntity productType :equipmentEntities){
            if (productType.getId().equals(id)){
                if(productType.getLeaseStatus().equals("已租用")){
                    return false;
                }
                equipmentEntities.remove(productType);
                DataUtils.deleteData(DataFileName.Equipment.getFileName());
                for(EquipmentEntity u2:equipmentEntities){
                    saveEquipment(u2);
                }
                return true;
            }
        }
        return false;
    }
    public boolean modifyProduct(String id, EquipmentEntity equipment) throws IOException {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        for (EquipmentEntity p1:equipmentEntities){
            if(p1.getId().equals(id)){
                if(p1.getLeaseStatus().equals("已租用")&&!p1.getFactory().equals("平台设备")){
                    return false;
                }
                p1.setDescription(equipment.getDescription());
                p1.setFactory(equipment.getFactory());
                p1.setSize(equipment.getSize());
                p1.setType(equipment.getType());
                p1.setName(equipment.getName());
                DataUtils.deleteData(DataFileName.Equipment.getFileName());
                for(EquipmentEntity u2:equipmentEntities){
                    saveEquipment(u2);
                }
                return true;
            }
        }
        return false;
    }
    public boolean modifySelfProduct(String id, EquipmentEntity equipment) throws IOException {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        for (EquipmentEntity p1:equipmentEntities){
            if(p1.getId().equals(id)){
                if(p1.getLeaseStatus().equals("已租用")){
                    return false;
                }
                p1.setDescription(equipment.getDescription());
                p1.setFactory(equipment.getFactory());
                p1.setSize(equipment.getSize());
                p1.setType(equipment.getType());
                p1.setName(equipment.getName());
                DataUtils.deleteData(DataFileName.Equipment.getFileName());
                for(EquipmentEntity u2:equipmentEntities){
                    saveEquipment(u2);
                }
                return true;
            }
        }
        return false;
    }

    public EquipmentEntity searchEquipment(String name) {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        for (EquipmentEntity p1:equipmentEntities){
            if(p1.getName().equals(name)){
               return p1;
            }
        }
        return null;
    }

    public boolean distanceController(EquipmentEntity equipmentEntity) throws IOException {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        for (EquipmentEntity p1:equipmentEntities){
            if(p1.getId().equals(equipmentEntity.getId())){
                if(p1.getEuipmentStatus().equals("闲置中")){
                    p1.setEuipmentStatus("已关闭");
                }else if(p1.getEuipmentStatus().equals("已关闭")){
                    p1.setEuipmentStatus("闲置中");
                }
                DataUtils.deleteData(DataFileName.Equipment.getFileName());
                for(EquipmentEntity u2:equipmentEntities){
                    saveEquipment(u2);
                }
                return true;
            }
        }
        return false;
    }

    public List<EquipmentEntity> findMangerList(UserEntity manager) {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        List<EquipmentEntity> equipments = new ArrayList<EquipmentEntity>();
        for (EquipmentEntity p1:equipmentEntities){
            if(p1.getCurrentFac().equals(manager.getFacName())){
                equipments.add(p1);
            }
        }
        return equipments;
    }
    //返回可租用的设备信息
    public List<EquipmentEntity> findBrorrowList(UserEntity manager) {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        List<EquipmentEntity> equipments = new ArrayList<EquipmentEntity>();
        for (EquipmentEntity p1:equipmentEntities){
            if(p1.getLeaseStatus().equals("未租用")&&p1.getEuipmentStatus().equals("闲置中")){
                equipments.add(p1);
            }
        }
        return equipments;
    }
    //租用设备
    public boolean borrowEquipment(EquipmentEntity equipmentEntity,UserEntity brorrower) throws IOException {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        for (EquipmentEntity p1:equipmentEntities){
            if(p1.getId().equals(equipmentEntity.getId())){
                p1.setLeaseStatus("已租用");
                p1.setCurrentFac(brorrower.getFacName());
                DataUtils.deleteData(DataFileName.Equipment.getFileName());
                for(EquipmentEntity u2:equipmentEntities){
                    saveEquipment(u2);
                }
                return true;
            }
        }
        return false;
    }

    public void updateEquip(String old,String newType) throws IOException {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        for (EquipmentEntity p1:equipmentEntities){
            if(p1.getType().equals(old)){
                p1.setType(newType);
            }
        }
        DataUtils.deleteData(DataFileName.Equipment.getFileName());
        for(EquipmentEntity u2:equipmentEntities){
            saveEquipment(u2);
        }

    }

    public List<String> getEquipStringList(String facName) {
        List<String> strings = new ArrayList<String>();
        List<EquipmentEntity> equipmentEntities = equipmentDao.findEquipmentList();
        for (EquipmentEntity p1:equipmentEntities){
            if(p1.getCurrentFac().equals(facName)){
                strings.add(p1.getName());
            }
        }
        return strings;
    }
}
