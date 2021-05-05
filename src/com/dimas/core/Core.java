package com.dimas.core;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Core {

    private static List<Sapi> daftarSapi = Sapi.getListSapi();
    private static List<Pembeli> daftarPembeli = Pembeli.getListPembeli();
    private static List<Pekerja> listPekerja = new ArrayList<>();

    private HashMap<String, Pembeli> listTAG = new HashMap<>();

    // tanggal dan sebagainya
    private String tanggalHariRayaQurban;

    // variable yang dapat diatur
    private BigInteger keuntunganPerSapi;
    private BigInteger biayaMakanSapi;
    private BigInteger biayaPengiriman;

    // Constructor
    Core(BigInteger keuntunganPerSapi,
         BigInteger biayaMakanSapi,
         BigInteger biayaPengiriman,
         String tanggalHariRayaQurban
    ){
        this.keuntunganPerSapi = keuntunganPerSapi;
        this.biayaMakanSapi = biayaMakanSapi;
        this.biayaPengiriman = biayaPengiriman;
        this.tanggalHariRayaQurban = tanggalHariRayaQurban;
    }
}
