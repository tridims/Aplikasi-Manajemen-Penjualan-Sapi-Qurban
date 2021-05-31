package com.dimas.GUIApp.ManajemenSapi;

import com.dimas.GUIApp.MainApp;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormTambahSapi {
    @FXML private TextField textField_hargaBeli;
    @FXML private TextField textField_JenisSapi;
    @FXML private TextField textField_warna;
    @FXML private TextField textField_tanggalPembelian;
    @FXML private TextArea textArea_catatan;
    @FXML private JFXButton button_simpan;
    @FXML private JFXButton button_batal;

    private ManajemenSapi manajemenSapi;

    @FXML
    void batal(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void simpan(ActionEvent event) {
        String jenisSapi, warna, tanggal, catatan;
        Long hargaBeli;
        try {
            hargaBeli = Long.parseLong(textField_hargaBeli.getText().replaceAll("[^\\d.]", ""));
            jenisSapi = textField_JenisSapi.getText();
            warna = textField_warna.getText();
            tanggal = textField_tanggalPembelian.getText();
            catatan = textArea_catatan.getText();

            MainApp.ManajemenApp.addSapi(jenisSapi, warna, hargaBeli, catatan, tanggal);
            manajemenSapi.refreshTable();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("ADA ERROR");
            alert.show();
        }
    }

    void init(ManajemenSapi manajemenSapi) {
        this.manajemenSapi = manajemenSapi;
    }
}
