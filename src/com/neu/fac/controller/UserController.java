package com.neu.fac.controller;

import com.neu.fac.dao.UserDao;
import com.neu.fac.TableMoudle.FacTable;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.TableMoudle.UserTable;

import java.io.IOException;
import java.util.List;


public class UserController {
    private final UserDao userDao = UserDao.getInstance();
    private static UserController userController = new UserController();

    private UserController(){

    }
    public static UserController getInstance(){
        return userController;
    }
    public boolean register(UserEntity user){
        boolean flag = userDao.saveUser(user);
        return flag;
    }
    public UserEntity login(String username, String password){
        return userDao.login(username,password);
    }
    //用户管理表格数据
    public List<UserTable> getUserTableList(){
        return userDao.findUserTableList();
    }
    //工厂管理表格数据
    public List<FacTable> getFacTableList(){
        return userDao.findFacTableList();
    }
    //删除用户
    public boolean removeUser(String id) throws IOException {
        return userDao.removeUser(id);
    }
    //修改用户信息
    public boolean modifyUser(String id, UserEntity userEntity) throws IOException {
        return userDao.modifyUser(id,userEntity);
    }
    //查找用户信息
    public UserEntity searchUser(String id){
        return userDao.searchUser(id);
    }


    public List<UserEntity> getUserList() {
        return userDao.findUserList();
    }
}
