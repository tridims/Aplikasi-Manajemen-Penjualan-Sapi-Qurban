package com.dimas.GUIApp.ManajemenSapi;

import com.dimas.GUIApp.MainApp;
import com.dimas.ManajemenPenjualanSapiQurban.Sapi;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ManajemenSapi implements Initializable {
    @FXML private TableView<Sapi> tableView_daftar_sapi;
    @FXML private TableColumn<Sapi, String> kolom_id;
    @FXML private TableColumn<Sapi, String> kolom_jenis_sapi;
    @FXML private TableColumn<Sapi, String> kolom_warna;
    @FXML private TableColumn<Sapi, String> kolom_harga_beli;
    @FXML private TableColumn<Sapi, String> kolom_harga_jual;
    @FXML private TableColumn<Sapi, String> kolom_kalkulasi_harga;
    @FXML private TableColumn<Sapi, String> kolom_pembeli;
    @FXML private TableColumn<Sapi, String> kolom_tanggal_antar;
    @FXML private JFXButton button_tambah_sapi;
    @FXML private JFXButton button_lihat_detail;
    @FXML private JFXButton button_hapus_sapi;
    @FXML private JFXButton button_edit_data;
    @FXML private JFXButton button_refresh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tampilDataKeTabel();
        button_hapus_sapi.setDisable(true);
        button_edit_data.setDisable(true);
        button_lihat_detail.setDisable(true);

        tableView_daftar_sapi.setOnMouseClicked(mouseEvent -> {
            if (tableView_daftar_sapi.getSelectionModel().getSelectedItem() != null) {
                button_hapus_sapi.setDisable(false);
                button_edit_data.setDisable(false);
                button_lihat_detail.setDisable(false);
            }
        });

        button_tambah_sapi.setOnMouseClicked(mouseEvent -> {
            // Munculkan window formulir tambah sapi
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormTambahSapi.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                FormTambahSapi formTambahSapi = fxmlLoader.getController();
                formTambahSapi.init(this);
                stage.setTitle("Form Tambah Sapi");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        });

        button_lihat_detail.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SceneShowDetailSapi.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Detail Sapi");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                SceneShowDetailSapi viewer = fxmlLoader.getController();
                viewer.init(tableView_daftar_sapi.getSelectionModel().getSelectedItem(), this);
                stage.show();
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        });

        button_hapus_sapi.setOnMouseClicked(mouseEvent -> {
            Sapi sapi = tableView_daftar_sapi.getSelectionModel().getSelectedItem();
            MainApp.ManajemenApp.removeSapi(sapi);
            refreshTable();
        });

        button_edit_data.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormEditDataSapi.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Form Edit Data Sapi");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                FormEditDataSapi editor = fxmlLoader.getController();
                editor.initData(tableView_daftar_sapi.getSelectionModel().getSelectedItem(), this);
                stage.show();

            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        });

        button_refresh.setOnMouseClicked(mouseEvent -> refreshTable());
    }

    public void tampilDataKeTabel() {
        kolom_id.setCellValueFactory(new PropertyValueFactory<Sapi, String>("id"));
        kolom_jenis_sapi.setCellValueFactory(new PropertyValueFactory<Sapi, String>("jenisSapi"));
        kolom_warna.setCellValueFactory(new PropertyValueFactory<Sapi, String>("warna"));
        kolom_harga_jual.setCellValueFactory(new PropertyValueFactory<Sapi, String>("hargaJual"));
        kolom_harga_beli.setCellValueFactory(new PropertyValueFactory<Sapi, String>("hargaBeli"));
        kolom_kalkulasi_harga.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sapi, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sapi, String> sapiStringCellDataFeatures) {
                return new SimpleStringProperty(String.valueOf(sapiStringCellDataFeatures.getValue().getHargaJualOptimal(
                        MainApp.ManajemenApp.getTargetKeuntunganPerSapi(),
                        MainApp.ManajemenApp.getBiayaPerawatanPerhari(),
                        MainApp.ManajemenApp.getBiayaPerawatanPerhari()
                )));
            }
        });
        kolom_pembeli.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Sapi, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Sapi, String> sapiStringCellDataFeatures) {
                        return new SimpleStringProperty(
                                (sapiStringCellDataFeatures.getValue().getPembeli() != null) ? sapiStringCellDataFeatures.getValue()
                                        .getPembeli().getNama() : ""
                        );
                    }
                }
        );
        kolom_tanggal_antar.setCellValueFactory(new PropertyValueFactory<Sapi, String>("tanggalAntar"));
        ObservableList<Sapi> dataTabelSapi = FXCollections.observableArrayList(MainApp.ManajemenApp.getDaftarSapi());
        tableView_daftar_sapi.setItems(dataTabelSapi);
    }

    public void refreshTable() {
        ObservableList<Sapi> dataTabelSapi = FXCollections.observableArrayList(MainApp.ManajemenApp.getDaftarSapi());
        tableView_daftar_sapi.refresh();
        tableView_daftar_sapi.setItems(dataTabelSapi);
    }
}
