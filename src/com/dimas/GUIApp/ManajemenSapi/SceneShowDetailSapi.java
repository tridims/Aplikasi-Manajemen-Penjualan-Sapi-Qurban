package com.dimas.GUIApp.ManajemenSapi;

import com.dimas.GUIApp.MainApp;
import com.dimas.ManajemenPenjualanSapiQurban.Sapi;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneShowDetailSapi implements Initializable{
    ManajemenSapi manajemenSapi;
    Sapi sapi;

    @FXML private Text textWarna;
    @FXML private Text textHargaBeli;
    @FXML private Text textKalkulasiHargaJual;
    @FXML private Text textTanggalBeli;
    @FXML private Text textStatus;
    @FXML private Text textHargaJual;
    @FXML private Text textTanggalAntar;
    @FXML private Text textJenis;
    @FXML private Text textNamaPembeli;
    @FXML private Text textID;
    @FXML private JFXButton buttonClose;
    @FXML private JFXButton buttonModifiy;
    @FXML private JFXButton buttonLihatPembeli;

    public void init(Sapi sapi, ManajemenSapi manajemenSapi) {
        this.manajemenSapi = manajemenSapi;
        this.sapi = sapi;
        textWarna.setText(sapi.getWarna());
        textHargaBeli.setText(String.valueOf(sapi.getHargaBeli()));
        textKalkulasiHargaJual.setText(String.valueOf(MainApp.ManajemenApp.hitungHargajual(sapi)));
        textTanggalBeli.setText(sapi.getTanggalPembelian());
        textStatus.setText("");
        textHargaJual.setText((sapi.isLaku())?String.valueOf(sapi.getHargaJual()):"");
        textTanggalAntar.setText((sapi.isLaku())?sapi.getTanggalAntar():"");
        textJenis.setText(sapi.getJenisSapi());
        textNamaPembeli.setText((sapi.isLaku())?sapi.getPembeli().getNama():"");
        textID.setText(String.valueOf(sapi.getId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonClose.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        });

        buttonModifiy.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormEditDataSapi.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Form Edit Data Sapi");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                FormEditDataSapi editor = fxmlLoader.getController();
                editor.initData(sapi);
                stage.show();

            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        });

        buttonLihatPembeli.setOnMouseClicked(mouseEvent -> {
            // TODO : get form to show the detail of pembeli
        });
    }
}