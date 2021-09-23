package com.neu.fac.view.human;
import com.neu.fac.controller.UserController;
import com.neu.fac.TableMoudle.FacTable;
import com.neu.fac.utils.DataTrans;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class FacManger {

    private UserController userController = UserController.getInstance();
    @FXML
    private Button replaceButton;

    @FXML
    private TableColumn<FacTable, String> userNameLine;

    @FXML
    private TableColumn<FacTable, String> facNameLine;

    @FXML
    private TableColumn<FacTable, Button> optionLine;

    @FXML
    private TextField checkText;

    @FXML
    private TableColumn<FacTable, String> phoneLine;

    @FXML
    private Button checkButton;

    @FXML
    private TableColumn<FacTable, String> idLine;

    @FXML
    private TableColumn<FacTable, String> stateLine;

    @FXML
    public TableView<FacTable> table;

    @FXML
    private TableColumn<FacTable, String> nameLine;

    @FXML
    private TableColumn<FacTable, String> descriptionLine;

    @FXML
    void check(ActionEvent event) {
        ObservableList<FacTable> list = table.getItems();
        List<FacTable> list1 = new ArrayList<FacTable>();
        for(FacTable o:list)
        {
            if(o.getFacName().equals(checkText.getText()))
            {
                list1.add(o);
            }
        }
        ObservableList<FacTable> chekList = FXCollections.observableList(list1);
        table.setItems(chekList);
    }

    @FXML
    void replace(ActionEvent event) {
        checkText.clear();
        initialize();
    }
    public void initialize() {

        ObservableList<FacTable> data = FXCollections.observableList(userController.getFacTableList());
        nameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()) );
        userNameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserName()) );
        phoneLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()) );
        facNameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFacName()) );
        idLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
        descriptionLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiscription()) );
        optionLine.setCellValueFactory(cellData ->cellData.getValue().bt.getButton());
        stateLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState()) );
        table.setItems(data);
        DataTrans.setTable(table);
    }

}
