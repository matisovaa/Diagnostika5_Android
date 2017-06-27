package sk.upjs.ics.diagnostika5;

import java.io.Serializable;
import java.util.Date;

public class Zaznam implements Serializable {

    private Integer _id;
    private String meno;
    private Date datumACas;
    private String poznamka;
    private int[] hodnoty = new int[25];

    private String[] hodnotyPreGridView = null;
    private String[] mapaPreGridView = null;

    private int idxPrePridanie = 1;

    private long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    private int Seconds =0;
    private boolean SpusteneStopky;
    private boolean PauznuteStopky;

    private int stredovaLinia = -1;
    private  double hf = -1;
    private  double lr = -1;
    private  double jinJang = -1;

    public long getMillisecondTime() {
        return MillisecondTime;
    }

    public void setMillisecondTime(long millisecondTime) {
        MillisecondTime = millisecondTime;
    }

    public long getStartTime() {
        return StartTime;
    }

    public void setStartTime(long startTime) {
        StartTime = startTime;
    }

    public long getTimeBuff() {
        return TimeBuff;
    }

    public void setTimeBuff(long timeBuff) {
        TimeBuff = timeBuff;
    }

    public long getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(long updateTime) {
        UpdateTime = updateTime;
    }

    public int getSeconds() {
        return Seconds;
    }

    public void setSeconds(int seconds) {
        Seconds = seconds;
    }

    public boolean isSpusteneStopky() {
        return SpusteneStopky;
    }

    public void setSpusteneStopky(boolean spusteneStopky) {
        SpusteneStopky = spusteneStopky;
    }

    public boolean isPauznuteStopky() {
        return PauznuteStopky;
    }

    public void setPauznuteStopky(boolean pauznuteStopky) {
        PauznuteStopky = pauznuteStopky;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public void setDatumACas(Date datumACas) {
        this.datumACas = datumACas;
    }

    public void setPoznamka(String poznamka) {
        this.poznamka = poznamka;
    }

    public void setHodnotu(int idx, int hodnota) {
        if (idx<=0 || idx<25){
            this.hodnoty[idx] = hodnota;
        }
    }

    public Integer get_id() {
        return _id;
    }

    public String getMeno() {
        return meno;
    }

    public Date getDatumACas() {
        return datumACas;
    }

    public String getPoznamka() {
        return poznamka;
    }

    public int[] getHodnoty() {
        return hodnoty.clone();
    }

    public String[] getHodnotyPreGridView(){

            hodnotyPreGridView = new String[28];

            hodnotyPreGridView[0] = "HL";
            hodnotyPreGridView[1] = "HR";
            hodnotyPreGridView[2] = "FL";
            hodnotyPreGridView[3] = "FR";

            int idxPreZapis=4;
            for (int i = 1; i<=6; i++){
                for (int j=i;j<25;j=j+6){
                    if(hodnoty[j]==0){
                        hodnotyPreGridView[idxPreZapis] = "";
                        idxPreZapis++;
                    }else{
                        hodnotyPreGridView[idxPreZapis] = String.valueOf(hodnoty[j]);
                        idxPreZapis++;
                    }
                }
            }

            return hodnotyPreGridView.clone();
    }

    public String[] getMapaPreGridView() {
        if(mapaPreGridView != null) {
            return mapaPreGridView;
        }else{
            mapaPreGridView = new String[16];

            mapaPreGridView[0] = "";
            mapaPreGridView[1] = "L";
            mapaPreGridView[2] = "C";
            mapaPreGridView[3] = "R";

            mapaPreGridView[4] = "H";
            mapaPreGridView[5] = String.valueOf(-(Math.round(suma(1,3)/3.0)-Math.round(suma(4,6)/3.0)));
            mapaPreGridView[6] = String.valueOf(-(Math.round((suma(1,3)+suma(7,9))/6.0)
                    -Math.round((suma(4,6)+suma(10,12))/6.0)));
            mapaPreGridView[7] = String.valueOf(-(Math.round((suma(7,9)/3.0))-Math.round((suma(10,12)/3.0))));

            mapaPreGridView[8] = "C";
            mapaPreGridView[9] = String.valueOf(-(Math.round((suma(1,3)+suma(13,15))/6.0)
                    -Math.round((suma(4,6)+suma(16,18))/6.0)));
            mapaPreGridView[10] = String.valueOf(-(Math.round((suma(1,3)+suma(7,9)+suma(13,15)+suma(19,21))/12.0)
                    -Math.round((suma(4,6)+suma(10,12)+suma(16,18)+suma(22,24))/12.0)));
            mapaPreGridView[11] = String.valueOf(-(Math.round((suma(7,9)+suma(19,21))/6.0)
                    -Math.round((suma(10,12)+suma(22,24))/6.0)));

            mapaPreGridView[12] = "F";
            mapaPreGridView[13] = String.valueOf(-(Math.round(suma(13,15)/3.0)-Math.round(suma(16,18)/3.0)));
            mapaPreGridView[14] = String.valueOf(-(Math.round((suma(13,15)+suma(19,21))/6.0)
                    -Math.round((suma(16,18)+suma(22,24))/6.0)));
            mapaPreGridView[15] = String.valueOf(-(Math.round(suma(19,21)/3.0)-Math.round(suma(22,24)/3.0)));

            return mapaPreGridView;
        }
    }

    public int getStredovaLinia() {
        if(stredovaLinia != -1){
            return stredovaLinia;
        }else{
            return (int) Math.abs(Math.round(suma(1,24)/24.0));
        }
    }

    public double getHf() {
        if(hf != -1){
            return hf;
        }else{
            return (Math.round((suma(1,12)/((double)suma(13,24)))*100))/100.0;
        }
    }

    public double getLr() {
        if(lr != -1){
            return lr;
        }else{
            return (Math.round(((suma(1,6)+suma(13,18))
                    /((double)(suma(7,12)+suma(19,24))))*100))/100.0;
        }
    }

    public double getJinJang() {
        if(jinJang != -1){
            return jinJang;
        }else {
            return (Math.round(((suma(1,3)+suma(7,9)+suma(13,15)+suma(19,21))
                    /((double)(suma(4,6)+suma(10,12)+suma(16,18)+suma(22,24))))*100))/100.0;
        }
    }

    private int suma (int idxOdVHodnotach, int idxDoVHodnotach){
        int s = 0;
        for (int i = idxOdVHodnotach; i<=idxDoVHodnotach;i++){
            s += hodnoty[i];
        }
        return s;
    }

    public void pridajHodnotu(int hodnota){
        if(idxPrePridanie<=24 && hodnota>0){
            hodnoty[idxPrePridanie]=hodnota;
            idxPrePridanie++;
        }
    }
    public void odoberHodnotu(){
        if(idxPrePridanie>0){
            hodnoty[idxPrePridanie-1]=0;
            idxPrePridanie--;
        }
    }

}