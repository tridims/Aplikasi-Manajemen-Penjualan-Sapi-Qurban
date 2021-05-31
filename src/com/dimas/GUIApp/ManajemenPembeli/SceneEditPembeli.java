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

public class SceneEditPembeli implements Initializable {
    @FXML private TextField textFieldNama;
    @FXML private TextField textFieldAlamat;
    @FXML private TextField textFieldNomorHP;
    @FXML private TextArea textAreaCatatan;
    @FXML private JFXButton buttonSimpan;
    @FXML private JFXButton buttonBatal;
    private Pembeli pembeli;

    void init(Pembeli pembeli) {
        this.pembeli = pembeli;
        textFieldNama.setText(pembeli.getNama());
        textFieldAlamat.setText(pembeli.getAlamat());
        textFieldNomorHP.setText(pembeli.getNomorHP());
        textAreaCatatan.setText(pembeli.getKeterangan());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonSimpan.setOnMouseClicked(mouseEvent -> {
            pembeli.setNama(textFieldNama.getText());
            pembeli.setAlamat(textFieldAlamat.getText());
            pembeli.setNomorHP(textFieldNomorHP.getText());
            pembeli.setKeterangan(textAreaCatatan.getText());
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        });

        buttonBatal.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        });
    }
}
