package com.dimas.ManajemenPenjualanSapiQurban;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManagementApp implements Serializable {

    private @Getter final List<Sapi> daftarSapi = new ArrayList<>();
    private @Getter final List<Pembeli> daftarPembeli = new ArrayList<>();
    private @Getter final List<Pekerja> listPekerja = new ArrayList<>();

    // variable yang dapat diatur
    private @Getter @Setter Long targetKeuntunganPerSapi;
    private @Getter @Setter Long perkiraanBiayaPerawatan;
    private @Getter @Setter Long biayaPengiriman;

    private int countIdPembeli = 0;
    private int countIdSapi = 0;

    public ManagementApp(Long targetKeuntunganPerSapi, Long perkiraanBiayaPerawatan, Long biayaPengiriman){
        this.targetKeuntunganPerSapi = targetKeuntunganPerSapi;
        this.perkiraanBiayaPerawatan = perkiraanBiayaPerawatan;
        this.biayaPengiriman = biayaPengiriman;

        // Set default Object for Demonstration
        addSapi("Limousin", "Merah", 18000000L, "Sapinya besar", "12 Maret 2021");
        addSapi("Brahma", "Merah", 18000000L, "Sapinya besar", "12 Maret 2021");
        addSapi("PO", "Putih", 18000000L, "Sapinya besar", "12 Maret 2021");
        addPembeli("Pak S", "Malang", "878787", "Sudah lama langganan");
        addPembeli("Pak Y", "Batu", "975643", "Pembeli baru");
        getPembeli(0).beli(getDaftarSapi().get(0), "Testing", 20000000L, "3 Desember 2021");
    }

    public void addPembeli(String nama, String alamat, String nomorHp, String keterangan){
        daftarPembeli.add(new Pembeli(countIdPembeli++, nama, alamat, nomorHp, keterangan));
    }

    public void removePembeli(String idOrNama) {
        Pembeli tempPembeli = null;
        try {
            int id = Integer.parseInt(idOrNama);
            for (Pembeli pembeli : daftarPembeli) {
                if (pembeli.getId() == id) {
                    tempPembeli = pembeli;
                    break;
                }
            }
        } catch (Exception e) {
            for (Pembeli pembeli : daftarPembeli) {
                if (pembeli.getNama().equals(idOrNama)){
                    tempPembeli = pembeli;
                    break;
                }
            }
        }

        assert tempPembeli != null;
        removePembeliAndAllData(tempPembeli);
    }

    private void removePembeliAndAllData(Pembeli tempPembeli) {
        if (!tempPembeli.getDaftarSapiDibeli().isEmpty()) {
            for (Sapi sapi : tempPembeli.getDaftarSapiDibeli()) {
                sapi.setLaku(false);
                sapi.setPembeli(null);
                sapi.setTanggalAntar(null);
                sapi.setHargaJual(null);
            }
        }
        daftarPembeli.remove(tempPembeli);
    }

    public void removePembeli(Pembeli pembeli) {
        removePembeliAndAllData(pembeli);
    }

    public List<Pembeli> searchPembeli(String nama){
        nama = nama.toLowerCase();
        List<Pembeli> result = new ArrayList<>();
        for (Pembeli pembeli : daftarPembeli){
            if (pembeli.getNama().toLowerCase().contains(nama)) result.add(pembeli);
        }
        return result;
    }

    public Pembeli getPembeli(int id) {
        return daftarPembeli.stream().filter(pembeli -> pembeli.getId()==id).findFirst().orElse(null);
    }

    public int getJumlahPembeli() {
        return daftarPembeli.size();
    }

    public int getJumlahPembeliBelumLunas() {
        return getDaftarPembeliBelumLunas().size();
    }

    public int getJumlahPembeliLunas() {
        return getDaftarPembeliSudahLunas().size();
    }

    public List<Pembeli> getDaftarPembeliBelumLunas() {
        ArrayList<Pembeli> listPembeliBelumLunas = new ArrayList<>();
        daftarPembeli.stream().filter(pembeli -> !pembeli.isLunas()).forEach(listPembeliBelumLunas::add);
        return listPembeliBelumLunas;
    }

    public List<Pembeli> getDaftarPembeliSudahLunas() {
        ArrayList<Pembeli> listPembeliLunas = new ArrayList<>();
        daftarPembeli.stream().filter(Pembeli::isLunas).forEach(listPembeliLunas::add);
        return listPembeliLunas;
    }

    public void addSapi(String jenisSapi, String warna, Long hargaBeli, String deskripsi, String tanggalPembelian){
        daftarSapi.add(new Sapi(countIdSapi++, hargaBeli, jenisSapi, deskripsi, warna, tanggalPembelian));
        System.out.println("Berhasil tambah sapi !");
    }

    public void removeSapi(int id) {
        Sapi temp = null;
        int index;
        for (Sapi sapi : daftarSapi){
            if (sapi.getId() == id) {
                temp = sapi;
                break;
            }
        }

        if (temp == null) {
            return;
        }

        removeSapi(temp);
    }

    public void removeSapi(Sapi sapi) {
        if (sapi.isLaku()) {
            Pembeli pembeli = sapi.getPembeli();
            pembeli.getDaftarSapiDibeli().remove(sapi);
        }
        daftarSapi.remove(sapi);
    }

    public List<Sapi> searchSapi(String jenisSapi){
        jenisSapi = jenisSapi.toLowerCase();
        List<Sapi> result = new ArrayList<>();
        for (Sapi sapi: daftarSapi){
            if (sapi.getJenisSapi().toLowerCase().contains(jenisSapi)) result.add(sapi);
        }
        return result;
    }

    public Sapi searchSapi(int id) {
        return daftarSapi.stream().filter(sapi -> sapi.getId() == id).findFirst().orElse(null);
    }

    public int getJumlahSapi() {
        return daftarSapi.size();
    }

    public int getJumlahSapiLaku() {
        int count = 0;
        for (Sapi sapi : daftarSapi) {
            if (sapi.isLaku()) count++;
        }
        return count;
    }

    public int getJumlahSapiBelumLaku() {
        return getJumlahSapi()-getJumlahSapiLaku();
    }

    public List<Sapi> getDaftarSapiBelumlaku() {
        List<Sapi> listSapiBelumLaku = new ArrayList<>();
        daftarSapi.stream().filter(sapi -> !sapi.isLaku()).forEach(listSapiBelumLaku::add);
        return listSapiBelumLaku;
    }

    public <T extends HewanQurban> Long hitungHargajual(T hewan) {
        return hewan.getHargaBeli() + targetKeuntunganPerSapi + biayaPengiriman + perkiraanBiayaPerawatan;
    }
}