package com.dimas.ManajemenPenjualanSapiQurban;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ManagementAppExternalSaveFileManager {
    private ManagementApp managementApp;

    public final String SAVE_PATH = "SaveData//";
    public final String SAVE_FILE_NAME = "ManagementApp.save";

    public ManagementAppExternalSaveFileManager() { }

    public ManagementApp initManagementAppObject() {
        // Check directory
        File pathAsFile = new File(SAVE_PATH);
        if (!Files.exists(Paths.get(SAVE_PATH))) {
            pathAsFile.mkdir();
        }

        // Check file if doesnot exist then create new ManagementApp object
        File file = new File(SAVE_PATH + SAVE_FILE_NAME);
        if (!file.exists()) {
            managementApp = new ManagementApp(0L, 0L, 0L);
            return managementApp;
        }

        // If file exist, then read the object from there
        try {
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fi);
            managementApp = (ManagementApp) oi.readObject();
            fi.close();
            oi.close();

            return managementApp;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveApp() {
        // Check directory
        File pathAsFile = new File(SAVE_PATH);
        if (!Files.exists(Paths.get(SAVE_PATH))) {
            pathAsFile.mkdir();
        }

        try {
            File file = new File(SAVE_PATH + SAVE_FILE_NAME);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(managementApp);
            o.close();
            f.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
