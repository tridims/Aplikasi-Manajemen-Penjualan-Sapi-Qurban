package com.dimas.GUIApp;

import com.dimas.ManajemenPenjualanSapiQurban.ManagementApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static ManagementApp ManajemenApp = new ManagementApp(150000L, 50000L, 150000L);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setTitle("Aplikasi Manajemen Penjualan Sapi Qurban");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
