package com.dimas.GUIApp;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeScene implements Initializable {
    @FXML private Label text_jumlah_sapi;
    @FXML private Label text_jumlah_sapi_belum_terjual;
    @FXML private Label text_jumlah_sapi_terjual;
    @FXML private Label text_jumlah_sapi_terkirim;
    @FXML private Label text_jumlah_pembeli;
    @FXML private Label text_jumlah_pembeli_belum_lunas;
    @FXML private Label text_jumlah_pembeli_lunas;
    @FXML private JFXButton button_home;
    @FXML private JFXButton button_manage_sapi;
    @FXML private JFXButton button_manage_pembeli;

    @FXML private TextField textField_target_keuntungan;
    @FXML private TextField textField_biaya_Perawatan;
    @FXML private TextField textField_biaya_pengiriman;

    @FXML private JFXButton button_change_setting;
    @FXML private JFXButton button_update;
    
    @FXML private BorderPane borderPane;

    // Scene
    Node homeScene;
    Node manageSapiScene;
    Node managePembeliScene;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get All Used Scene
        homeScene = borderPane.getCenter();
        manageSapiScene = (Node)FXMLLoader.load(getClass().getResource("ManajemenSapi/ManajemenSapi.fxml"));
        managePembeliScene = (Node)FXMLLoader.load(getClass().getResource("ManajemenPembeli/ManajemenPembeli.fxml"));

        // Scene navigation
        button_home.setOnMouseClicked(mouseEvent -> {
            borderPane.setCenter(homeScene);
        });

        button_manage_sapi.setOnMouseClicked(mouseEvent -> {
            borderPane.setCenter(manageSapiScene);
        });

        button_manage_pembeli.setOnMouseClicked(event -> {
            borderPane.setCenter(managePembeliScene);
        });

        // App Setting section
        textField_target_keuntungan.setText(String.valueOf(MainApp.ManajemenApp.getTargetKeuntunganPerSapi()));
        textField_biaya_pengiriman.setText(String.valueOf(MainApp.ManajemenApp.getBiayaPengiriman()));
        textField_biaya_Perawatan.setText(String.valueOf(MainApp.ManajemenApp.getBiayaPerawatanPerhari()));
        button_update.setDisable(true);

        button_change_setting.setOnMouseClicked(mouseEvent -> {
            if (button_change_setting.getText().equals("Change Setting")) {
                button_update.setDisable(false);
                textField_biaya_Perawatan.setEditable(true);
                textField_biaya_pengiriman.setEditable(true);
                textField_target_keuntungan.setEditable(true);
                button_change_setting.setText("Cancel");
            }
            else if (button_change_setting.getText().equals("Cancel")) {
                button_update.setDisable(true);
                textField_biaya_pengiriman.setEditable(false);
                textField_biaya_pengiriman.setEditable(false);
                textField_target_keuntungan.setEditable(false);
                button_change_setting.setText("Change Setting");
            }
        });

        button_update.setOnMouseClicked(mouseEvent -> {
            Long biayaPerawatan, biayaPengiriman, targetKeuntungan;
            try {
                biayaPerawatan = Long.parseLong(textField_biaya_Perawatan.getText());
                biayaPengiriman = Long.parseLong(textField_biaya_pengiriman.getText());
                targetKeuntungan = Long.parseLong(textField_target_keuntungan.getText());
                MainApp.ManajemenApp.setBiayaPerawatanPerhari(biayaPerawatan);
                MainApp.ManajemenApp.setBiayaPengiriman(biayaPengiriman);
                MainApp.ManajemenApp.setTargetKeuntunganPerSapi(targetKeuntungan);
                button_update.setDisable(true);
                button_change_setting.setText("Change Setting");

            } catch (Exception ignored) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Masukan Nomor !!");
                alert.show();
            }
        });

        // Setting text for the statistics
        text_jumlah_sapi.setText(String.valueOf(MainApp.ManajemenApp.getJumlahSapi()));
        text_jumlah_sapi_belum_terjual.setText(String.valueOf(MainApp.ManajemenApp.getJumlahSapiBelumLaku()));
        text_jumlah_sapi_terjual.setText(String.valueOf(MainApp.ManajemenApp.getJumlahSapiLaku()));
        text_jumlah_sapi_terkirim.setText(String.valueOf(0));

        text_jumlah_pembeli.setText(String.valueOf(MainApp.ManajemenApp.getJumlahPembeli()));
        text_jumlah_pembeli_belum_lunas.setText(String.valueOf(MainApp.ManajemenApp.getJumlahPembeliBelumLunas()));
        text_jumlah_pembeli_lunas.setText(String.valueOf(MainApp.ManajemenApp.getJumlahPembeliLunas()));
    }
}
