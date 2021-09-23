package com.neu.fac.controller;

import com.neu.fac.dao.BidDao;
import com.neu.fac.pojo.BidEntity;
import com.neu.fac.pojo.OrderEntity;

import java.io.IOException;
import java.util.List;

public class BidController {
    private static BidController bidController = new BidController();
    private BidController(){

    }
    private final BidDao bidDao = BidDao.getInstance();
    public static BidController getInstance(){
        return bidController;
    }

    public boolean newBid(BidEntity bid) {
        return bidDao.saveBid(bid);
    }

    public List<BidEntity> getBidList() {
        return bidDao.getBidList();
    }

    public List<BidEntity> getCurrentBidList(String number) {
        return bidDao.getCurrentBidList(number);
    }

    public boolean choiceBid(BidEntity selectedItem) throws IOException {
        return bidDao.choiceBid(selectedItem);
    }

    public List<OrderEntity> getChoicedBid(String facName) {
        return bidDao.getChoicedBid(facName);
    }

    public void updata(String number,String status) throws IOException {
        bidDao.updata(number,status);
    }
    public boolean remove(String ordNun) throws IOException {
        return bidDao.remove(ordNun);
    }

    public boolean hasBid(String facName) {
        return bidDao.hasBid(facName);
    }
}
