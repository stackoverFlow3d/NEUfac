package com.neu.fac.controller;

import com.neu.fac.dao.ProductDao;
import com.neu.fac.dao.ProductTypeDao;
import com.neu.fac.pojo.ProductEntity;
import com.neu.fac.pojo.ProductTypeEntity;

import java.io.IOException;
import java.util.List;

public class ProductController {
    private final ProductDao productDao = ProductDao.getInstance();
    private static ProductController productController = new ProductController();
    private ProductController(){

    }
    public static ProductController getInstance() {
        return productController;
    }
    //新建
    public boolean newProduct(ProductEntity product){
        return productDao.saveProduct(product);
    }
    //删除
    public boolean removeProduct(String id) throws IOException {
        return productDao.removeProduct(id);
    }
    //修改
    public boolean modifyProduct(String id,ProductEntity productEntity) throws IOException {
        return productDao.modifyProduct(id,productEntity);
    }
    //检索
    public ProductEntity searchProduct(String product){
        if(product.equals("")){

            return new ProductEntity();
        }
        return productDao.searchProduct(product);
    }

    public List<ProductEntity> getProductList(){
        return productDao.findProductList();
    }

    public boolean addProduct(ProductEntity product) {
        return productDao.saveProduct(product);
    }


    public List<String> getProductStringList() {
        return productDao.getProductStringList();
    }
    public void update(String old,String newType) throws IOException {
        productDao.updatePro(old,newType);
    }
}
