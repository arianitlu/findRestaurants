package com.example.pluscomputers.publictoilet2;

public class ListLocationDetails {

    private double pastertia;
    private double ndricimi;
    private double madhesia;
    private double ventilimi;
    private double komoditeti;
    private double pajisje;


    public ListLocationDetails(double pastertia, double ndricimi, double madhesia, double ventilimi,
                               double komoditeti, double pajisje) {
        this.pastertia = pastertia;
        this.ndricimi = ndricimi;
        this.madhesia = madhesia;
        this.ventilimi = ventilimi;
        this.komoditeti = komoditeti;
        this.pajisje = pajisje;
    }

    public void setPastertia(double pastertia) {
        this.pastertia = pastertia;
    }

    public void setNdricimi(double ndricimi) {
        this.ndricimi = ndricimi;
    }

    public void setMadhesia(double madhesia) {
        this.madhesia = madhesia;
    }

    public void setVentilimi(double ventilimi) {
        this.ventilimi = ventilimi;
    }

    public void setKomoditeti(double komoditeti) {
        this.komoditeti = komoditeti;
    }

    public void setPajisje(double pajisje) {
        this.pajisje = pajisje;
    }

    public double getPastertia() {
        return pastertia;
    }

    public double getNdricimi() {
        return ndricimi;
    }

    public double getMadhesia() {
        return madhesia;
    }

    public double getVentilimi() {
        return ventilimi;
    }

    public double getKomoditeti() {
        return komoditeti;
    }

    public double getPajisje() {
        return pajisje;
    }

}