package ba.unsa.etf.rpr.projekat.Posao;

public class Posao {
    private String id;
    private String nazivPosla;
    private float minPlata;
    private float maxPlata;

    public Posao(String posaoId, String nazivPosla, float minPlata, float maxPlata) {
        this.id = posaoId;
        this.nazivPosla = nazivPosla;
        this.minPlata = minPlata;
        this.maxPlata = maxPlata;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
