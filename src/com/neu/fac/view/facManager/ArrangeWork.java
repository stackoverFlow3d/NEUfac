package com.neu.fac.view.facManager;
import com.neu.fac.controller.EquipmentController;
import com.neu.fac.controller.WorkController;
import com.neu.fac.pojo.BidEntity;
import com.neu.fac.pojo.OrderEntity;
import com.neu.fac.pojo.PowerEntity;
import com.neu.fac.pojo.WorkEntity;
import com.neu.fac.utils.DataCheckUtils;
import com.neu.fac.utils.DataTrans;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class ArrangeWork {
    private WorkController workController = WorkController.getInstance();
    private EquipmentController equipmentController = EquipmentController.getInstance();
    @FXML
    private ChoiceBox<String> eName;

    @FXML
    private DatePicker aDate;

    @FXML
    private DatePicker fDate;

    @FXML
    private TableColumn<WorkEntity, String> fLine;

    @FXML
    private TableView<WorkEntity> table;

    @FXML
    private TableColumn<WorkEntity, String> nameLine;

    @FXML
    private TableColumn<WorkEntity, String> aLine;

    @FXML
    void addE(ActionEvent event) {
        WorkEntity workEntity = new WorkEntity();
        workEntity.setEuqipName(eName.getValue());
        workEntity.setOrdernum(DataTrans.getOrderEntity().getNumber());
        workEntity.setFinishDate(fDate.getValue().toString());
        workEntity.setStartDate(aDate.getValue().toString());

        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("添加生产设备");
        alert.setHeaderText("温馨提示:");
        //信息校验判断
        String message = DataCheckUtils.validateData(workEntity);
        if (message == null) {
            boolean flag = workController.addWork(workEntity);
            if (flag) {

                alert.setContentText("添加成功!");
                alert.showAndWait();
            } else {
                alert.setContentText("添加失败,请稍后再试.");
                alert.showAndWait();
            }
        } else {
            alert.setContentText(message);
            alert.showAndWait();
        }
        initialize();
    }

    @FXML
    void delE(ActionEvent event) throws IOException {
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
        ObservableList<WorkEntity> list=table.getItems();
        boolean flage = workController.remove(table.getSelectionModel().getSelectedItem().getEuqipName());
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
        ObservableList<WorkEntity> data = FXCollections.observableList(workController.findCurrentWorkList(DataTrans.getOrderEntity().getNumber()));
        nameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEuqipName()) );
        aLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartDate()) );
        fLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFinishDate()) );
        ObservableList<String> data1 = FXCollections.observableList(equipmentController.getEquipStringList(DataTrans.getUserDate().getFacName()));
        eName.setItems(data1);
        table.setItems(data);
    }
}

