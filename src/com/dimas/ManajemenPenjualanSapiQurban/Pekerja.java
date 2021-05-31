package com.dimas.ManajemenPenjualanSapiQurban;

import lombok.Data;

@Data
public class Pekerja {
    private String nama;
    private Long gajiPerBulan;

    public Pekerja(String nama, Long gajiPerBulan) {
        this.nama = nama;
        this.gajiPerBulan = gajiPerBulan;
    }
}
