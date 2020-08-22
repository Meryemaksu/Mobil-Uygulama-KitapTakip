package com.example.ktphanem;

public class Kitapekle {
    private String kitapadi;
    private String kitapid;
    private String yazaradi;
    private String rafyeri;
    private int fiyat;
    private int adett;

    public Kitapekle() {
    }

    public Kitapekle( String kitapadi, String yazaradi, String rafyeri, int fiyat, int adett) {
        this.kitapadi = kitapadi;
        this.yazaradi = yazaradi;
        this.rafyeri = rafyeri;
        this.fiyat = fiyat;
        this.adett = adett;
    }

    public int getAdett() {
        return adett;
    }

    public void setAdett(int adett) {
        this.adett = adett;
    }

    public String getKitapadi() {
        return kitapadi;
    }

    public String getKitapid() {
        return kitapid;
    }

    public void setKitapid(String kitapid) {
        this.kitapid = kitapid;
    }

    public void setKitapadi(String kitapadi) {
        this.kitapadi = kitapadi;
    }

    public String getYazaradi() {
        return yazaradi;
    }

    public void setYazaradi(String yazaradi) {
        this.yazaradi = yazaradi;
    }

    public String getRafyeri() {
        return rafyeri;
    }

    public void setRafyeri(String rafyeri) {
        this.rafyeri = rafyeri;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }
}
