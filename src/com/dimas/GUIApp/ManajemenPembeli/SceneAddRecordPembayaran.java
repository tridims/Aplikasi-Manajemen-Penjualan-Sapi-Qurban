package com.dimas.GUIApp.ManajemenPembeli;

import com.dimas.ManajemenPenjualanSapiQurban.Pembeli;
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

public class SceneAddRecordPembayaran implements Initializable {

    private Pembeli pembeli;
    private SceneTransaksiPembeli sceneTransaksiPembeli;
    @FXML private TextField textFieldJumlahPembayaran;
    @FXML private TextField textFieldTanggal;
    @FXML private TextArea textAreaKeterangan;
    @FXML private JFXButton tombolTambah;
    @FXML private JFXButton tombolBatal;

    void init(Pembeli pembeli, SceneTransaksiPembeli sceneTransaksiPembeli) {
        this.pembeli = pembeli;
        this.sceneTransaksiPembeli = sceneTransaksiPembeli;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tombolBatal.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        });

        tombolTambah.setOnMouseClicked(mouseEvent -> {
            String tanggal, keterangan;
            long jumlahPembayaran;
            try {
                tanggal = textFieldTanggal.getText();
                keterangan = textAreaKeterangan.getText();
                jumlahPembayaran = Long.parseLong(textFieldJumlahPembayaran.getText());
                pembeli.transaksiPembayaran(jumlahPembayaran, keterangan, tanggal);
                sceneTransaksiPembeli.refreshTableRecordPembayaran();
                sceneTransaksiPembeli.setStatusInformation();
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Masukan angka pada jumlah pembayaran");
                alert.show();
            }
        });
    }
}

