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

    private int idxPrePridanie = 1;

    private long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    private int Seconds =0;
    private boolean SpusteneStopky;
    private boolean PauznuteStopky;

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
