package com.neu.fac.view.product;

import com.neu.fac.controller.ProductController;
import com.neu.fac.controller.ProductTypeController;
import com.neu.fac.pojo.ProductEntity;
import com.neu.fac.pojo.ProductTypeEntity;
import com.neu.fac.scenes.product.AddProductTypeView;
import com.neu.fac.scenes.product.ModifyProTypeView;
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

public class ProductTypeManger {
    private ProductTypeController productTypeController = ProductTypeController.getInstance();
    private AddProductTypeView addProductTypeView = new AddProductTypeView();
    private ModifyProTypeView modifyProTypeView = new ModifyProTypeView();
    private ProductController productController = ProductController.getInstance();
    @FXML
    private TableColumn<ProductTypeEntity, String> typeLine;
    @FXML
    private TableColumn<ProductTypeEntity, String> idLine;
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
    private TableView<ProductTypeEntity> table;

    @FXML
    void check(ActionEvent event) {

        String type = typeText.getText();
        ProductTypeEntity target = productTypeController.searchproductType(type);
        List<ProductTypeEntity> targets = new ArrayList<ProductTypeEntity>();
        targets.add(target);
        ObservableList<ProductTypeEntity> chekList = FXCollections.observableList(targets);
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
        addProductTypeView.start(add);
        initialize();
    }

    @FXML
    void modifyType(ActionEvent event) throws Exception {
        ProductTypeEntity productTypeEntity = table.getSelectionModel().getSelectedItem();
        DataTrans.setProductTypeEntity(productTypeEntity);
        Stage modi = (Stage) newButton.getScene().getWindow();
        modifyProTypeView.start(modi);
        initialize();
    }

    @FXML
    void deletType(ActionEvent event) throws IOException {
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
        List<ProductEntity> productEntities = productController.getProductList();
        for(ProductEntity p1:productEntities){
            if(p1.getType().equals(table.getSelectionModel().getSelectedItem().getType())){
                alert.setContentText("???????????????????????????????????????!");
                alert.showAndWait();
                return;
            }
        }
        boolean flag = false;

        flag = productTypeController.removeProductType(table.getSelectionModel().getSelectedItem().getId());

        if(flag) {
            alert.setContentText("????????????!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("????????????!");
            alert.showAndWait();
        }
    }

    public void initialize() {
        ObservableList<ProductTypeEntity> data = FXCollections.observableList(productTypeController.getProductTpyeList());
        typeLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()) );
        idLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
        table.setItems(data);
    }

}

