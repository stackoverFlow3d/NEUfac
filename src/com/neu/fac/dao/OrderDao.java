package com.neu.fac.dao;

import com.neu.fac.pojo.OrderEntity;
import com.neu.fac.utils.DataFileName;
import com.neu.fac.utils.DataUtils;
import com.neu.fac.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static OrderDao orderDao = new OrderDao();

    public static OrderDao getInstance() {
        return orderDao;
    }
    private OrderDao(){

    }
    public boolean saveOrder(OrderEntity order){
        order.setId(getMaxId());
        String json = JsonUtils.objectToJson(order);
        try{
            DataUtils.writeData(DataFileName.Order.getFileName(),json);
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //系统分配一个ID
    private String getMaxId(){
        List<OrderEntity> orderList = findOrderList();
        int max = 0;
        if(orderList==null||orderList.size()==0){
            return "1";
        }
        for(OrderEntity order:orderList){
            if(Integer.parseInt(order.getId())>max){
                max = Integer.parseInt(order.getId());
            }
        }
        return String.valueOf(max+1);
    }

    public List<OrderEntity> findOrderList(){
        String json = DataUtils.readData(DataFileName.Order.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<OrderEntity> orderList = JsonUtils.jsonToList(json, OrderEntity.class);
            return orderList;
        }
        return null;
    }

    public boolean removeOrder(String id) throws IOException {
        List<OrderEntity> orders = orderDao.findOrderList();
        for(OrderEntity order :orders){
            if (order.getId().equals(id)){
                orders.remove(order);
                DataUtils.deleteData(DataFileName.Order.getFileName());
                for(OrderEntity u2:orders){
                    saveOrder(u2);
                }
                return true;
            }
        }
        return false;
    }

    public boolean modifyOrder(String id, OrderEntity productEntity) throws IOException {
        List<OrderEntity> orders = orderDao.findOrderList();
        for(OrderEntity order :orders){
            if (order.getId().equals(id)){
                order.setAcpAddress(productEntity.getAcpAddress());
                order.setAcpName(productEntity.getAcpName());
                order.setAcpPhone(productEntity.getAcpPhone());
                order.setAmount(productEntity.getAmount());
                order.setDeadLine(productEntity.getDeadLine());
                order.setFinsishDay(productEntity.getFinsishDay());
                order.setStatus(productEntity.getStatus());
                order.setProductName(productEntity.getProductName());
                DataUtils.deleteData(DataFileName.Order.getFileName());
                for(OrderEntity u2:orders){
                    saveOrder(u2);
                }
                return true;
            }
        }
        return false;
    }

    public boolean release(String id) throws IOException {
        List<OrderEntity> orders = orderDao.findOrderList();
        for(OrderEntity order :orders){
            if(id.equals(order.getId())){
                if(order.getStatus().equals("已保存")){
                    order.setStatus("已发布");
                    DataUtils.deleteData(DataFileName.Order.getFileName());
                    for(OrderEntity u2:orders){
                        saveOrder(u2);
                    }
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public List<OrderEntity> getBid() {
        List<OrderEntity> orders = orderDao.findOrderList();
        List<OrderEntity> bids = new ArrayList<OrderEntity>();
        for(OrderEntity order :orders){
            if(order.getStatus().equals("已发布")){
                bids.add(order);
            }
        }
        return bids;
    }

    public void choiced(String number) throws IOException {
        List<OrderEntity> orders = orderDao.findOrderList();
        for(OrderEntity order :orders){
            if(number.equals(order.getNumber())){
                if(order.getStatus().equals("已发布")){
                    order.setStatus("投标结束");
                    DataUtils.deleteData(DataFileName.Order.getFileName());
                    for(OrderEntity u2:orders){
                        saveOrder(u2);
                    }
                }
            }
        }
    }

    public void update(String number,String status) throws IOException {
        List<OrderEntity> orders = orderDao.findOrderList();
        for(OrderEntity order :orders){
            if(number.equals(order.getNumber())){
                    order.setStatus(status);
                    DataUtils.deleteData(DataFileName.Order.getFileName());
                    for(OrderEntity u2:orders){
                        saveOrder(u2);
                    }
            }
        }
    }
}
