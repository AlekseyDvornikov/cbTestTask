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
import net.iryndin.jdbf.core.DbfMetadata;
import net.iryndin.jdbf.core.DbfRecord;
import net.iryndin.jdbf.reader.DbfReader;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public class MainController {

    // знаю это страшно и криво, но в 2 часа ночи мозги уже не соображали
    public static String file="";

    private ObservableList<BnkSeekEntity> data;


    @Autowired
    private BnkSeekService bnkSeekService;

    @FXML
    private TableView<BnkSeekEntity> mainTable;

    @FXML
    public void exit(){
        System.exit(0);
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        List<BnkSeekEntity> entitiesList = bnkSeekService.findAll();
        data = FXCollections.observableArrayList(entitiesList);

        // Столбцы таблицы
        TableColumn<BnkSeekEntity, String> idColumn = new TableColumn<>("VKEY");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("vkey"));

        TableColumn<BnkSeekEntity, String> nameColumn = new TableColumn<>("PZN");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("pzn"));

        TableColumn<BnkSeekEntity, String> phoneColumn = new TableColumn<>("UER");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("uer"));

        TableColumn<BnkSeekEntity, String> emailColumn = new TableColumn<>("RGN");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("rgn"));

        mainTable.getColumns().setAll(idColumn, nameColumn, phoneColumn, emailColumn);

        // Данные таблицы
        mainTable.setItems(data);
    }

    private void proceedFiles(){
        if (file==null || file.equals("")) return;
        Charset stringCharset = Charset.forName("Cp866");

        InputStream dbf = null;
        try {
            dbf = new FileInputStream(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DbfRecord rec;
        try (DbfReader reader = new DbfReader(dbf)) {
            DbfMetadata meta = reader.getMetadata();

            System.out.println("Read DBF Metadata: " + meta);
            while ((rec = reader.read()) != null) {
                rec.setStringCharset(stringCharset);

                bnkSeekService.save(new BnkSeekEntity(rec.getString("VKEY"),rec.getString("PZN"),
                        rec.getString("UER"), rec.getString("RGN"),
                        "d","d","d","d","d","d","d","d","d","d","d"
                        ,"d","d","d","d","d","d",new Date(System.currentTimeMillis()),"d","d",
                        new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),"",new Date(System.currentTimeMillis())));

                System.out.println("Record #" + rec.getRecordNumber() + ": " + rec.toMap());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        init();
    }

    public void sh(javafx.event.ActionEvent event) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(SettingsController.class.getResource("../ui/settings.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Settings");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        proceedFiles();
    }
}
