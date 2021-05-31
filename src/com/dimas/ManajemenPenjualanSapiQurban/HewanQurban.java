package com.dimas.ManajemenPenjualanSapiQurban;

import lombok.Getter;
import lombok.Setter;

public class HewanQurban {
    private @Getter @Setter int id;
    private @Getter @Setter Long hargaBeli;
    private @Getter @Setter String deskripsi;
    private @Getter @Setter String warna;

    // BELUM IMPLEMENTASI
    private @Getter @Setter String tanggalPembelian;
    private @Getter @Setter Long hargaJual;
    private @Getter @Setter Pembeli pembeli;
    private @Getter @Setter boolean laku;
    private @Getter @Setter String tanggalAntar;
    private @Getter @Setter String catatanPembeli;

    public HewanQurban(int id, Long hargaBeli, String deskripsi, String warna, String tanggalPembelian) {
        this.id = id;
        this.hargaBeli = hargaBeli;
        this.deskripsi = deskripsi;
        this.warna = warna;
        this.tanggalPembelian = tanggalPembelian;
    }

    public Long getHargaJualOptimal(Long targetKeuntungan, Long biayaPerawatanTotal, Long biayaPengiriman){
        return hargaBeli + targetKeuntungan + biayaPengiriman + biayaPerawatanTotal;
    }
}
