package com.neu.fac.view.human;
import com.neu.fac.controller.UserController;
import com.neu.fac.scenes.human.AddUserView;
import com.neu.fac.TableMoudle.UserTable;
import com.neu.fac.scenes.human.ReformatUserView;
import com.neu.fac.utils.DataTrans;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.neu.fac.pojo.UserEntity;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserManger {
    //private final AppModel model;
    private AddUserView addUserView = new AddUserView();
    private ReformatUserView reformatUserView = new ReformatUserView();
    private UserController userController = UserController.getInstance();
    private DataTrans dataTrans = DataTrans.getInstance();
    @FXML
    private TableView<UserTable> table;
    @FXML
    private TableColumn<UserTable, CheckBox> checkLine;
    @FXML
    private Button replaceButton;

    @FXML
    private TableColumn<UserTable, String> userNameLine;

    @FXML
    private TableColumn<UserTable, String> roleLine;

    @FXML
    private TextField checkText;

    @FXML
    private TableColumn<UserTable, String> phoneLine;

    @FXML
    private Button fixButton;

    @FXML
    private Button checkButton;

    @FXML
    private Button delButton;

    @FXML
    private Button newButton;

    @FXML
    private TableColumn<UserEntity, String> idLine;

    @FXML
    private TableColumn<UserEntity, String> nameLine;


    @FXML
    void check(ActionEvent event) {
        ObservableList<UserTable> list = table.getItems();
        List<UserTable> list1 = new ArrayList<UserTable>();
        for(UserTable o:list)
        {
            if(o.getName().equals(checkText.getText()))
            {
                list1.add(o);
            }
        }
        ObservableList<UserTable> chekList = FXCollections.observableList(list1);
        table.setItems(chekList);
    }

    @FXML
    void replace(ActionEvent event) {
        checkText.clear();
        initialize();
    }

    @FXML
    void newLine(ActionEvent event) throws Exception {
        Stage newStage = (Stage) newButton.getScene().getWindow();
        addUserView.start(newStage);
        initialize();
    }

    @FXML
    void delLine(ActionEvent event) throws IOException {
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
        boolean flag = false;
        ObservableList<UserTable> list=table.getItems();
        for(UserTable o:list)
        {
            if(o.cb.isSelected())
            {
                flag = userController.removeUser(o.getId());
            }
        }
        if(flag) {
            alert.setContentText("????????????!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("????????????!");
            alert.showAndWait();
        }

    }

    @FXML
    void fixLine(ActionEvent event) throws Exception {

        //??????????????????????????????
        UserEntity userEntity = new UserEntity();
        ObservableList<UserTable> list=table.getItems();
        for(UserTable o:list)
        {
            if(o.cb.isSelected())
            {
                userEntity = userController.searchUser(o.getId());
                DataTrans.setUserDate(userEntity);
                break;
            }
        }

        Stage newStage = (Stage) fixButton.getScene().getWindow();
        reformatUserView.start(newStage);
        initialize();
    }

    public void initialize() {
        ObservableList<UserTable> data = FXCollections.observableList(userController.getUserTableList());
        nameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()) );
        checkLine.setCellValueFactory(cellData ->cellData.getValue().cb.getCheckBox());
        userNameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserName()) );
        roleLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPower()) );
        phoneLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()) );
        idLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
        table.setItems(data);
    }
}

