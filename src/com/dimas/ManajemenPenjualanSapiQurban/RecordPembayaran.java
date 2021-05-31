package com.dimas.ManajemenPenjualanSapiQurban;

import java.io.Serializable;

public record RecordPembayaran (
        int nomor,
        String pembeli,
        Long jumlahUang,
        String keterangan,
        String tanggal
) implements Serializable{}
