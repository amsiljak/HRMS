package ba.unsa.etf.rpr.projekat;

public class Odjel {
    private Integer id;
    private String nazivOdjela;
    private Integer menadzerId;
    private String adresa;
    private Integer postnanskiBroj;
    private String grad;

    public Odjel(Integer id, String nazivOdjela, Integer menadzerId, String adresa, Integer postnanskiBroj, String grad) {
        this.id = id;
        this.nazivOdjela = nazivOdjela;
        this.menadzerId = menadzerId;
        this.adresa = adresa;
        this.postnanskiBroj = postnanskiBroj;
        this.grad = grad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazivOdjela() {
        return nazivOdjela;
    }

    public void setNazivOdjela(String nazivOdjela) {
        this.nazivOdjela = nazivOdjela;
    }

    public Integer getMenadzerId() {
        return menadzerId;
    }

    public void setMenadzerId(Integer menadzerId) {
        this.menadzerId = menadzerId;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Integer getPostnanskiBroj() {
        return postnanskiBroj;
    }

    public void setPostnanskiBroj(Integer postnanskiBroj) {
        this.postnanskiBroj = postnanskiBroj;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }
}
