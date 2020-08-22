package ba.unsa.etf.rpr.projekat.Zaposlenik;

public class Zaposlenik {
    private Integer id;
	private String ime;
    private String prezime;
    private String email;
    private String brojTelefona;
    private String datumZaposlenja;
    private String posaoId;
    private float plata;
    private float dodatakNaPlatu;
    private Integer odjelId;

    public Zaposlenik(Integer id, String ime, String prezime, String email, String brojTelefona, String datumZaposlenja, String posaoId, float plata, float dodatakNaPlatu, Integer odjelId) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.datumZaposlenja = datumZaposlenja;
        this.posaoId = posaoId;
        this.plata = plata;
        this.dodatakNaPlatu = dodatakNaPlatu;
        this.odjelId = odjelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(String datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public String getPosaoId() {
        return posaoId;
    }

    public void setPosaoId(String posaoId) {
        this.posaoId = posaoId;
    }

    public float getPlata() {
        return plata;
    }

    public void setPlata(float plata) {
        this.plata = plata;
    }

    public float getDodatakNaPlatu() {
        return dodatakNaPlatu;
    }

    public void setDodatakNaPlatu(float dodatakNaPlatu) {
        this.dodatakNaPlatu = dodatakNaPlatu;
    }

    public Integer getOdjelId() {
        return odjelId;
    }

    public void setOdjelId(Integer odjelId) {
        this.odjelId = odjelId;
    }
    @Override
    public String toString() {
        return ime;
    }

}
