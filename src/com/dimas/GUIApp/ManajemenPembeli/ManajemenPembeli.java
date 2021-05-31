package com.dimas.GUIApp.ManajemenPembeli;

import com.dimas.GUIApp.MainApp;
import com.dimas.ManajemenPenjualanSapiQurban.Pembeli;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleIntegerProperty;
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

// TODO : implementasi cara refresh table ketika selesai transaksi maupun ketika change scene

public class ManajemenPembeli implements Initializable {
    @FXML private TableView<Pembeli> tabelDaftarPembeli;
    @FXML private TableColumn<Pembeli, Number> tableID;
    @FXML private TableColumn<Pembeli, String> tableNama;
    @FXML private TableColumn<Pembeli, String> tableAlamat;
    @FXML private TableColumn<Pembeli, String> tableNomorHP;
    @FXML private TableColumn<Pembeli, Number> tableJumlahSapiDijual;
    @FXML private TableColumn<Pembeli, String> tableCatatan;
    @FXML private JFXButton button_transaksi;
    @FXML private JFXButton button_lihat_detail;
    @FXML private JFXButton button_hapus;
    @FXML private JFXButton button_edit_data;
    @FXML private JFXButton button_tambahPembeli;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // First display the data in the table and make some button unclickable
        tampilDataKeTabel();
        button_transaksi.setDisable(true);
        button_edit_data.setDisable(true);
        button_hapus.setDisable(true);
        button_lihat_detail.setDisable(true);

        // Button function
        button_tambahPembeli.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SceneAddPembeli.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                SceneAddPembeli sceneAddPembeli = fxmlLoader.getController();
                sceneAddPembeli.init(this);
                stage.setTitle("Tambah Pembeli");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        button_transaksi.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SceneTransaksiPembeli.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                SceneTransaksiPembeli sceneTransaksiPembeli = fxmlLoader.getController();
                sceneTransaksiPembeli.init(tabelDaftarPembeli.getSelectionModel().getSelectedItem());
                Stage stage = new Stage();
                stage.setTitle("Transaksi Pembeli");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        button_lihat_detail.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SceneDetailPembeli.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                SceneDetailPembeli sceneDetailPembeli = fxmlLoader.getController();
                sceneDetailPembeli.init(tabelDaftarPembeli.getSelectionModel().getSelectedItem());
                stage.setTitle("Detail Pembeli");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        button_hapus.setOnMouseClicked(mouseEvent -> {
            Pembeli pembeli = tabelDaftarPembeli.getSelectionModel().getSelectedItem();
            MainApp.ManajemenApp.removePembeli(pembeli);
            refreshTable();
        });

        button_edit_data.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SceneEditPembeli.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                SceneEditPembeli sceneEditPembeli = fxmlLoader.getController();
                sceneEditPembeli.init(tabelDaftarPembeli.getSelectionModel().getSelectedItem());
                stage.setTitle("Detail Pembeli");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tabelDaftarPembeli.setOnMouseClicked(mouseEvent -> {
            if (tabelDaftarPembeli.getSelectionModel() != null) {
                button_lihat_detail.setDisable(false);
                button_edit_data.setDisable(false);
                button_hapus.setDisable(false);
                button_transaksi.setDisable(false);
            }
        });
    }

    public void tampilDataKeTabel() {
        tableID.setCellValueFactory(new PropertyValueFactory<Pembeli, Number>("id"));
        tableNama.setCellValueFactory(new PropertyValueFactory<Pembeli, String>("nama"));
        tableAlamat.setCellValueFactory(new PropertyValueFactory<Pembeli, String>("alamat"));
        tableNomorHP.setCellValueFactory(new PropertyValueFactory<Pembeli, String>("nomorHP"));
        tableCatatan.setCellValueFactory(new PropertyValueFactory<Pembeli, String>("keterangan"));
        tableJumlahSapiDijual.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pembeli, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Pembeli, Number> pembeliNumberCellDataFeatures) {
                return new SimpleIntegerProperty(pembeliNumberCellDataFeatures.getValue().getJumlahSapiDibeli());
            }
        });
        ObservableList<Pembeli> dataTabelPembeli = FXCollections.observableArrayList(MainApp.ManajemenApp.getDaftarPembeli());
        tabelDaftarPembeli.setItems(dataTabelPembeli);
    }

    public void refreshTable() {
        ObservableList<Pembeli> dataTabelPembeli = FXCollections.observableArrayList(MainApp.ManajemenApp.getDaftarPembeli());
        tabelDaftarPembeli.refresh();
        tabelDaftarPembeli.setItems(dataTabelPembeli);
    }
}
