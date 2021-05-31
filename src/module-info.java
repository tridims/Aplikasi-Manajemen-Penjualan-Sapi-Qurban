module Tugas.Final.PBO {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.jfoenix;

    opens com.dimas.ManajemenPenjualanSapiQurban;
    opens com.dimas.GUIApp.ManajemenPembeli;
    opens com.dimas.GUIApp.ManajemenSapi;
    opens com.dimas.GUIApp;
}