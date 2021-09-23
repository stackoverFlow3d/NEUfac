package com.neu.fac.view.facManager;
import com.neu.fac.controller.EquipmentController;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.scenes.equipment.AddEquipmentView;
import com.neu.fac.utils.DataTrans;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BorrowEquip {
    private EquipmentController equipmentController = EquipmentController.getInstance();
    private AddEquipmentView addEquipmentView = new AddEquipmentView();
    @FXML
    private TableColumn<EquipmentEntity, String> numberLine;

    @FXML
    private TableColumn<EquipmentEntity, String> typeLine;

    @FXML
    private TableColumn<EquipmentEntity, String> sizeLIne;

    @FXML
    private Button checkButton;

    @FXML
    private Button borrowButton;

    @FXML
    private TableColumn<EquipmentEntity, String> nameLine;

    @FXML
    private Button replaceButton;

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
    void Borrow() throws IOException {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION,"确认租借吗？",new ButtonType("确定", ButtonBar.ButtonData.YES),new ButtonType("取消", ButtonBar.ButtonData.NO));
        alert2.setTitle("确认");
        alert2.setHeaderText("温馨提示:");
        alert2.setContentText("确认租借吗？");
        Optional<ButtonType> _buttonType = alert2.showAndWait();
        if(_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.NO)){
            System.out.println("否定！");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("租借");
        alert.setHeaderText("温馨提示:");
        boolean flag = false;

        flag = equipmentController.borrowEquipment(table.getSelectionModel().getSelectedItem(),DataTrans.getUserDate());
        if(flag) {
            alert.setContentText("租借成功!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("租借失败!");
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

    public void initialize() {
        ObservableList<EquipmentEntity> data = FXCollections.observableList(equipmentController.findBorrwList(DataTrans.getUserDate()));
        typeLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()) );
        idLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
        descripLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()) );
        nameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()) );
        numberLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumber()) );
        sizeLIne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSize()) );
        statuLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEuipmentStatus()) );
        table.setItems(data);
    }

}
