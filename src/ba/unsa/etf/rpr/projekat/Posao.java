package ba.unsa.etf.rpr.projekat;

public class Posao {
    private Integer posaoId;
    private String nazivPosla;
    private float minPlata;
    private float maxPlata;

    public Posao(Integer posaoId, String nazivPosla, float minPlata, float maxPlata) {
        this.posaoId = posaoId;
        this.nazivPosla = nazivPosla;
        this.minPlata = minPlata;
        this.maxPlata = maxPlata;
    }

    public Integer getPosaoId() {
        return posaoId;
    }

    public void setPosaoId(Integer posaoId) {
        this.posaoId = posaoId;
    }

    public String getNazivPosla() {
        return nazivPosla;
    }

    public void setNazivPosla(String nazivPosla) {
        this.nazivPosla = nazivPosla;
    }

    public float getMinPlata() {
        return minPlata;
    }

    public void setMinPlata(float minPlata) {
        this.minPlata = minPlata;
    }

    public float getMaxPlata() {
        return maxPlata;
    }

    public void setMaxPlata(float maxPlata) {
        this.maxPlata = maxPlata;
    }
}
