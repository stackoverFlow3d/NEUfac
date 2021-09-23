package com.neu.fac.view;

import com.neu.fac.controller.BidController;
import com.neu.fac.controller.OrderController;
import com.neu.fac.pojo.BidEntity;
import com.neu.fac.pojo.OrderEntity;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.utils.DataTrans;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoiceBid {
    private BidController bidController = BidController.getInstance();
    private OrderController orderController = OrderController.getInstance();
    @FXML
    private TableColumn<BidEntity, String> statusLine;

    @FXML
    private TableColumn<BidEntity, String> facLine;

    @FXML
    private TableColumn<BidEntity, String> phoneLine;

    @FXML
    private TableColumn<BidEntity, String> priceLine;

    @FXML
    private TableColumn<BidEntity, String> idLine;

    @FXML
    private TableView<BidEntity> table;

    @FXML
    private TableColumn<BidEntity, String> nameLine;

    @FXML
    void Choice(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("选标");
        alert.setHeaderText("温馨提示:");
        boolean flag = bidController.choiceBid(table.getSelectionModel().getSelectedItem());
        if (flag) {
            orderController.choiced(table.getSelectionModel().getSelectedItem().getOrder().getNumber());
            bidController.updata(table.getSelectionModel().getSelectedItem().getOrder().getNumber(),"投标结束");
            alert.setContentText("选标成功!");
            alert.showAndWait();
            Stage stage = (Stage) table.getScene().getWindow();
            stage.close();
        } else {
            alert.setContentText("选标失败,请稍后再试.");
            alert.showAndWait();
        }
    }
    public void initialize() {
        ObservableList<BidEntity> data = FXCollections.observableList(bidController.getCurrentBidList(DataTrans.getOrderEntity().getNumber()));
        idLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBidder().getName()));
        statusLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()) );
        phoneLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBidder().getPhone()) );
        facLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBidder().getFacName()) );
        priceLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrice()) );
        table.setItems(data);
    }
}

