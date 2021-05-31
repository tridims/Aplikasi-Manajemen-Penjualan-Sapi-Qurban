package com.dimas.Testing;

import com.dimas.ManajemenPenjualanSapiQurban.ManagementApp;
import com.dimas.ManajemenPenjualanSapiQurban.ManagementAppExternalSaveFileManager;

public class Testing {
    public static void main(String[] args) {
        ManagementAppExternalSaveFileManager saveFileManager = new ManagementAppExternalSaveFileManager();
        ManagementApp managementApp = saveFileManager.initManagementAppObject();

        managementApp.getDaftarPembeli().forEach(pembeli -> {
            System.out.println(pembeli.getNama());
        });

        saveFileManager.saveApp();
    }
}