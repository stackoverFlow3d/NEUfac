package com.neu.fac.view.facManager;
import com.neu.fac.TableMoudle.UserTable;
import com.neu.fac.controller.EquipmentController;
import com.neu.fac.controller.PowerController;
import com.neu.fac.controller.ProductController;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.pojo.PowerEntity;
import com.neu.fac.pojo.ProductEntity;
import com.neu.fac.pojo.WorkEntity;
import com.neu.fac.utils.DataCheckUtils;
import com.neu.fac.utils.DataTrans;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddProduct {
    private EquipmentController equipmentController = EquipmentController.getInstance();
    private ProductController productController = ProductController.getInstance();

    private List<ProductEntity> productEntities = productController.getProductList();
    private List<ProductEntity> currentList = new ArrayList<ProductEntity>();
    private ObservableList<ProductEntity> observableList = FXCollections.observableList(currentList);
    private EquipmentEntity thisEquip = DataTrans.getEquipmentData();
    private PowerController powerController = PowerController.getInstance();
    @FXML
    private TextField numbetText;

    @FXML
    private TableColumn<PowerEntity, String> powerLine;

    @FXML
    private TextField energyText;

    @FXML
    private Button removeButton;

    @FXML
    private Button newButton;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField nameText;

    @FXML
    private TableView<PowerEntity> table;

    @FXML
    private TableColumn<PowerEntity, String> nameLine;

    @FXML
    void AddProduct(ActionEvent event) {
        PowerEntity powerEntity = new PowerEntity();
        powerEntity.setEquipNun(DataTrans.getEquipmentData().getNumber());
        powerEntity.setPower(energyText.getText());
        powerEntity.setProducrName(choiceBox.getValue());

        //????????????????????????
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("????????????");
        alert.setHeaderText("????????????:");
        //??????????????????
        String message = DataCheckUtils.validateData(powerEntity);
        if (message == null) {
            boolean flag = powerController.addPower(powerEntity);
            if (flag) {
                alert.setContentText("????????????!");
                alert.showAndWait();
            } else {
                alert.setContentText("????????????,???????????????.");
                alert.showAndWait();
            }
        } else {
            alert.setContentText(message);
            alert.showAndWait();
        }
        initialize();
    }

    @FXML
    void remove(ActionEvent event) throws IOException {
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
        ObservableList<PowerEntity> list=table.getItems();
        boolean flage = powerController.remove(table.getSelectionModel().getSelectedItem().getProducrName());
        if(flage) {
            alert.setContentText("????????????!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("????????????!");
            alert.showAndWait();
        }
        initialize();
    }


    public void initialize(){
        nameText.setText(thisEquip.getName());
        numbetText.setText(thisEquip.getNumber());

        ObservableList<PowerEntity> data = FXCollections.observableList(powerController.findCurrentPowerList(DataTrans.getEquipmentData().getNumber()));
        nameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducrName()) );
        powerLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPower()) );
        ObservableList<String> data1 = FXCollections.observableList(productController.getProductStringList());
        choiceBox.setItems(data1);
        table.setItems(data);
    }

}

