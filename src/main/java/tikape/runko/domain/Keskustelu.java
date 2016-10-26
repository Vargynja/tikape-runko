package tikape.runko.domain;

public class Keskustelu {

    private int id;
    private String nimi;
    private int alue;
    private int maara;
    private String time;

    public Keskustelu(int id, String nimi, int alue, int maara, String time) {
        this.id = id;
        this.nimi = nimi;
        this.alue = alue;
        this.maara = maara;
        this.time = time;
    }

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

    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        this.maara = maara;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAlue(int alue) {
        this.alue = alue;
    }

}
