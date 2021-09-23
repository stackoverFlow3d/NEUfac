package com.neu.fac.dao;

import com.neu.fac.pojo.BidEntity;
import com.neu.fac.pojo.OrderEntity;
import com.neu.fac.pojo.PowerEntity;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.utils.DataFileName;
import com.neu.fac.utils.DataUtils;
import com.neu.fac.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BidDao {
    private static BidDao bidDao = new BidDao();
    private BidDao(){

    }
    public static BidDao getInstance(){
        return bidDao;
    }

    public boolean saveBid(BidEntity bid) {
        String json = JsonUtils.objectToJson(bid);
        try{
            DataUtils.writeData(DataFileName.Bid.getFileName(),json);
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //系统分配一个ID
    private String getMaxId(){
        List<BidEntity> bids = getBidList();
        int max = 0;
        if(bids==null||bids.size()==0){
            return "1";
        }
        for(BidEntity user:bids){
            if(Integer.parseInt(user.getId())>max){
                max = Integer.parseInt(user.getId());
            }
        }
        return String.valueOf(max+1);
    }
    public List<BidEntity> getBidList() {
        String json = DataUtils.readData(DataFileName.Bid.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<BidEntity> bids = JsonUtils.jsonToList(json, BidEntity.class);
            return bids;
        }
        return null;
    }

    public List<BidEntity> getCurrentBidList(String number) {
        List<BidEntity> bids = getBidList();
        List<BidEntity> cbids = new ArrayList<BidEntity>();
        for(BidEntity bidEntity:bids){
            if(number.equals(bidEntity.getOrder().getNumber())){
                cbids.add(bidEntity);
            }
        }
        return cbids;
    }

    public boolean choiceBid(BidEntity selectedItem) throws IOException {
        List<BidEntity> bids = getBidList();
        for(BidEntity bidEntity:bids){
            if(selectedItem.getOrder().getNumber().equals(bidEntity.getOrder().getNumber())){
                bidEntity.setStatus("是");
                DataUtils.deleteData(DataFileName.Bid.getFileName());
                for (BidEntity bidEntity1:bids){
                    saveBid(bidEntity1);
                }
                return true;
            }
        }
        return false;
    }

    public List<OrderEntity> getChoicedBid(String facName) {
        List<BidEntity> bids = getBidList();
        List<OrderEntity> cbids = new ArrayList<OrderEntity>();
        for(BidEntity bidEntity:bids){
            if(facName.equals(bidEntity.getBidder().getFacName())&&bidEntity.getStatus().equals("是")){
                cbids.add(bidEntity.getOrder());
            }
        }
        return cbids;
    }

    public void updata(String number,String status) throws IOException {
        List<BidEntity> bids = getBidList();
        for(BidEntity bidEntity:bids){
            if(number.equals(bidEntity.getOrder().getNumber())){
                bidEntity.getOrder().setStatus(status);
                DataUtils.deleteData(DataFileName.Bid.getFileName());
                for (BidEntity bidEntity1:bids){
                    saveBid(bidEntity1);
                }
                return;
            }
        }
    }
    public boolean remove(String ordNun) throws IOException {
        List<BidEntity> bids = getBidList();
        for(BidEntity bidEntity:bids){
            if(bidEntity.getOrder().getNumber().equals(ordNun)){
                bids.remove(bidEntity);
            }
            DataUtils.deleteData(DataFileName.Bid.getFileName());
            for(BidEntity u2:bids){
                saveBid(u2);
            }
            return true;
        }
        return false;
    }

    public boolean hasBid(String facName) {
        List<BidEntity> bids = getBidList();
        for(BidEntity bidEntity:bids){
            if(bidEntity.getBidder().getFacName().equals(facName)){
                return true;
            }
        }
        return false;
    }
}
