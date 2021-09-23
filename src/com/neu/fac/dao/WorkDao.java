package com.neu.fac.dao;


import com.neu.fac.pojo.BidEntity;
import com.neu.fac.pojo.WorkEntity;
import com.neu.fac.utils.DataFileName;
import com.neu.fac.utils.DataUtils;
import com.neu.fac.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkDao {
    private static WorkDao workDao = new WorkDao();
    private WorkDao(){

    }
    public static WorkDao getInstance() {
        return workDao;
    }
    public boolean saveWork(WorkEntity work){
        String json = JsonUtils.objectToJson(work);
        try{
            DataUtils.writeData(DataFileName.Work.getFileName(),json);
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<WorkEntity> findWorkList(){
        String json = DataUtils.readData(DataFileName.Work.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<WorkEntity> userList = JsonUtils.jsonToList(json, WorkEntity.class);
            return userList;
        }
        return null;
    }

    public List<WorkEntity> findCurrentWorkList(String facName) {
        List<WorkEntity> workEntities = workDao.findWorkList();
        List<WorkEntity> workEntities1 = new ArrayList<WorkEntity>();
        for (WorkEntity workEntity:workEntities){
            if(workEntity.getOrdernum().equals(facName)){
                workEntities1.add(workEntity);
            }
        }
        return workEntities1;
    }

    public boolean remove(String ordNun) throws IOException {
        List<WorkEntity> workEntities = workDao.findWorkList();
        for(WorkEntity bidEntity:workEntities){
            if(bidEntity.getEuqipName().equals(ordNun)){
                workEntities.remove(bidEntity);
            }
            DataUtils.deleteData(DataFileName.Work.getFileName());
            for(WorkEntity u2:workEntities){
                saveWork(u2);
            }
            return true;
        }
        return false;
    }
}
