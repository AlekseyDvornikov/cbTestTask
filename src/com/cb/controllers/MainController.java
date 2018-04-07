package com.cb.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainController {

    // Переменные
    private ObservableList<Contact> data;


    @FXML
    public void exit(){
        System.exit(0);
    }

    @FXML
    public void showSettings(ActionEvent event){


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
        stage.setTitle("My modal window");
        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initOwner(
//                ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }
}
