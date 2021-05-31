package com.dimas.GUIApp.ManajemenPembeli;

import com.dimas.GUIApp.MainApp;
import com.dimas.ManajemenPenjualanSapiQurban.Pembeli;
import com.dimas.ManajemenPenjualanSapiQurban.Sapi;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ScenePilihSapi implements Initializable {
    SceneTransaksiPembeli sceneTransaksiPembeli;
    private Pembeli pembeli;

    @FXML private TableView<Sapi> tabelDaftarSapi;
    @FXML private TableColumn<Sapi, String> kolomID;
    @FXML private TableColumn<Sapi, String> kolomJenisSapi;
    @FXML private TableColumn<Sapi, String> kolomWarna;
    @FXML private TableColumn<Sapi, String> kolomHargaBeli;
    @FXML private TableColumn<Sapi, String> kolomkalkulasiHargaJual;
    @FXML private TableColumn<?, ?> kolomCatatan;
    @FXML private JFXButton tombolSimpan;
    @FXML private JFXButton tombolBatal;
    @FXML private TextField textFieldHargaLaku;
    @FXML private TextField textFieldTanggalAntar;
    @FXML private TextArea textFieldKeterangan;

    public void init(SceneTransaksiPembeli sceneTransaksiPembeli, Pembeli pembeli) {
        this.sceneTransaksiPembeli = sceneTransaksiPembeli;
        this.pembeli = pembeli;
        tampilData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tombolSimpan.setDisable(true);
        tombolSimpan.setOnMouseClicked(mouseEvent -> {
            Sapi selected = tabelDaftarSapi.getSelectionModel().getSelectedItem();
            String tanggalAntar, keterangan;
            Long hargaLaku;
            try {
                hargaLaku = Long.parseLong(textFieldHargaLaku.getText());
                tanggalAntar = textFieldTanggalAntar.getText();
                keterangan = textFieldKeterangan.getText();

                if (textFieldHargaLaku.getText().equals("")||tanggalAntar.equals("")||keterangan.equals(""))
                    throw new Exception();

                pembeli.beli(selected,keterangan,hargaLaku,tanggalAntar);
                sceneTransaksiPembeli.refreshTableDaftarPembelian();
                sceneTransaksiPembeli.setStatusInformation();
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Ada Error!");
                alert.show();
            }
        });

        tombolBatal.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        });

        tabelDaftarSapi.setOnMouseClicked(mouseEvent -> {
            if (!tabelDaftarSapi.getSelectionModel().isEmpty())
                tombolSimpan.setDisable(false);
        });
    }

    private void tampilData() {
        kolomID.setCellValueFactory(new PropertyValueFactory<Sapi, String>("id"));
        kolomJenisSapi.setCellValueFactory(new PropertyValueFactory<Sapi, String>("jenisSapi"));
        kolomWarna.setCellValueFactory(new PropertyValueFactory<Sapi, String>("warna"));
        kolomHargaBeli.setCellValueFactory(new PropertyValueFactory<Sapi, String>("hargaBeli"));
        kolomkalkulasiHargaJual.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sapi, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sapi, String> sapiStringCellDataFeatures) {
                return new SimpleStringProperty(String.valueOf(sapiStringCellDataFeatures.getValue().getHargaJualOptimal(
                        MainApp.ManajemenApp.getTargetKeuntunganPerSapi(),
                        MainApp.ManajemenApp.getBiayaPerawatanPerhari(),
                        MainApp.ManajemenApp.getBiayaPerawatanPerhari()
                )));
            }
        });

        ObservableList<Sapi> dataTabel = FXCollections.observableArrayList(MainApp.ManajemenApp.getDaftarSapiBelumlaku());
        tabelDaftarSapi.setItems(dataTabel);
    }

    public void refreshTable() {
        ObservableList<Sapi> dataTabel = FXCollections.observableArrayList(MainApp.ManajemenApp.getDaftarSapiBelumlaku());
        tabelDaftarSapi.refresh();
        tabelDaftarSapi.setItems(dataTabel);
    }
}
