package com.neu.fac.dao;

import com.neu.fac.TableMoudle.FacTable;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.TableMoudle.UserTable;
import com.neu.fac.utils.DataFileName;
import com.neu.fac.utils.DataTrans;
import com.neu.fac.utils.DataUtils;
import com.neu.fac.utils.JsonUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //内置超级管理员
    private static final UserEntity ADMIN = new UserEntity();
    private static UserDao userDao = new UserDao();
    private UserDao(){
        ADMIN.setUserName("admin");
        ADMIN.setPassWord("admin");
    }

    public static UserDao getInstance() {
        return userDao;
    }

    //保存用户信息
    public boolean saveUser(UserEntity user){
        user.setId(getMaxId());
        String json = JsonUtils.objectToJson(user);
        try{
            DataUtils.writeData(DataFileName.USER.getFileName(),json);
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //从文件读取用户信息存储至列表
    public List<UserEntity> findUserList(){
        String json = DataUtils.readData(DataFileName.USER.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<UserEntity> userList = JsonUtils.jsonToList(json, UserEntity.class);
            return userList;
        }
        return null;
    }
    //系统分配一个ID
    private String getMaxId(){
        List<UserEntity> userList = findUserList();
        int max = 0;
        if(userList==null||userList.size()==0){
            return "1";
        }
        for(UserEntity user:userList){
            if(Integer.parseInt(user.getId())>max){
                max = Integer.parseInt(user.getId());
            }
        }
        return String.valueOf(max+1);
    }
    //登录功能：从列表寻找用户数据
    public UserEntity login(String username, String password){
        List<UserEntity> userList = findUserList();
        if(ADMIN.getUserName().equals(username)&&ADMIN.getPassWord().equals(password)){
            return ADMIN;
        }
        if(userList==null||userList.size()==0){
            return null;
        }
        for(UserEntity user:userList){
            if(user.getUserName().equals(username)&&user.getPassWord().equals(password)){
                return user;
            }
        }

        return null;
    }
    //检索
    public UserEntity searchUser(String id){
        List<UserEntity> userList = findUserList();
        for(UserEntity user:userList){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    //生成用于用户管理的列表
    public List<UserTable> findUserTableList(){
        String json = DataUtils.readData(DataFileName.USER.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<UserTable> userTableList = JsonUtils.jsonToList(json, UserTable.class);
            return userTableList;
        }
        return null;
    }

    public boolean removeUser(String id) throws IOException {
        List<UserEntity> userList = findUserList();
        for(UserEntity user :userList){
            if (user.getId().equals(id)){
                userList.remove(user);
                DataUtils.deleteData(DataFileName.USER.getFileName());
                for(UserEntity u2:userList){
                    saveUser(u2);
                }
                return true;
            }
        }
        return false;
    }
    //修改用户信息
    public boolean modifyUser(String id, UserEntity userEntity) throws IOException {
        List<UserEntity> userList = findUserList();
        for(UserEntity user :userList){
            if (user.getId().equals(id)){
                user.setUserName(userEntity.getUserName());
                user.setPassWord(userEntity.getPassWord());
                user.setPhone(userEntity.getPhone());
                user.setName(userEntity.getName());
                user.setFacName(userEntity.getFacName());
                user.setPower(userEntity.getPower());
                user.setDiscription(userEntity.getDiscription());
                DataUtils.deleteData(DataFileName.USER.getFileName());
                for(UserEntity u2:userList){
                    saveUser(u2);
                }
                return true;
            }
        }
        return false;
    }

    //生成工厂数据的列表
    public List<FacTable> findFacTableList(){
        String json = DataUtils.readData(DataFileName.USER.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<FacTable> allTableList = JsonUtils.jsonToList(json, FacTable.class);
            for(FacTable ft :allTableList){
                if(ft.getState().equals("启用")){
                    ft.bt.getButton1().setText("关停");
                }else if(ft.getState().equals("关停")) {
                    ft.bt.getButton1().setText("启用");
                }
                ft.bt.getButton1().setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        if(ft.getState().equals("启用")){
                            ft.setState("关停");
                            ft.bt.getButton1().setText("启用");

                        }else if(ft.getState().equals("关停")) {
                            ft.setState("启用");
                            ft.bt.getButton1().setText("关停");
                        }
                        List<UserEntity> userList = findUserList();
                        for(UserEntity user1 :userList){
                            if (user1.getId().equals(ft.getId())){
                                user1.setState(ft.getState());
                                try {
                                    DataUtils.deleteData(DataFileName.USER.getFileName());
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                for(UserEntity u2:userList){
                                    saveUser(u2);
                                }
                                break;
                            }
                        }
                        TableView<FacTable> table = DataTrans.getTable();
                        table.refresh();
                    }
                });
            }
            List<FacTable> facTableList = new ArrayList<FacTable>();
            for(FacTable facTable:allTableList){
                if(facTable.getPower().equals("云工厂")){
                    facTableList.add(facTable);
                }
            }
            return facTableList;
        }
        return null;
    }
}
