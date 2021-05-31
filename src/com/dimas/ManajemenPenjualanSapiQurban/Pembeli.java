package com.dimas.ManajemenPenjualanSapiQurban;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pembeli implements Serializable {
    private @Getter final int id;
    private @Getter @Setter String nama;
    private @Getter @Setter String alamat;
    private @Getter @Setter String keterangan;
    private @Getter @Setter String nomorHP;
    private @Getter final List<Sapi> daftarSapiDibeli = new ArrayList<>();
    private @Getter final List<RecordPembayaran> riwayatPembayaran = new ArrayList<>();

    Pembeli(int id, String nama, String alamat, String nomorHP, String keterangan){
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.nomorHP = nomorHP;
        this.keterangan = keterangan;
    }

    public boolean beli(Sapi sapi, String catatanPembeli, Long hargaLaku, String tanggalAntar) {
        if (sapi.isLaku()) return false;

        daftarSapiDibeli.add(sapi);
        sapi.setLaku(true);
        sapi.setPembeli(this);
        sapi.setCatatanPembeli(catatanPembeli);
        sapi.setHargaJual(hargaLaku);
        sapi.setTanggalAntar(tanggalAntar);
        return true;
    }

    public int getJumlahSapiDibeli(){
        return this.daftarSapiDibeli.size();
    }

    public void transaksiPembayaran(Long nominalPembayaran, String keterangan, String tanggal) {
        RecordPembayaran kuitansi = new RecordPembayaran(
                getJumlahKaliPembayaran() + 1,
                this.nama,
                nominalPembayaran,
                keterangan,
                tanggal
        );

        riwayatPembayaran.add(kuitansi);
    }

    public int getJumlahKaliPembayaran() {
        return riwayatPembayaran.size();
    }

    public Long kalkulasiSisaPembayaran(){
        return totalHargaPembelian() - getJumlahTotalUangDibayarkan();
    }

    public Long totalHargaPembelian(){
        Long total = 0L;

        if (daftarSapiDibeli.isEmpty()) return total;

        for (Sapi sapiDibeli : daftarSapiDibeli) {
            total += sapiDibeli.getHargaJual();
        }

        return total;
    }

    public boolean isLunas() {
        return kalkulasiSisaPembayaran().intValue() == 0;
    }

    public Long getJumlahTotalUangDibayarkan() {

        Long temp = 0L;

        for (RecordPembayaran riwayat : riwayatPembayaran) {
            temp += riwayat.jumlahUang();
        }

        return temp;
    }

    public void removeRiwayatPembayaran(RecordPembayaran recordPembayaran) {
        riwayatPembayaran.remove(recordPembayaran);
    }
}
