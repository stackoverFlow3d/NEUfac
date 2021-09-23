package com.neu.fac.dao;

import com.neu.fac.pojo.ProductEntity;
import com.neu.fac.pojo.ProductTypeEntity;
import com.neu.fac.utils.DataFileName;
import com.neu.fac.utils.DataUtils;
import com.neu.fac.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static ProductDao productDao = new ProductDao();
    private ProductDao(){

    }
    public static ProductDao getInstance() {
        return productDao;
    }

    //保存用户信息
    public boolean saveProduct(ProductEntity productEntity){
        productEntity.setId(getMaxId());
        String json = JsonUtils.objectToJson(productEntity);
        try{
            DataUtils.writeData(DataFileName.Product.getFileName(),json);
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //从文件读取信息存储至列表
    public List<ProductEntity> findProductList(){
        String json = DataUtils.readData(DataFileName.Product.getFileName());
        if(json!=null){
            json = json.replace('/',',');
            json = "["+json+"]";
            List<ProductEntity> productList = JsonUtils.jsonToList(json, ProductEntity.class);
            return productList;
        }
        return null;
    }
    //系统分配一个ID
    private String getMaxId(){
        List<ProductEntity> productList = findProductList();
        int max = 0;
        if(productList==null||productList.size()==0){
            return "1";
        }
        for(ProductEntity product:productList){
            if(Integer.parseInt(product.getId())>max){
                max = Integer.parseInt(product.getId());
            }
        }
        return String.valueOf(max+1);
    }
    public ProductEntity searchProduct(String name) {
        List<ProductEntity> product = productDao.findProductList();
        for (ProductEntity productEntity:product){
            if(productEntity.getName().equals(name)){
                return  productEntity;
            }
        }
        return null;
    }

    public boolean removeProduct(String id) throws IOException {
        List<ProductEntity> productList = findProductList();
        for(ProductEntity productType :productList){
            if (productType.getId().equals(id)){
                productList.remove(productType);
                DataUtils.deleteData(DataFileName.Product.getFileName());
                for(ProductEntity u2:productList){
                    saveProduct(u2);
                }
                return true;
            }
        }
        return false;
    }

    public boolean modifyProduct(String id, ProductEntity productEntity) throws IOException {
        List<ProductEntity> productEntities = productDao.findProductList();
        for (ProductEntity p1:productEntities){
            if(p1.getId().equals(id)){
                p1.setSize(productEntity.getSize());
                p1.setDescription(productEntity.getDescription());
                p1.setName(productEntity.getName());
                p1.setType(productEntity.getType());
                DataUtils.deleteData(DataFileName.Product.getFileName());
                for(ProductEntity u2:productEntities){
                    saveProduct(u2);
                }
                return true;
            }
        }
        return false;
    }

    public List<String> getProductStringList() {
        List<ProductEntity> productEntities = productDao.findProductList();
        List<String> productStringList = new ArrayList<String>();
        for(ProductEntity product:productEntities){
            productStringList.add(product.getName());
        }
        return productStringList;
    }

    public void updatePro(String old,String newType) throws IOException {
        List<ProductEntity> productEntities = productDao.findProductList();
        for(ProductEntity product:productEntities){
            if (product.getType().equals(old)){
                product.setType(newType);
            }
        }
        DataUtils.deleteData(DataFileName.Product.getFileName());
        for(ProductEntity u2:productEntities){
            saveProduct(u2);
        }
    }
}
