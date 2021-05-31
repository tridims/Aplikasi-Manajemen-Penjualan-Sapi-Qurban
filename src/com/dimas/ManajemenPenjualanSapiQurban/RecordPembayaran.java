package com.dimas.ManajemenPenjualanSapiQurban;

public record RecordPembayaran(
        int nomor,
        String pembeli,
        Long jumlahUang,
        String keterangan,
        String tanggal
){}
