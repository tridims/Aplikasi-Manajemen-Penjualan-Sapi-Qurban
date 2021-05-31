package com.dimas.GUIApp.ManajemenPembeli;

import com.dimas.GUIApp.MainApp;
import com.dimas.ManajemenPenjualanSapiQurban.Pembeli;
import com.dimas.ManajemenPenjualanSapiQurban.RecordPembayaran;
import com.dimas.ManajemenPenjualanSapiQurban.Sapi;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneTransaksiPembeli implements Initializable {
    @FXML private TableView<Sapi> tableViewDaftarSapiDibeli;
    @FXML private TableColumn<Sapi, String> tableViewDaftarSapiID;
    @FXML private TableColumn<Sapi, String> tableViewDaftarSapiJenisSapi;
    @FXML private TableColumn<Sapi, String> tableViewDaftarSapiWarna;
    @FXML private TableColumn<Sapi, String> tableViewDaftarSapiHargaBeli;
    @FXML private TableColumn<Sapi, String> tableViewDaftarSapiHargaJual;
    @FXML private TableColumn<Sapi, String> tableViewDaftarSapiTanggalAntar;
    @FXML private TableView<RecordPembayaran> tableViewRiwayatPembayaran;
    @FXML private TableColumn<RecordPembayaran, Number> tableRiwayatPembayaranNomor;
    @FXML private TableColumn<RecordPembayaran, Number> tableRiwayatPembayaranJumlahUang;
    @FXML private TableColumn<RecordPembayaran, String> tableRiwayatPembayaranTanggal;
    @FXML private TableColumn<RecordPembayaran, String> tableRiwayatPembayaranKeterangan;
    @FXML private JFXButton tombolTambahSapi;
    @FXML private JFXButton tombolHapusSapi;
    @FXML private Label labelStatusPembayaran;
    @FXML private Label labelTotalPembayaran;
    @FXML private Label labelTotalPembelian;
    @FXML private Label labelKekuranganPembayaran;
    @FXML private JFXButton tombolTambahPembayaran;
    @FXML private JFXButton tombolHapusRiwayat;

    Pembeli selectedPembeli = null;

    void init(Pembeli pembeli) {
        this.selectedPembeli = pembeli;
        tampilDaftarSapi();
        tampilDaftarRecordPembayaran();
        setStatusInformation();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Tombol tombol !!
        tombolTambahSapi.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScenePilihSapi.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Pilih Sapi");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                ScenePilihSapi scenePilihSapi = fxmlLoader.getController();
                scenePilihSapi.init(this, selectedPembeli);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tombolHapusSapi.setOnMouseClicked(mouseEvent -> {
            Sapi selected = tableViewDaftarSapiDibeli.getSelectionModel().getSelectedItem();
            MainApp.ManajemenApp.removeSapi(selected);
            refreshTableDaftarPembelian();
        });

        tombolTambahPembayaran.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SceneAddRecordPembayaran.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Tambah Riwayat Pembayaran");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                SceneAddRecordPembayaran sceneRecordPembayaran = fxmlLoader.getController();
                sceneRecordPembayaran.init(selectedPembeli, this);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tombolHapusRiwayat.setOnMouseClicked(mouseEvent -> {
            RecordPembayaran selected = tableViewRiwayatPembayaran.getSelectionModel().getSelectedItem();
            selectedPembeli.removeRiwayatPembayaran(selected);
            refreshTableRecordPembayaran();
            setStatusInformation();
        });

        tableViewRiwayatPembayaran.setOnMouseClicked(mouseEvent -> {
            if (tableViewRiwayatPembayaran.getSelectionModel().getSelectedItem() != null) {
                tombolHapusRiwayat.setDisable(false);
            }
        });

        tableViewDaftarSapiDibeli.setOnMouseClicked(mouseEvent -> {
            if (tableViewDaftarSapiDibeli.getSelectionModel().getSelectedItem() != null) {
                tombolHapusSapi.setDisable(false);
            }
        });

        tombolHapusSapi.setDisable(true);
        tombolHapusRiwayat.setDisable(true);
    }

    void setStatusInformation() {
        labelStatusPembayaran.setText((selectedPembeli.isLunas()? "Lunas" : "Belum Lunas"));
        labelKekuranganPembayaran.setText(String.valueOf(selectedPembeli.kalkulasiSisaPembayaran()));
        labelTotalPembayaran.setText(String.valueOf(selectedPembeli.getJumlahTotalUangDibayarkan()));
        labelTotalPembelian.setText(String.valueOf(selectedPembeli.getJumlahSapiDibeli()));
    }

    public void tampilDaftarSapi() {
        tableViewDaftarSapiID.setCellValueFactory(new PropertyValueFactory<Sapi, String>("id"));
        tableViewDaftarSapiJenisSapi.setCellValueFactory(new PropertyValueFactory<Sapi, String>("jenisSapi"));
        tableViewDaftarSapiWarna.setCellValueFactory(new PropertyValueFactory<Sapi,String>("warna"));
        tableViewDaftarSapiHargaBeli.setCellValueFactory(new PropertyValueFactory<Sapi, String>("hargaBeli"));
        tableViewDaftarSapiHargaJual.setCellValueFactory(new PropertyValueFactory<Sapi, String>("hargaJual"));
        tableViewDaftarSapiTanggalAntar.setCellValueFactory(new PropertyValueFactory<Sapi, String>("tanggalAntar"));

        ObservableList<Sapi> daftarSapiDibeli = FXCollections.observableArrayList(selectedPembeli.getDaftarSapiDibeli());
        tableViewDaftarSapiDibeli.setItems(daftarSapiDibeli);
    }

    public void refreshTableDaftarPembelian() {
        ObservableList<Sapi> daftarSapiDibeli = FXCollections.observableArrayList(selectedPembeli.getDaftarSapiDibeli());
        tableViewDaftarSapiDibeli.refresh();
        tableViewDaftarSapiDibeli.setItems(daftarSapiDibeli);
    }

    public void tampilDaftarRecordPembayaran() {
        tableRiwayatPembayaranNomor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RecordPembayaran, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<RecordPembayaran, Number> recordPembayaranNumberCellDataFeatures) {
                return new SimpleIntegerProperty(recordPembayaranNumberCellDataFeatures.getValue().nomor());
            }
        });

        tableRiwayatPembayaranJumlahUang.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RecordPembayaran, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<RecordPembayaran, Number> recordPembayaranNumberCellDataFeatures) {
                return new SimpleLongProperty(recordPembayaranNumberCellDataFeatures.getValue().jumlahUang());
            }
        });

        tableRiwayatPembayaranTanggal.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RecordPembayaran, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RecordPembayaran, String> recordPembayaranStringCellDataFeatures) {
                return new SimpleStringProperty(recordPembayaranStringCellDataFeatures.getValue().tanggal());
            }
        });

        tableRiwayatPembayaranKeterangan.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RecordPembayaran, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RecordPembayaran, String> recordPembayaranStringCellDataFeatures) {
                return new SimpleStringProperty(recordPembayaranStringCellDataFeatures.getValue().keterangan());
            }
        });

        ObservableList<RecordPembayaran> daftarRiwayatPembayaran = FXCollections.observableArrayList(selectedPembeli.getRiwayatPembayaran());
        tableViewRiwayatPembayaran.setItems(daftarRiwayatPembayaran);
    }

    public void refreshTableRecordPembayaran() {
        ObservableList<RecordPembayaran> daftarRiwayatPembayaran = FXCollections.observableArrayList(selectedPembeli.getRiwayatPembayaran());
        tableViewRiwayatPembayaran.refresh();
        tableViewRiwayatPembayaran.setItems(daftarRiwayatPembayaran);
    }
}
