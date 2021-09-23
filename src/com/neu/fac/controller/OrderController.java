package com.neu.fac.controller;

import com.neu.fac.dao.OrderDao;
import com.neu.fac.pojo.OrderEntity;

import java.io.IOException;
import java.util.List;

public class OrderController {
    private final OrderDao orderDao = OrderDao.getInstance();
    private static OrderController orderController = new OrderController();
    public static OrderController getInstance() {
        return orderController;
    }
    private OrderController(){

    }
    public List<OrderEntity> getorderList(){
        return orderDao.findOrderList();
    }
    //新建
    public boolean newOrder(OrderEntity product){
        return orderDao.saveOrder(product);
    }
    //删除
    public boolean removeOrder(String id) throws IOException {
        return orderDao.removeOrder(id);
    }
    //修改
    public boolean modifyOrder(String id,OrderEntity productEntity) throws IOException {
        return orderDao.modifyOrder(id,productEntity);
    }
    //发布订单
    public boolean release(String id) throws IOException {
        return orderDao.release(id);
    }

    public List<OrderEntity> getBid(){
        return orderDao.getBid();
    }
    //选标
    public void choiced(String number) throws IOException {
        orderDao.choiced(number);
    }

    public void updata(String number, String status) throws IOException {
        orderDao.update(number,status);
    }
}
