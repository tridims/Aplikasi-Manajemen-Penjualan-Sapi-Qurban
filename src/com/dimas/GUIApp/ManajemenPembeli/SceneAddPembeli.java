package com.dimas.GUIApp.ManajemenPembeli;

import com.dimas.GUIApp.MainApp;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneAddPembeli implements Initializable {
    @FXML private TextField textFieldNama;
    @FXML private TextField textFieldAlamat;
    @FXML private TextField textFieldNomorHP;
    @FXML private TextArea textAreaCatatan;
    @FXML private JFXButton tombolTambah;
    @FXML private JFXButton tombolBatal;

    ManajemenPembeli manajemenPembeli = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tombolTambah.setOnMouseClicked(mouseEvent -> {
            try {
                String nama = textFieldNama.getText();
                String alamat = textFieldAlamat.getText();
                String nomorHP = textFieldNomorHP.getText();
                String catatan = textAreaCatatan.getText();
                if (nama.equals("")||alamat.equals("")||nomorHP.equals("")||catatan.equals(""))
                    throw new Exception();
                MainApp.ManajemenApp.addPembeli(nama,alamat,nomorHP,catatan);
                manajemenPembeli.refreshTable();
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Tidak boleh ada yang kosong !");
                alert.show();
            }
        });

        tombolBatal.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    void init(ManajemenPembeli manajemenPembeli) {
        this.manajemenPembeli = manajemenPembeli;
    }
}
