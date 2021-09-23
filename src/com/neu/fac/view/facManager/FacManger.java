package com.neu.fac.view.facManager;

import com.neu.fac.controller.BidController;
import com.neu.fac.controller.EquipmentController;
import com.neu.fac.controller.OrderController;
import com.neu.fac.pojo.BidEntity;
import com.neu.fac.pojo.EquipmentEntity;
import com.neu.fac.pojo.OrderEntity;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.scenes.equipment.ModifyEquipView;
import com.neu.fac.scenes.facManager.AddFacEquipView;
import com.neu.fac.scenes.facManager.AddProduct;
import com.neu.fac.scenes.facManager.ArrangeView;
import com.neu.fac.scenes.facManager.BorrowView;
import com.neu.fac.utils.DataCheckUtils;
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

public class FacManger {
    private EquipmentController equipmentController = EquipmentController.getInstance();
    private AddFacEquipView addFacEquipView = new AddFacEquipView();
    private BorrowView borrowView = new BorrowView();
    private AddProduct addProduct = new AddProduct();
    private ModifyEquipView modifyEquipView = new ModifyEquipView();
    private OrderController orderController = OrderController.getInstance();
    private BidController bidController = BidController.getInstance();
    private UserEntity user = DataTrans.getUserDate();
    private ArrangeView arrangeView = new ArrangeView();
    @FXML
    private Button confrimButton;
    @FXML
    private TableColumn<OrderEntity, String> numLine1;

    @FXML
    private TableColumn<OrderEntity, String> amountLine1;

    @FXML
    private TableColumn<OrderEntity, String> phoneLine1;

    @FXML
    private TableColumn<OrderEntity, String> adressLine1;

    @FXML
    private TableColumn<OrderEntity, String> orderNameLine1;

    @FXML
    private TableColumn<OrderEntity, String> stateLine1;

    @FXML
    private TableColumn<OrderEntity, String> accLine1;

    @FXML
    private TableColumn<OrderEntity, String> finsishLine1;




    @FXML
    private TableColumn<OrderEntity, String> numLine;
    @FXML
    private TextField priceText;
    @FXML
    private TableColumn<OrderEntity, String> amountLine;

    @FXML
    private TableColumn<OrderEntity, String> phoneLine;

    @FXML
    private TableColumn<OrderEntity, String> adressLine;

    @FXML
    private TableColumn<OrderEntity, String> orderNameLine;

    @FXML
    private TableColumn<OrderEntity, String> deadLine;

    @FXML
    private TableColumn<OrderEntity, String> accLine;

    @FXML
    private TableColumn<OrderEntity, String> finsishLine;

    @FXML
    private TableColumn<OrderEntity, String> orderIdLine;

    @FXML
    private TableView<OrderEntity> orderTable;

    @FXML
    private TableView<OrderEntity> orderTable1;

    @FXML
    private Button borrowButton;
    @FXML
    private TableColumn<EquipmentEntity, String> numberLine;

    @FXML
    private TableColumn<EquipmentEntity, String> typeLine;

    @FXML
    private TableColumn<EquipmentEntity, String> sizeLIne;

    @FXML
    private TableColumn<EquipmentEntity, String> browwerLine;

    @FXML
    private Button checkButton;

    @FXML
    private Button newButtomn;

    @FXML
    private Button controlButton;

    @FXML
    private TableColumn<EquipmentEntity, String> nameLine;

    @FXML
    private Button replaceButton;

    @FXML
    private Button deletButton;

    @FXML
    private TextField checkText;

    @FXML
    private TableColumn<EquipmentEntity, String> statuLine;

    @FXML
    private TableColumn<EquipmentEntity, String> idLine;

    @FXML
    private Button choiceProduct;

    @FXML
    private TableView<EquipmentEntity> table;

    @FXML
    private TableColumn<EquipmentEntity, String> descripLine;

    @FXML
    private Button modifyButton;

