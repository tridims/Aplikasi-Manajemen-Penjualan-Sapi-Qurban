package com.dimas.GUIApp.ManajemenSapi;

import com.dimas.ManajemenPenjualanSapiQurban.Sapi;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FormEditDataSapi implements Initializable {
    private Sapi sapi;
    private ManajemenSapi manajemenSapi;

    @FXML private TextField textField_hargaBeli;
    @FXML private TextField textField_JenisSapi;
    @FXML private TextField textField_warna;
    @FXML private TextField textField_tanggalPembelian;
    @FXML private TextArea textArea_catatan;
    @FXML private JFXButton button_simpan;
    @FXML private JFXButton button_batal;

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

            sapi.setHargaBeli(hargaBeli);
            sapi.setJenisSapi(jenisSapi);
            sapi.setWarna(warna);
            sapi.setTanggalPembelian(tanggal);
            sapi.setDeskripsi(catatan);

            if (manajemenSapi != null) {
                manajemenSapi.refreshTable();
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("ADA ERROR");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO : add button function
        button_batal.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        });

        button_simpan.setOnMouseClicked(mouseEvent -> {
            sapi.setJenisSapi(textField_JenisSapi.getText());
            sapi.setWarna(textField_warna.getText());
            sapi.setHargaBeli(Long.parseLong(textField_hargaBeli.getText()));
            sapi.setTanggalPembelian(textField_tanggalPembelian.getText());
            sapi.setDeskripsi(textArea_catatan.getText());
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            manajemenSapi.refreshTable();
            stage.close();
        });
    }

    void initData(Sapi sapi, ManajemenSapi manajemenSapi) {
        initData(sapi);
        this.manajemenSapi = manajemenSapi;
    }

    void initData(Sapi sapi) {
        textArea_catatan.setText(sapi.getDeskripsi());
        textField_hargaBeli.setText(String.valueOf(sapi.getHargaBeli()));
        textField_JenisSapi.setText(sapi.getJenisSapi());
        textField_warna.setText(sapi.getWarna());
        textField_tanggalPembelian.setText(sapi.getTanggalPembelian());
        this.sapi = sapi;
    }
}
