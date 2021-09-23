package com.neu.fac.utils;

import com.neu.fac.pojo.*;

import java.util.regex.Pattern;

public class DataCheckUtils {
        //构造正则表达式
        private static final String USERNAMEPATTERN = "^[a-z|A-Z|0-9|_]{3,20}$";
        private static final String PASSWORDPATTERN= "^\\d{3,12}$";
        private static final String PHONEPATTERN = "^\\d{11}$";
        //校验信息
        public static String validateData(UserEntity user){

            if("".equals(user.getUserName())){
                return "用户名不能为空";
            }
            if("".equals(user.getPassWord())){
                return "密码不能为空";
            }
            if("".equals(user.getPower())){
                return "注册类型必须选择";
            }
            if(!Pattern.matches(USERNAMEPATTERN,user.getUserName())){
                return "登录名应该为3-20位合法字符.(合法字符包括：a-z,A-Z,0-9,_)";
            }
            if(!Pattern.matches(PASSWORDPATTERN,user.getPassWord()))
            {
                return "密码应该为3-12位的数字.";
            }
            if(!"".equals(user.getPhone())){
                if(!Pattern.matches(PHONEPATTERN,user.getPhone())){
                    return "联系方式应为11位的数字";
                }
            }
            return null;
        }

    public static String validateData(ProductTypeEntity productType){
        if("".equals(productType.getType())){
            return "类型不能为空";
        }
        return null;
    }

    public static String validateData(ProductEntity product){
        if("".equals(product.getName())){
            return "产品名不能为空";
        }
        if("".equals(product.getSize())){
            return "规格不能为空";
        }

        if("".equals(product.getDescription())){
            return "描述不能为空";
        }
        return null;
    }

    public static String validateData(EquipmentTypeEntity equipmentTypeEntity){
        if("".equals(equipmentTypeEntity.getType())){
            return "类型不能为空";
        }
        return null;
    }

    public static String validateData(EquipmentEntity equipment){
        if("".equals(equipment.getName())){
            return "产品名不能为空";
        }
        if("".equals(equipment.getSize())){
            return "规格不能为空";
        }

        if("".equals(equipment.getDescription())){
            return "描述不能为空";
        }
        if("".equals(equipment.getFactory())){
            return "设备类型不能为空";
        }
        return null;
    }
    public static String validateData(OrderEntity order){
        if("".equals(order.getProductName())){
            return "产品名不能为空";
        }
        if("".equals(order.getAmount())||"0".equals(order.getAmount())){
            return "数量不能为零";
        }

        if("".equals(order.getDeadLine())){
            return "投标截止日期不能为空";
        }
        if("".equals(order.getFinsishDay())){
            return "完成日期不能为空";
        }
        if("".equals(order.getAcpAddress())){
            return "地址不能为空";
        }
        return null;
    }

    public static String validateData(BidEntity bid) {
        if("".equals(bid.getPrice())){
            return "价格不能为空";
        }
        return null;
    }

    public static String validateData(WorkEntity workEntity) {
            return null;
    }

    public static String validateData(PowerEntity powerEntity) {
            return null;
    }
}