    @FXML
    void confrim(ActionEvent event) {
        BidEntity bid = new BidEntity();
        bid.setBidder(user);
        bid.setPrice(priceText.getText());
        bid.setOrder(orderTable.getSelectionModel().getSelectedItem());
        bid.setStatus("否");
        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("投标");
        alert.setHeaderText("温馨提示:");
        //信息校验判断
        String message = DataCheckUtils.validateData(bid);
        if (message == null) {
            List<BidEntity> bidEntities = bidController.getBidList();
            for(BidEntity bidEntity:bidEntities){
                if(bidEntity.getOrder().getId().equals(orderTable.getSelectionModel().getSelectedItem().getId())&&bidController.hasBid(user.getFacName())){
                    alert.setContentText("不能重复投标!");
                    alert.showAndWait();
                    return;
                }
            }
            boolean flag = bidController.newBid(bid);
            if (flag) {
                alert.setContentText("投标成功!");
                alert.showAndWait();
            } else {
                alert.setContentText("投标失败,请稍后再试.");
                alert.showAndWait();
            }
        } else {
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

    @FXML
    void replace(ActionEvent event) {
        checkText.clear();
        initialize();
    }

    @FXML
    void check(ActionEvent event) {
        String name = checkText.getText();
        EquipmentEntity target = equipmentController.searchEquipment(name);
        List<EquipmentEntity> targets = new ArrayList<EquipmentEntity>();
        targets.add(target);
        ObservableList<EquipmentEntity> chekList = FXCollections.observableList(targets);
        table.setItems(chekList);
    }

    @FXML
    void DIstanceControll(ActionEvent event) throws IOException {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION,"确认操作吗？",new ButtonType("确定", ButtonBar.ButtonData.YES),new ButtonType("取消", ButtonBar.ButtonData.NO));
        alert2.setTitle("确认");
        alert2.setHeaderText("温馨提示:");
        alert2.setContentText("确认操作吗？");
        Optional<ButtonType> _buttonType = alert2.showAndWait();
        if(_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.NO)){
            System.out.println("否定！");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("操作");
        alert.setHeaderText("温馨提示:");
        boolean flag = false;
        flag = equipmentController.distanceController(table.getSelectionModel().getSelectedItem());
        if(flag) {
            alert.setContentText("操作成功!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("操作失败!");
            alert.showAndWait();
        }
    }

    @FXML
    void delEquip(ActionEvent event) throws IOException {
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
        if(table.getSelectionModel().getSelectedItem().getLeaseStatus().equals("已租用")) {
            alert.setContentText("不能删除租来的设备!");
            alert.showAndWait();
            return;
        }
        boolean flag = false;

        flag = equipmentController.removeSelfProduct(table.getSelectionModel().getSelectedItem().getId());
        if(flag) {
            alert.setContentText("删除成功!");
            initialize();
            alert.showAndWait();
        }else {
            alert.setContentText("删除失败!");
            alert.showAndWait();
        }
    }

    @FXML
    void newEquip(ActionEvent event) throws Exception {
        Stage add = (Stage) newButtomn.getScene().getWindow();
        addFacEquipView.start(add);
        initialize();
    }
    @FXML
    void borrowEquip(ActionEvent event) throws Exception {
        Stage b = (Stage) newButtomn.getScene().getWindow();
        borrowView.start(b);
        initialize();
    }

    @FXML
    void SettingProduct(ActionEvent event) throws Exception {
        //会报错
        if(table.getSelectionModel().getSelectedItem().equals(null)){
            return;
        }
        DataTrans.setEquipmentData(table.getSelectionModel().getSelectedItem());
        Stage c = (Stage) controlButton.getScene().getWindow();
        addProduct.start(c);
        initialize();
    }
    @FXML
    void modify(ActionEvent event) throws Exception {
        if(table.getSelectionModel().getSelectedItem().getLeaseStatus().equals("已租用")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("修改");
            alert.setHeaderText("温馨提示:");
            alert.setContentText("只能修改工厂自己的设备！");
            alert.showAndWait();
            return;
        }
        DataTrans.setEquipmentData(table.getSelectionModel().getSelectedItem());
        Stage ad = (Stage) modifyButton.getScene().getWindow();
        modifyEquipView.start(ad);
        initialize();
    }

    @FXML
    void arrangeWork(ActionEvent event) throws Exception {
        if(!orderTable1.getSelectionModel().getSelectedItem().getStatus().equals("投标结束")&&!orderTable1.getSelectionModel().getSelectedItem().getStatus().equals("已排产")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("安排生产");
            alert.setHeaderText("温馨提示:");
            alert.setContentText("只能安排投标完毕的产品生产！");
            alert.showAndWait();
            return;
        }
        DataTrans.setOrderEntity(orderTable1.getSelectionModel().getSelectedItem());
        Stage add = (Stage) newButtomn.getScene().getWindow();
        arrangeView.start(add);
        bidController.updata(orderTable1.getSelectionModel().getSelectedItem().getNumber(),"已排产");
        orderController.updata(orderTable1.getSelectionModel().getSelectedItem().getNumber(),"已排产");
        initialize();
    }

    @FXML
    void finishWork(ActionEvent event) throws IOException {
        if(!orderTable1.getSelectionModel().getSelectedItem().getStatus().equals("已排产")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("结束生产");
            alert.setHeaderText("温馨提示:");
            alert.setContentText("只能结束排产完毕的产品！");
            alert.showAndWait();
            return;
        }
        bidController.updata(orderTable1.getSelectionModel().getSelectedItem().getNumber(),"完工");
        orderController.updata(orderTable1.getSelectionModel().getSelectedItem().getNumber(),"完工");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("安排生产");
        alert.setHeaderText("温馨提示:");
        alert.setContentText("操作成功！");
        alert.showAndWait();
        initialize();
    }

    @FXML
    void fahuo(ActionEvent event) throws IOException {
        if(!orderTable1.getSelectionModel().getSelectedItem().getStatus().equals("完工")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("安排发货");
            alert.setHeaderText("温馨提示:");
            alert.setContentText("只能安排生产完毕的产品发货！");
            alert.showAndWait();
            return;
        }
        bidController.updata(orderTable1.getSelectionModel().getSelectedItem().getNumber(),"已收货");
        orderController.updata(orderTable1.getSelectionModel().getSelectedItem().getNumber(),"已收货");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("安排生产");
        alert.setHeaderText("温馨提示:");
        alert.setContentText("操作成功！");
        alert.showAndWait();
        initialize();
    }

    @FXML
    void finishOrder(ActionEvent event) throws IOException {
        if(!orderTable1.getSelectionModel().getSelectedItem().getStatus().equals("已收货")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("结束");
            alert.setHeaderText("温馨提示:");
            alert.setContentText("只能结束发货完毕的订单！");
            alert.showAndWait();
            return;
        }
        bidController.updata(orderTable1.getSelectionModel().getSelectedItem().getNumber(),"结束");
        orderController.updata(orderTable1.getSelectionModel().getSelectedItem().getNumber(),"结束");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("安排生产");
        alert.setHeaderText("温馨提示:");
        alert.setContentText("操作成功！");
        alert.showAndWait();
        initialize();
    }

    public void initialize() {
        ObservableList<EquipmentEntity> data = FXCollections.observableList(equipmentController.findMangerList(user));
        typeLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()) );
        idLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
        descripLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()) );
        nameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()) );
        numberLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumber()) );
        sizeLIne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSize()) );
        browwerLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFactory().equals("平台设备")?"平台设备":"自有设备") );
        statuLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEuipmentStatus()) );
        table.setItems(data);
        ObservableList<OrderEntity> ord = FXCollections.observableList(orderController.getBid());
        amountLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAmount()) );
        orderIdLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
        deadLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeadLine().toString()) );
        orderNameLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()) );
        finsishLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFinsishDay().toString()) );
        accLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcpName()) );
        adressLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcpAddress()) );
        phoneLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcpPhone()) );
        numLine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumber()) );
        orderTable.setItems(ord);
        ObservableList<OrderEntity> ord1 = FXCollections.observableList(bidController.getChoicedBid(user.getFacName()));
        amountLine1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAmount()) );
        orderNameLine1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()) );
        finsishLine1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFinsishDay().toString()) );
        accLine1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcpName()) );
        adressLine1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcpAddress()) );
        phoneLine1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcpPhone()) );
        numLine1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumber()) );
        stateLine1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()) );
        orderTable1.setItems(ord1);
    }
}
