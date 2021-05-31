package com.dimas.ManajemenPenjualanSapiQurban;

import lombok.Data;

import java.io.Serializable;

@Data
public class Pekerja implements Serializable {
    private String nama;
    private Long gajiPerBulan;

    public Pekerja(String nama, Long gajiPerBulan) {
        this.nama = nama;
        this.gajiPerBulan = gajiPerBulan;
    }
}
