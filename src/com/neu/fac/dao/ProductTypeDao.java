package com.neu.fac.dao;

import com.neu.fac.pojo.ProductTypeEntity;
import com.neu.fac.utils.DataFileName;
import com.neu.fac.utils.DataUtils;
import com.neu.fac.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDao {
    private static ProductTypeDao productTypeDao = new ProductTypeDao();
    private ProductTypeDao(){

    }
    public static ProductTypeDao getInstance() {
        return productTypeDao;
    }
    //保存用户信息
    public boolean saveProductType(ProductTypeEntity productTypeEntity){
        productTypeEntity.setId(getMaxId());
        String json = JsonUtils.objectToJson(productTypeEntity);
        try{
            DataUtils.writeData(DataFileName.ProductType.getFileName(),json);
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //从文件读取用户信息存储至列表
    public List<ProductTypeEntity> findProductTpyeList(){
        String json = DataUtils.readData(DataFileName.ProductType.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<ProductTypeEntity> productTypesListEntity = JsonUtils.jsonToList(json, ProductTypeEntity.class);
            return productTypesListEntity;
        }
        return null;
    }
    //系统分配一个ID
    private String getMaxId(){
        List<ProductTypeEntity> productTypeEntityList = findProductTpyeList();
        int max = 0;
        if(productTypeEntityList ==null|| productTypeEntityList.size()==0){
            return "1";
        }
        for(ProductTypeEntity productTypeEntity : productTypeEntityList){
            if(Integer.parseInt(productTypeEntity.getId())>max){
                max = Integer.parseInt(productTypeEntity.getId());
            }
        }
        return String.valueOf(max+1);
    }

    public boolean removeProductType(String id) throws IOException {
        List<ProductTypeEntity> productTypeList = findProductTpyeList();
        for(ProductTypeEntity productType :productTypeList){
            if (productType.getId().equals(id)){
                productTypeList.remove(productType);
                DataUtils.deleteData(DataFileName.ProductType.getFileName());
                for(ProductTypeEntity u2:productTypeList){
                    saveProductType(u2);
                }
                return true;
            }
        }
        return false;
    }

    public ProductTypeEntity searchproductType(String type) {
        List<ProductTypeEntity> productTypeEntities = productTypeDao.findProductTpyeList();
        for (ProductTypeEntity productTypeEntity:productTypeEntities){
            if(productTypeEntity.getType().equals(type)){
                return  productTypeEntity;
            }
        }
        return null;
    }

    public boolean modifyProducttype(String id, ProductTypeEntity productTypeEntity) throws IOException {
        List<ProductTypeEntity> productTypeEntities = productTypeDao.findProductTpyeList();
        for (ProductTypeEntity p1:productTypeEntities){
            if(p1.getId().equals(id)){
                p1.setType(productTypeEntity.getType());
                DataUtils.deleteData(DataFileName.ProductType.getFileName());
                for(ProductTypeEntity u2:productTypeEntities){
                    saveProductType(u2);
                }
                return true;
            }
        }
        return false;
    }

    public List<String> getTpyeStringList() {
        List<ProductTypeEntity> productTypeList = findProductTpyeList();
        List<String> tpyeStringList = new ArrayList<String>();
        for(ProductTypeEntity productType :productTypeList){
            tpyeStringList.add(productType.getType());
        }
        return tpyeStringList;
    }
}
