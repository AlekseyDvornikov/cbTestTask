package com.cb.controllers;


import com.cb.model.BnkSeekEntity;
import com.cb.repository.BnkSeekRepository;
import com.cb.service.BnkSeekService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

public class SettingsController {

    String selectedFile;

    @FXML
    public Button cancelButton;

    @FXML
    public Button okButton;

    @FXML
    public Button applyButton;

    @FXML
    public Button selectButton;

    @FXML
    public Label selectedFileLabel;

    private ObservableList<BnkSeekEntity> data;




    @FXML
    public void initialize(){
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Stage stage = (Stage) cancelButton.getScene().getWindow();

                if (selectedFile!=null && !selectedFile.equals("")){
                    MainController.file = selectedFile;
                    stage.close();

        }}});

        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
               public void handle(MouseEvent mouseEvent) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });

        selectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open DBF File");
                FileChooser.ExtensionFilter fileExtensions =
                                  new FileChooser.ExtensionFilter("DBF files (*.dbf)", "*.dbf");
                fileChooser.getExtensionFilters().add(fileExtensions);
                File file = fileChooser.showOpenDialog(stage);

                if (file != null) {
                    try {
                        selectedFileLabel.setText(file.getAbsolutePath());
                        selectedFile = file.getCanonicalPath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }

}
