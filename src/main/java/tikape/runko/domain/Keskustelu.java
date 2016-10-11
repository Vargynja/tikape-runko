package tikape.runko.domain;

public class Keskustelu {

    private int id;
    private String nimi;
    private int alue;

    public Keskustelu(int id, String nimi, int alue) {
        this.id = id;
        this.nimi = nimi;
        this.alue = alue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getAlue() {
        return alue;
    }

    public void setAlue(int alue) {
        this.alue = alue;
    }

}
