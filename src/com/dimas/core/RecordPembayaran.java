package com.dimas.core;

record RecordPembayaran(
        int nomor,
        String pembeli,
        double jumlahUang,
        double tujuanPembayaran,
        String keterangan,
        String tanggal
) {
}
