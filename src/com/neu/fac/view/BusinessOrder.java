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
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION,"??????????????????",new ButtonType("??????", ButtonBar.ButtonData.YES),new ButtonType("??????", ButtonBar.ButtonData.NO));
        alert2.setTitle("??????");
        alert2.setHeaderText("????????????:");
        alert2.setContentText("??????????????????");
        Optional<ButtonType> _buttonType = alert2.showAndWait();
        if(_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.NO)){
            System.out.println("?????????");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("??????");
        alert.setHeaderText("????????????:");
        boolean flag = false;

        flag = orderController.removeOrder(table.getSelectionModel().getSelectedItem().getId());
        if(flag) {
            alert.setContentText("????????????!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("????????????!");
            alert.showAndWait();
        }
    }

    @FXML
    void releaseOrder(ActionEvent event) throws IOException {
        if(!table.getSelectionModel().getSelectedItem().getStatus().equals("?????????")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("??????");
            alert.setHeaderText("????????????:");
            alert.setContentText("?????????????????????????????????????????????!");
            alert.showAndWait();
            return;
        }
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION,"??????????????????",new ButtonType("??????", ButtonBar.ButtonData.YES),new ButtonType("??????", ButtonBar.ButtonData.NO));
        alert2.setTitle("??????");
        alert2.setHeaderText("????????????:");
        alert2.setContentText("??????????????????");
        Optional<ButtonType> _buttonType = alert2.showAndWait();
        if(_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.NO)){
            System.out.println("?????????");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("??????");
        alert.setHeaderText("????????????:");
        boolean flag = false;

        flag = orderController.release(table.getSelectionModel().getSelectedItem().getId());
        if(flag) {
            alert.setContentText("????????????!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("????????????!");
            alert.showAndWait();
        }

    }
    @FXML
    void detail(ActionEvent event) throws Exception {
        if(!table.getSelectionModel().getSelectedItem().getStatus().equals("?????????")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("??????????????????");
            alert.setHeaderText("????????????:");
            alert.setContentText("??????????????????????????????!");
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

