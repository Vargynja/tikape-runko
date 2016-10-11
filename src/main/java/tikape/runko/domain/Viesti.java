package tikape.runko.domain;

import java.sql.Date;

public class Viesti {

    private int id;
    private String viesti;
    private String nimimerkki;
    private Date date;
    private int keskustelu;

    public Viesti(int id, String viesti, String nimimerkki, Date date, int keskustelu) {
        this.id = id;
        this.viesti = viesti;
        this.nimimerkki = nimimerkki;
        this.date = date;
        this.keskustelu = keskustelu;
    }

    public int getKeskustelu() {
        return keskustelu;
    }

    public void setKeskustelu(int keskustelu) {
        this.keskustelu = keskustelu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getViesti() {
        return viesti;
    }

    public void setViesti(String viesti) {
        this.viesti = viesti;
    }

    public String getNimimerkki() {
        return nimimerkki;
    }

    public void setNimimerkki(String nimimerkki) {
        this.nimimerkki = nimimerkki;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
