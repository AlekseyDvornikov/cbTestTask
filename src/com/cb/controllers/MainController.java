package com.cb.controllers;

import com.cb.model.BnkSeekEntity;
import com.cb.service.BnkSeekService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class MainController {

    private ObservableList<BnkSeekEntity> data;


    @Autowired
    private BnkSeekService bnkSeekService;

    @FXML
    private TableView<BnkSeekEntity> mainTable;

    @FXML
    public void exit(){
        System.exit(0);
    }

    @FXML
    public void showSettings(ActionEvent event){
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        List<BnkSeekEntity> contacts = bnkSeekService.findAll();
        data = FXCollections.observableArrayList(contacts);

        // Столбцы таблицы
        TableColumn<BnkSeekEntity, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("vkey"));

        TableColumn<BnkSeekEntity, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("real"));

        TableColumn<BnkSeekEntity, String> phoneColumn = new TableColumn<>("Телефон");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("pzn"));

        TableColumn<BnkSeekEntity, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("uer"));

        mainTable.getColumns().setAll(idColumn, nameColumn, phoneColumn, emailColumn);

        // Данные таблицы
        mainTable.setItems(data);
    }

    public void sh(javafx.event.ActionEvent event) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    SettingsController.class.getResource("../ui/settings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Настройки хранилища");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
