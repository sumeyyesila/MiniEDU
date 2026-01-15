package org.example;

public class Ders {
    private int id;
    private String dersAdi;
    private String aciklama;
    private int egitmenId;

    public Ders(int id, String dersAdi, String aciklama, int egitmenId) {
        this.id = id;
        this.dersAdi = dersAdi;
        this.aciklama = aciklama;
        this.egitmenId = egitmenId;
    }

    // Getter Metotları
    public int getId() { return id; }
    public String getDersAdi() { return dersAdi; }
    public String getAciklama() { return aciklama; }
    public int getEgitmenId() { return egitmenId; }
}
