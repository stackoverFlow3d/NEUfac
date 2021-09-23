package com.neu.fac.view.equipment;

import com.neu.fac.controller.EquipmentController;
import com.neu.fac.controller.EquipmentTypeController;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.pojo.EquipmentTypeEntity;
import com.neu.fac.pojo.ProductTypeEntity;
import com.neu.fac.utils.DataCheckUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class AddEquipType {
    private EquipmentTypeController equipmentTypeController = EquipmentTypeController.getInstance();
    private EquipmentController equipmentController = EquipmentController.getInstance();
    @FXML
    private TextField typrText;

    @FXML
    private Button okk;

    @FXML
    private Button noo;

    @FXML
    void yes(ActionEvent event) {
        EquipmentTypeEntity equipmentType = new EquipmentTypeEntity();
        equipmentType.setType(typrText.getText()== null ? "" : typrText.getText());

        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("添加种类");
        alert.setHeaderText("温馨提示:");

        //信息校验判断
        String message = DataCheckUtils.validateData(equipmentType);
        if (message == null) {
            List<EquipmentTypeEntity> equipmentTypeEntities = equipmentTypeController.getEquipmentTpyeList();
            for (EquipmentTypeEntity equipmentTypeEntity:equipmentTypeEntities){
                if(equipmentTypeEntity.getType().equals(typrText.getText())){
                    alert.setContentText("不能添加已经存在的设备类型");
                    alert.showAndWait();
                    return;
                }

            }
            boolean flag = equipmentTypeController.addType(equipmentType);
            if (flag) {
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

}

