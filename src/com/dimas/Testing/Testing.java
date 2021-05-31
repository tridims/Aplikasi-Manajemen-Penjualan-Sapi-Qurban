package com.dimas.Testing;

import com.dimas.ManajemenPenjualanSapiQurban.RecordPembayaran;

import java.util.ArrayList;
import java.util.List;

public class Testing {
    public static void main(String[] args) {
        RecordPembayaran re = new RecordPembayaran(1, "Saya", 99L, "TEST", "33");
        System.out.println(re.tanggal());
    }
}