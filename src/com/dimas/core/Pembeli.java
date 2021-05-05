package com.dimas.core;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

class Pembeli {
    private int id;

    private String nama;
    private String alamat;
    private String keterangan; // Memberikan keterangan dan rincian tentang client
    private String nomorHP;

    private List<Sapi> daftarSapiDibeli;
    private List<RecordPembayaran> riwayatPembayaran;
    private boolean lunas;

    // Static method
    private static int countId = 0;
    private static @Getter List<Pembeli> listPembeli = new ArrayList<>();

    Pembeli(String nama,
            String alamat,
            String alamatKota,
            String nomorHP
    ){
        this.nama = nama;
        this.alamat = alamat;
        this.nomorHP = nomorHP;
        id = ++countId;
        listPembeli.add(this);
    }

    public boolean beli(Sapi sapi, String keterangan){
        daftarSapiDibeli.add(sapi);
        sapi.setLaku(true);
        sapi.setPembeli(this);
        sapi.setKeterangan(keterangan);
        return true;
    }
}
