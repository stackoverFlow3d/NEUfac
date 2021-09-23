package com.neu.fac.view.equipment;

import com.neu.fac.controller.EquipmentController;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.scenes.equipment.AddEquipmentView;
import com.neu.fac.scenes.equipment.ModifyEquipView;
import com.neu.fac.utils.DataTrans;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipmentManager {
    private EquipmentController equipmentController = EquipmentController.getInstance();
    private AddEquipmentView addEquipmentView = new AddEquipmentView();
    private ModifyEquipView modifyEquipView = new ModifyEquipView();
    @FXML
    private TableColumn<EquipmentEntity, String> numberLine;

    @FXML
    private TableColumn<EquipmentEntity, String> typeLine;

    @FXML
    private TableColumn<EquipmentEntity, String> sizeLIne;

    @FXML
    private TableColumn<EquipmentEntity, String> browwerLine;

    @FXML
    private TableColumn<EquipmentEntity, String> facLine;
    @FXML
    private Button modifyButtomn;

    @FXML
    private Button checkButton;

    @FXML
    private Button newButtomn;

    @FXML
    private Button controlButton;

    @FXML
    private TableColumn<EquipmentEntity, String> nameLine;

    @FXML
    private Button replaceButton;

    @FXML
    private Button deletButton;

    @FXML
    private TextField checkText;

    @FXML
    private TableColumn<EquipmentEntity, String> statuLine;

    @FXML
    private TableColumn<EquipmentEntity, String> idLine;

    @FXML
    private TableView<EquipmentEntity> table;

    @FXML
    private TableColumn<EquipmentEntity, String> descripLine;

    @FXML
    void newEquip(ActionEvent event) throws Exception {
        Stage add = (Stage) newButtomn.getScene().getWindow();
        addEquipmentView.start(add);
        initialize();
    }

    @FXML
    void delEquip(ActionEvent event) throws IOException {
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
        if(table.getSelectionModel().getSelectedItem().getLeaseStatus().equals("已租用")) {
            alert.setContentText("不能删除已经租用的设备!");
            alert.showAndWait();
            return;
        }
        if(table.getSelectionModel().getSelectedItem().getLeaseStatus().equals("工厂设备")) {
            alert.setContentText("不能删除工厂的设备!");
            alert.showAndWait();
            return;
        }
        boolean flag = false;

        flag = equipmentController.removeEquipment(table.getSelectionModel().getSelectedItem().getId());
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
    void DIstanceControll(ActionEvent event) throws IOException {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION,"确认操作吗？",new ButtonType("确定", ButtonBar.ButtonData.YES),new ButtonType("取消", ButtonBar.ButtonData.NO));
        alert2.setTitle("确认");
        alert2.setHeaderText("温馨提示:");
        alert2.setContentText("确认操作吗？");
        Optional<ButtonType> _buttonType = alert2.showAndWait();
        if(_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.NO)){
            System.out.println("否定！");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("操作");
        alert.setHeaderText("温馨提示:");
        boolean flag = false;
        flag = equipmentController.distanceController(table.getSelectionModel().getSelectedItem());
        if(flag) {
            alert.setContentText("操作成功!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("操作失败!");
            alert.showAndWait();
        }
    }

    @FXML
    void check(ActionEvent event) {
        String name = checkText.getText();
        EquipmentEntity target = equipmentController.searchEquipment(name);
        List<EquipmentEntity> targets = new ArrayList<EquipmentEntity>();
        targets.add(target);
        ObservableList<EquipmentEntity> chekList = FXCollections.observableList(targets);
        table.setItems(chekList);
    }

    @FXML
    void replace(ActionEvent event) {
        checkText.clear();
        initialize();
    }
    @FXML
    void modifyEquip(ActionEvent event) throws Exception {
        EquipmentEntity equipmentEntity = table.getSelectionModel().getSelectedItem();
        DataTrans.setEquipmentData(equipmentEntity);
        Stage newStage = (Stage) modifyButtomn.getScene().getWindow();
        modifyEquipView.start(newStage);
        initialize();
    }


    public void initialize() {
        ObservableList<EquipmentEntity> data = FXCollections.observableList(equipmentController.getEquipmentList());
        typeLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()) );
        idLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
        descripLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()) );
        nameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()) );
        numberLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumber()) );
        sizeLIne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSize()) );
        browwerLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLeaseStatus()) );
        statuLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEuipmentStatus()) );
        facLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurrentFac()) );
        table.setItems(data);
    }

}

