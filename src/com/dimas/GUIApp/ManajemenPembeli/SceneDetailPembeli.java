package com.dimas.GUIApp.ManajemenPembeli;

import com.dimas.ManajemenPenjualanSapiQurban.Pembeli;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneDetailPembeli implements Initializable {
    @FXML private TextField labelNama;
    @FXML private TextField labelAlamat;
    @FXML private TextField labelNomorHP;
    @FXML private TextField labelStatusPembayaran;
    @FXML private TextField labelJumlahSapiDibeli;
    @FXML private TextField labelTotalUangDibayarkan;
    @FXML private TextArea labelCatatan;
    @FXML private JFXButton tombolKeluar;
    private Pembeli pembeli;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tombolKeluar.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    public void init(Pembeli pembeli) {
        this.pembeli = pembeli;
        labelNama.setText(pembeli.getNama());
        labelAlamat.setText(pembeli.getAlamat());
        labelNomorHP.setText(pembeli.getNomorHP());
        labelStatusPembayaran.setText(pembeli.isLunas()?"Lunas":"Belum Lunas");
        labelJumlahSapiDibeli.setText(String.valueOf(pembeli.getJumlahSapiDibeli()));
        labelTotalUangDibayarkan.setText(String.valueOf(pembeli.getJumlahTotalUangDibayarkan()));
        labelCatatan.setText(pembeli.getKeterangan());

    }
}
