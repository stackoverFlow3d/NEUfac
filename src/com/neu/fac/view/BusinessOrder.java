package com.neu.fac.view;

import com.neu.fac.controller.OrderController;
import com.neu.fac.pojo.OrderEntity;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.scenes.AddOrderView;
import com.neu.fac.scenes.ChoiceBidView;
import com.neu.fac.scenes.ModifyOrderView;
import com.neu.fac.utils.DataTrans;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class BusinessOrder {
    private OrderController orderController = OrderController.getInstance();
    private AddOrderView addOrderView = new AddOrderView();
    private ModifyOrderView modifyOrderView = new ModifyOrderView();
    private ChoiceBidView choiceBidView = new ChoiceBidView();
    @FXML
    private TableColumn<OrderEntity, String> numLine;

    @FXML
    private TableColumn<OrderEntity, String> amountLine;

    @FXML
    private TableColumn<OrderEntity, String> statusLine;

    @FXML
    private TableColumn<OrderEntity, String> phoneLine;

    @FXML
    private Button delButton;

    @FXML
    private TableColumn<OrderEntity, String> adressLine;

    @FXML
    private Button releaseButton;

    @FXML
    private TableColumn<OrderEntity, String> nameLine;

    @FXML
    private TableColumn<OrderEntity, String> deadLine;

    @FXML
    private Button modifyButton;


    @FXML
    private Button detailButton;

    @FXML
    private TableColumn<OrderEntity, String> accLine;

    @FXML
    private Button newButton;

    @FXML
    private TableColumn<OrderEntity, String> finsishLine;

    @FXML
    private TableColumn<OrderEntity, String> idLine;

    @FXML
    private TableView<OrderEntity> table;

    @FXML
    void addOrder(ActionEvent event) throws Exception {
        Stage newStage = (Stage) newButton.getScene().getWindow();
        addOrderView.start(newStage);
        initialize();
    }

    @FXML
    void modify(ActionEvent event) throws Exception {
        OrderEntity orderEntity = table.getSelectionModel().getSelectedItem();
        DataTrans.setOrderEntity(orderEntity);
        Stage newStage = (Stage) modifyButton.getScene().getWindow();
        modifyOrderView.start(newStage);
        initialize();
    }

    @FXML
    void delOrder(ActionEvent event) throws IOException {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION,"确认删除吗？",new ButtonType("确定", ButtonBar.ButtonData.YES),new ButtonType("取消", ButtonBar.ButtonData.NO));
        alert2.setTitle("确认");
        alert2.setHeaderText("温馨提示:");
        alert2.setContentText("确认删除吗？");
        Optional<ButtonType> _buttonType = alert2.showAndWait();
        if(_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.NO)){
            System.out.println("否定！");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("删除");
        alert.setHeaderText("温馨提示:");
        boolean flag = false;

        flag = orderController.removeOrder(table.getSelectionModel().getSelectedItem().getId());
        if(flag) {
            alert.setContentText("删除成功!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("删除失败!");
            alert.showAndWait();
        }
    }

    @FXML
    void releaseOrder(ActionEvent event) throws IOException {
        if(!table.getSelectionModel().getSelectedItem().getStatus().equals("已保存")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("删除");
            alert.setHeaderText("温馨提示:");
            alert.setContentText("只有状态为已保存的订单才能发布!");
            alert.showAndWait();
            return;
        }
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION,"确认发布吗？",new ButtonType("确定", ButtonBar.ButtonData.YES),new ButtonType("取消", ButtonBar.ButtonData.NO));
        alert2.setTitle("确认");
        alert2.setHeaderText("温馨提示:");
        alert2.setContentText("确认发布吗？");
        Optional<ButtonType> _buttonType = alert2.showAndWait();
        if(_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.NO)){
            System.out.println("否定！");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("删除");
        alert.setHeaderText("温馨提示:");
        boolean flag = false;

        flag = orderController.release(table.getSelectionModel().getSelectedItem().getId());
        if(flag) {
            alert.setContentText("发布成功!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("发布失败!");
            alert.showAndWait();
        }

    }
    @FXML
    void detail(ActionEvent event) throws Exception {
        if(!table.getSelectionModel().getSelectedItem().getStatus().equals("已发布")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("查看投标详情");
            alert.setHeaderText("温馨提示:");
            alert.setContentText("只能查看已发布的订单!");
            alert.showAndWait();
            return;
        }
        DataTrans.setOrderEntity(table.getSelectionModel().getSelectedItem());
        Stage newStage = (Stage) detailButton.getScene().getWindow();
        choiceBidView.start(newStage);
        initialize();

    }


    public void initialize() {
        UserEntity user = DataTrans.getUserDate();
        ObservableList<OrderEntity> data = FXCollections.observableList(orderController.getorderList());
        amountLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAmount()) );
        idLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
        deadLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeadLine().toString()) );
        nameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()) );
        finsishLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFinsishDay().toString()) );
        accLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcpName()) );
        adressLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcpAddress()) );
        phoneLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcpPhone()) );
        statusLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()) );
        numLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumber()) );
        table.setItems(data);
    }

}

