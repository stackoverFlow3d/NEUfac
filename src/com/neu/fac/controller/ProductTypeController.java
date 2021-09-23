package com.neu.fac.controller;

import com.neu.fac.dao.ProductTypeDao;
import com.neu.fac.pojo.ProductTypeEntity;

import java.io.IOException;
import java.util.List;

public class ProductTypeController {
    private final ProductTypeDao productTypeDao = ProductTypeDao.getInstance();
    private static ProductTypeController productTypeController = new ProductTypeController();
    private ProductTypeController(){

    }
    public static ProductTypeController getInstance() {
        return productTypeController;
    }
    //新建
    public boolean newProductType(ProductTypeEntity productType){
        return productTypeDao.saveProductType(productType);
    }
    //删除
    public boolean removeProductType(String id) throws IOException {
        return productTypeDao.removeProductType(id);
    }
    //修改
    public boolean modifyProducttype(String id,ProductTypeEntity productTypeEntity) throws IOException {
        return productTypeDao.modifyProducttype(id,productTypeEntity);
    }
    //检索
    public ProductTypeEntity searchproductType(String type){
        if(type.equals("")){
            return new ProductTypeEntity();
        }
        return productTypeDao.searchproductType(type);
    }

    public List<ProductTypeEntity> getProductTpyeList(){
        return productTypeDao.findProductTpyeList();
    }

    public boolean addType(ProductTypeEntity productTypeEntity) {
        return productTypeDao.saveProductType(productTypeEntity);
    }
    //获得所有种类的列表
    public List<String> getTpyeStringList() {
        return productTypeDao.getTpyeStringList();
    }
}
