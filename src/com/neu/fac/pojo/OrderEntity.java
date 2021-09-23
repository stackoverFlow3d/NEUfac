package com.neu.fac.pojo;

//订单的基本信息包括：、订单编号（自动生成）产品名称、订购数量，交付日期，投标截止日期、收货人、收货人联系方式、收货地址、订单状态。
public class OrderEntity {
    String id;
    String number;
    String productName;
    String amount;
    String finsishDay;
    String deadLine;
    String acpName;
    String acpPhone;
    String acpAddress;
    String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFinsishDay() {
        return finsishDay;
    }

    public void setFinsishDay(String finsishDay) {
        this.finsishDay = finsishDay;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getAcpName() {
        return acpName;
    }

    public void setAcpName(String acpName) {
        this.acpName = acpName;
    }

    public String getAcpPhone() {
        return acpPhone;
    }

    public void setAcpPhone(String acpPhone) {
        this.acpPhone = acpPhone;
    }

    public String getAcpAddress() {
        return acpAddress;
    }

    public void setAcpAddress(String acpAddress) {
        this.acpAddress = acpAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
