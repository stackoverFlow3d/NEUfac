package com.neu.fac.view.product;

import com.neu.fac.TableMoudle.UserTable;
import com.neu.fac.controller.ProductController;
import com.neu.fac.pojo.ProductEntity;
import com.neu.fac.scenes.product.AddProductView;
import com.neu.fac.scenes.product.ModifyProView;
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

public class ProductManager {
    private ProductController productController = ProductController.getInstance();
    private AddProductView addProductView = new AddProductView();
    private ModifyProView modifyProView = new ModifyProView();
    @FXML
    private TableColumn<ProductEntity, String> typeLine;

    @FXML
    private TableColumn<ProductEntity, String> desLine;

    @FXML
    private TableColumn<ProductEntity, String> namberLine;

    @FXML
    private TableColumn<ProductEntity, String> sizeLine;

    @FXML
    private TableColumn<ProductEntity, String> nameLine;

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
    private TableColumn<ProductEntity, String> idLine;

    @FXML
    private TableView<ProductEntity> table;

    @FXML
    void check(ActionEvent event) {
        String name = typeText.getText();
        ProductEntity target = productController.searchProduct(name);
        List<ProductEntity> targets = new ArrayList<ProductEntity>();
        targets.add(target);
        ObservableList<ProductEntity> chekList = FXCollections.observableList(targets);
        table.setItems(chekList);
    }

    @FXML
    void replace(ActionEvent event) {
        typeText.clear();
        initialize();
    }

    @FXML
    void newPro(ActionEvent event) throws Exception {
        Stage add = (Stage) newButton.getScene().getWindow();
        addProductView.start(add);
        initialize();
    }

    @FXML
    void modifyPro(ActionEvent event) throws Exception {
        ProductEntity productEntity = table.getSelectionModel().getSelectedItem();
        DataTrans.setProductEntity(productEntity);
        Stage newStage = (Stage) modfiyButton.getScene().getWindow();
        modifyProView.start(newStage);
        initialize();
    }

    @FXML
    void deletPro(ActionEvent event) throws IOException {
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

        flag = productController.removeProduct(table.getSelectionModel().getSelectedItem().getId());
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
        ObservableList<ProductEntity> data = FXCollections.observableList(productController.getProductList());
        typeLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()) );
        idLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
        desLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()) );
        nameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()) );
        namberLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumber()) );
        sizeLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSize()) );
        table.setItems(data);
    }

}

