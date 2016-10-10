
package tikape.runko.domain;

import java.sql.Date;


public class Viesti {
    private int id;
    private String viesti;
    private String nimimerkki;
    private Date date;

    public Viesti(int id, String viesti, String nimimerkki, Date date) {
        this.id = id;
        this.viesti = viesti;
        this.nimimerkki = nimimerkki;
        this.date = date;
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
