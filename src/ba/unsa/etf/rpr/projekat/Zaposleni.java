package ba.unsa.etf.rpr.projekat;

public class Zaposleni {
    private Integer id;
	private String ime;
    private String prezime;
    private String email;
    private Integer brojTelefona;
    private String datumZaposlenja;
    private Integer posaoId;
    private float plata;
    private float dodatakNaPlatu;
    private Integer menadzerId;
    private Integer odjelId;

    public Zaposleni(Integer id, String firstName, String lastName, String email, Integer phoneNumber, String hireDate, Integer jobId, float salary, float commissionPct, Integer managerId, Integer departmentId) {
        this.id = id;
        this.ime = firstName;
        this.prezime = lastName;
        this.email = email;
        this.brojTelefona = phoneNumber;
        this.datumZaposlenja = hireDate;
        this.posaoId = jobId;
        this.plata = salary;
        this.dodatakNaPlatu = commissionPct;
        this.menadzerId = managerId;
        this.odjelId = departmentId;
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

    public Integer getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(Integer brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(String datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public Integer getPosaoId() {
        return posaoId;
    }

    public void setPosaoId(Integer posaoId) {
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

    public Integer getMenadzerId() {
        return menadzerId;
    }

    public void setMenadzerId(Integer menadzerId) {
        this.menadzerId = menadzerId;
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
