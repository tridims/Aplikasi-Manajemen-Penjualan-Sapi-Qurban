package com.dimas.ManajemenPenjualanSapiQurban;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Sapi extends HewanQurban implements Serializable {

    private @Getter @Setter String jenisSapi;

    Sapi(int id, Long hargaBeli, String jenisSapi, String deskripsi, String warna, String tanggalPembelian) {
        super(id, hargaBeli, deskripsi, warna, tanggalPembelian);
        this.jenisSapi = jenisSapi;
    }
}
