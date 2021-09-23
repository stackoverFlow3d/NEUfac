package com.neu.fac.view;

import com.neu.fac.controller.OrderController;
import com.neu.fac.controller.ProductController;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.pojo.OrderEntity;
import com.neu.fac.pojo.ProductEntity;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.utils.DataCheckUtils;
import com.neu.fac.utils.DataTrans;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class AddOrder {
    private OrderController orderController = OrderController.getInstance();
    private ProductController productController = ProductController.getInstance();
    private UserEntity user = new UserEntity();
    @FXML
    private Button cancelButton;

    @FXML
    private TextField addressPick;

    @FXML
    private TextField amountText;

    @FXML
    private ChoiceBox<String> productChoice;

    @FXML
    private Button confrimButton;

    @FXML
    private DatePicker deadlinePick;

    @FXML
    private DatePicker finsishPick;

    @FXML
    void confrim(ActionEvent event) {
        OrderEntity order = new OrderEntity();
        order.setProductName(productChoice.getSelectionModel().getSelectedItem());
        order.setAmount(amountText.getText());
        order.setStatus("已保存");
        order.setFinsishDay(finsishPick.getValue().toString());
        order.setDeadLine(deadlinePick.getValue().toString());
        order.setAcpPhone(user.getPhone());
        order.setAcpName(user.getName());
        order.setNumber(getNum());
        order.setAcpAddress(addressPick.getText());

        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("创建订单");
        alert.setHeaderText("温馨提示:");
        //信息校验判断
        String message = DataCheckUtils.validateData(order);
        if (message == null) {
            boolean flag = orderController.newOrder(order);
            if (flag) {
                alert.setContentText("创建成功!");
                alert.showAndWait();
                Stage stage = (Stage) confrimButton.getScene().getWindow();
                stage.close();
            } else {
                alert.setContentText("创建失败,请稍后再试.");
                alert.showAndWait();
            }
        } else {
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

    private String getNum() {
        List<OrderEntity> orderEntities = orderController.getorderList();
        String s = "HSO139723";
        int max = 1001;
        if(orderEntities==null||orderEntities.size()==0){
            return s + "1001";
        }
        for(OrderEntity orderEntity:orderEntities){
            String result = orderEntity.getNumber().substring(orderEntity.getNumber().length()-4,orderEntity.getNumber().length());
            if(Integer.parseInt(result)>max){
                max = Integer.parseInt(result);
            }
        }
        return s + String.valueOf(max + 1);
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void initialize(){
        user = DataTrans.getUserDate();
        ObservableList<String> data = FXCollections.observableList(productController.getProductStringList());
        productChoice.setItems(data);
    }
}

