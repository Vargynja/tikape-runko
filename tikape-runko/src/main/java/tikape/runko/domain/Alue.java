package tikape.runko.domain;

import java.time.LocalDateTime;

public class Alue {

    private int id;
    private String nimi;
    private int viesteja;
    private String time;
    
    public Alue(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public Alue(int id, String nimi, int viesteja, String time) {
        this.id = id;
        this.nimi = nimi;
        this.viesteja = viesteja;
        this.time = time;
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

    public int getViesteja() {
        return viesteja;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setViesteja(int viesteja) {
        this.viesteja = viesteja;
    }
      
    
}
