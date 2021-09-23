package com.neu.fac.view.facManager;

import com.neu.fac.controller.EquipmentController;
import com.neu.fac.controller.EquipmentTypeController;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.utils.DataCheckUtils;
import com.neu.fac.utils.DataTrans;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ModifyEquip {
    private EquipmentController equipmentController = EquipmentController.getInstance();
    private EquipmentTypeController equipmentTypeController = EquipmentTypeController.getInstance();
    private EquipmentEntity equipmentEntity = DataTrans.getEquipmentData();
    @FXML
    private ChoiceBox<String> typeChoice;

    @FXML
    private TextField sizeText;

    @FXML
    private Button cancelbutton;

    @FXML
    private Button comfrimButtom;

    @FXML
    private TextField desText;

    @FXML
    private TextField nameText;

    @FXML
    void comfrim(ActionEvent event) throws IOException {

        equipmentEntity.setType(typeChoice.getSelectionModel().getSelectedItem());
        equipmentEntity.setName(nameText.getText());
        equipmentEntity.setDescription(desText.getText());
        equipmentEntity.setSize(sizeText.getText());
        equipmentEntity.setEuipmentStatus("闲置中");
        equipmentEntity.setLeaseStatus("工厂设备");
        equipmentEntity.setFactory(DataTrans.getUserDate().getFacName());
        equipmentEntity.setCurrentFac(equipmentEntity.getFactory());

        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("添加产品");
        alert.setHeaderText("温馨提示:");
        //信息校验判断
        String message = DataCheckUtils.validateData(equipmentEntity);
        if (message == null) {
            boolean flag = equipmentController.modifyEquipment(equipmentEntity.getId(),equipmentEntity);
            if (flag) {
                alert.setContentText("修改成功!");
                alert.showAndWait();
                Stage stage = (Stage) comfrimButtom.getScene().getWindow();
                stage.close();
            } else {
                alert.setContentText("修改失败,请稍后再试.");
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
        nameText.setText(equipmentEntity.getName());
        sizeText.setText(equipmentEntity.getSize());
        desText.setText(equipmentEntity.getDescription());
        typeChoice.setValue(equipmentEntity.getType());
        typeChoice.setItems(data);
    }
}
