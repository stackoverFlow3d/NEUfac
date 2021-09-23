package com.neu.fac.controller;

import com.neu.fac.dao.PowerDao;
import com.neu.fac.pojo.PowerEntity;

import java.io.IOException;
import java.util.List;

public class PowerController {
    private final PowerDao powerDao = PowerDao.getInstance();
    private static PowerController powerController = new PowerController();
    private PowerController(){

    }
    public static PowerController getInstance(){
        return powerController;
    }

    public List<PowerEntity> findCurrentPowerList(String number) {
        return powerDao.findCurrentPowerList(number);
    }

    public boolean addPower(PowerEntity powerEntity) {
        return powerDao.savePower(powerEntity);
    }

    public boolean remove(String equipNun) throws IOException {
        return powerDao.remove(equipNun);
    }
}
