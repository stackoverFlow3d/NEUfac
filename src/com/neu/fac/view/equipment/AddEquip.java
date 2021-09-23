package com.neu.fac.view.equipment;

import com.neu.fac.controller.EquipmentController;
import com.neu.fac.controller.EquipmentTypeController;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.pojo.EquipmentTypeEntity;
import com.neu.fac.utils.DataCheckUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class AddEquip {
    private EquipmentController equipmentController = EquipmentController.getInstance();
    private EquipmentTypeController equipmentTypeController = EquipmentTypeController.getInstance();
    @FXML
    private ChoiceBox<String> typeChoice;

    @FXML
    private TextField sizeText;

    @FXML
    private Button cancelbutton;

    @FXML
    private TextField typeText;

    @FXML
    private Button comfrimButtom;

    @FXML
    private TextField desText;

    @FXML
    private TextField nameText;

    @FXML
    void comfrim(ActionEvent event) {
        EquipmentEntity equipment = new EquipmentEntity();
        equipment.setType(typeChoice.getSelectionModel().getSelectedItem());
        equipment.setName(nameText.getText());
        equipment.setDescription(desText.getText());
        equipment.setSize(sizeText.getText());
        equipment.setEuipmentStatus("闲置中");
        equipment.setNumber(getNum());
        if(typeText.getText().equals("平台设备")){
            equipment.setFactory(typeText.getText());
        }else{
            equipment.setLeaseStatus("工厂设备");
            equipment.setFactory(typeText.getText());
        }
        if(equipment.getFactory().equals("平台设备")){
            equipment.setLeaseStatus("未租用");
        }
        if(equipment.getLeaseStatus().equals("工厂设备")){
            equipment.setCurrentFac(equipment.getFactory());
        }
        if(equipment.getFactory().equals("平台设备")){
            equipment.setCurrentFac("平台设备");
        }
        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("添加产品");
        alert.setHeaderText("温馨提示:");

        //信息校验判断
        String message = DataCheckUtils.validateData(equipment);
        if (message == null) {
            boolean flag = equipmentController.newEquipment(equipment);
            if (flag) {
                alert.setContentText("添加成功!");
                alert.showAndWait();
                Stage stage = (Stage) comfrimButtom.getScene().getWindow();
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
    //系统生成编码
    private String getNum(){
        List<EquipmentEntity> equipmentList = equipmentController.getEquipmentList();
        String s = "FSN283742";
        int max = 1001;
        if(equipmentList==null||equipmentList.size()==0){
            return s + "1001";
        }
        for(EquipmentEntity equip:equipmentList){
            String result = equip.getNumber().substring(equip.getNumber().length()-4,equip.getNumber().length());
            if(Integer.parseInt(result) > max){
                max = Integer.parseInt(result);
            }
        }
        return s + String.valueOf(max + 1);
    }
    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }
    public void initialize(){
        ObservableList<String> data = FXCollections.observableList(equipmentTypeController.getTpyeStringList());
        typeChoice.setItems(data);
    }
}

