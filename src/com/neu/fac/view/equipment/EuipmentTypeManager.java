package com.neu.fac.view.equipment;

import com.neu.fac.controller.EquipmentController;
import com.neu.fac.controller.EquipmentTypeController;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.pojo.EquipmentTypeEntity;
import com.neu.fac.scenes.equipment.AddEquipTypeView;
import com.neu.fac.scenes.equipment.ModifyEquipTypeView;
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

public class EuipmentTypeManager {
    private EquipmentTypeController equipmentTypeController = EquipmentTypeController.getInstance();
    private AddEquipTypeView addEquipTypeView = new AddEquipTypeView();
    private ModifyEquipTypeView modifyEquipTypeView = new ModifyEquipTypeView();
    private EquipmentController equipmentController = EquipmentController.getInstance();
    @FXML
    private TableColumn<EquipmentTypeEntity, String> typeLine;
    @FXML
    private TableColumn<EquipmentTypeEntity, String> idLine;
    @FXML
    private Button chexkButton;

    @FXML
    private Button replaceButton;

    @FXML
    private Button deletButton;

    @FXML
    private Button modfiyButton;

    @FXML
    private TextField typeText;

    @FXML
    private Button newButton;


    @FXML
    private TableView<EquipmentTypeEntity> table;

    @FXML
    void check(ActionEvent event) {
        String type = typeText.getText();
        EquipmentTypeEntity target = equipmentTypeController.searchEquipmentType(type);
        List<EquipmentTypeEntity> targets = new ArrayList<EquipmentTypeEntity>();
        targets.add(target);
        ObservableList<EquipmentTypeEntity> chekList = FXCollections.observableList(targets);
        table.setItems(chekList);
    }

    @FXML
    void replace(ActionEvent event) {
        typeText.clear();
        initialize();
    }

    @FXML
    void newType(ActionEvent event) throws Exception {
        Stage add = (Stage) newButton.getScene().getWindow();
        addEquipTypeView.start(add);
        initialize();
    }

    @FXML
    void modifyType(ActionEvent event) throws Exception {
        DataTrans.setEquipmentTypeEntity(table.getSelectionModel().getSelectedItem());
        Stage mo = (Stage) newButton.getScene().getWindow();
        modifyEquipTypeView.start(mo);
        initialize();
    }

    @FXML
    void deletType(ActionEvent event) throws IOException {
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
        List<EquipmentEntity> equipmentEntities = equipmentController.getEquipmentList();
        for (EquipmentEntity equipmentEntity:equipmentEntities){
            if(equipmentEntity.getType().equals(table.getSelectionModel().getSelectedItem().getType())){
                alert.setContentText("添加失败,请稍后再试.");
                alert.showAndWait();
                return;
            }
        }
        boolean flag = false;

        flag = equipmentTypeController.removeType(table.getSelectionModel().getSelectedItem().getId());
        if(flag) {
            alert.setContentText("删除成功!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("删除失败!");
            alert.showAndWait();
        }
    }

    public void initialize() {
        ObservableList<EquipmentTypeEntity> data = FXCollections.observableList(equipmentTypeController.getEquipmentTpyeList());
        typeLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()) );
        idLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
        table.setItems(data);
    }

}
