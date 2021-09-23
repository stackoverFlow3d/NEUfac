package com.neu.fac.controller;

import com.neu.fac.dao.WorkDao;
import com.neu.fac.pojo.WorkEntity;

import java.io.IOException;
import java.util.List;

public class WorkController {
    private static WorkController workController = new WorkController();
    private final WorkDao workDao = WorkDao.getInstance();
    private WorkController(){

    }
    public static WorkController getInstance(){
        return workController;
    }

    public List<WorkEntity> findWorkList(String facName) {
        return workDao.findWorkList();
    }

    public List<WorkEntity> findCurrentWorkList(String facName) {
        return workDao.findCurrentWorkList(facName);
    }

    public boolean addWork(WorkEntity workEntity) {
        return workDao.saveWork(workEntity);
    }

    public boolean remove(String ordNun) throws IOException {
        return workDao.remove(ordNun);
    }
}
