package com.neu.fac.view.equipment;

import com.neu.fac.controller.EquipmentController;
import com.neu.fac.controller.EquipmentTypeController;
import com.neu.fac.pojo.EquipmentTypeEntity;
import com.neu.fac.utils.DataCheckUtils;
import com.neu.fac.utils.DataTrans;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyEuqipType {
    private EquipmentTypeController equipmentTypeController = EquipmentTypeController.getInstance();
    private EquipmentTypeEntity equipmentTypeEntity = DataTrans.getEquipmentTypeEntity();
    private EquipmentController equipmentController = EquipmentController.getInstance();
    private String old;
    private String newType;
    @FXML
    private TextField typrText;

    @FXML
    private Button okk;

    @FXML
    private Button noo;

    @FXML
    void yes(ActionEvent event) throws IOException {
        equipmentTypeEntity.setType(typrText.getText()== null ? "" : typrText.getText());
        newType = typrText.getText();
        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("添加种类");
        alert.setHeaderText("温馨提示:");
        //信息校验判断
        String message = DataCheckUtils.validateData(equipmentTypeEntity);
        if (message == null) {

            boolean flag = equipmentTypeController.modifyEquiptype(equipmentTypeEntity.getId(),equipmentTypeEntity);
            if (flag) {
                equipmentController.updata(old,newType);
                alert.setContentText("添加成功!");
                alert.showAndWait();
                Stage stage = (Stage) okk.getScene().getWindow();
                stage.close();
            } else {
                alert.setContentText("添加失败,请稍后再试.");
                alert.showAndWait();
            }
        } else {
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

    @FXML
    void returnBack(ActionEvent event) {
        Stage stage = (Stage) noo.getScene().getWindow();
        stage.close();
    }
    public void initialize(){
        old = equipmentTypeEntity.getType();
        typrText.setText(equipmentTypeEntity.getType());
    }
}
