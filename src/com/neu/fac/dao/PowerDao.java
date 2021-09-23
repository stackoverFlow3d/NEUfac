package com.neu.fac.dao;

import com.neu.fac.pojo.PowerEntity;
import com.neu.fac.pojo.ProductEntity;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.utils.DataFileName;
import com.neu.fac.utils.DataUtils;
import com.neu.fac.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PowerDao {
    private static PowerDao powerDao = new PowerDao();
    private PowerDao(){

    }
    public static PowerDao getInstance(){
        return powerDao;
    }
    public boolean savePower(PowerEntity power){
        String json = JsonUtils.objectToJson(power);
        try{
            DataUtils.writeData(DataFileName.Power.getFileName(),json);
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //从文件读取用户信息存储至列表
    public List<PowerEntity> findPowerList(){
        String json = DataUtils.readData(DataFileName.Power.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<PowerEntity> powerList = JsonUtils.jsonToList(json, PowerEntity.class);
            return powerList;
        }
        return null;
    }

    public List<PowerEntity> findCurrentPowerList(String number) {
        List<PowerEntity> powerEntities = powerDao.findPowerList();
        List<PowerEntity> powerEntities1 = new ArrayList<PowerEntity>();
        for(PowerEntity productEntity:powerEntities){
            if(productEntity.getEquipNun().equals(number)){
                powerEntities1.add(productEntity);
            }
        }
        return powerEntities1;
    }

    public boolean remove(String equipNun) throws IOException {
        List<PowerEntity> powerEntities = powerDao.findPowerList();
        for(PowerEntity productEntity:powerEntities){
            if(productEntity.getProducrName().equals(equipNun)){
                powerEntities.remove(productEntity);
                DataUtils.deleteData(DataFileName.Power.getFileName());
                for(PowerEntity u2:powerEntities){
                    savePower(u2);
                }
                return true;
            }
        }
        return false;
    }
}
