package com.dimas.core;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class Sapi {
    private @Getter @Setter int id;
    private @Getter @Setter BigDecimal hargaBeli;
    private @Getter @Setter BigDecimal hargaJual;
    private @Getter @Setter BigDecimal biayaMakan;

    private @Getter @Setter String jenisSapi;
    private @Getter @Setter String deskripsi;
    private @Getter @Setter String keterangan;
    private @Getter @Setter String TAG;
    private @Getter @Setter String tanggalPembelian;
    private @Getter @Setter Pembeli pembeli;

    private @Getter @Setter boolean laku;
    private @Getter @Setter String tanggalAntar;

    private static List<Sapi> listSapi = new ArrayList<>();
    private static int countId = 0;

    Sapi(String jenisSapi,
         BigDecimal hargaBeli,
         String deskripsi
    ){
        this.deskripsi = deskripsi;
        this.hargaBeli = hargaBeli;
        this.jenisSapi = jenisSapi;
        id = ++countId;
        listSapi.add(this);
    }

    public static List<Sapi> getListSapi() {
        return listSapi;
    }

    // Method
    void perkiraanHargaJual(BigDecimal targetKeuntungan){

    }
}
