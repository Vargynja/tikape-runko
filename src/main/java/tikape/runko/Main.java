package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AlueDao;
import tikape.runko.database.KeskusteluDao;
import tikape.runko.database.ViestiDao;

public class Main {

    public static void main(String[] args) throws Exception {
        String db = "jdbc:sqlite:opiskelijat.db";
        AlueDao alueDao = new AlueDao(db);
        KeskusteluDao keskusteluDao = new KeskusteluDao(db);
        ViestiDao viestiDao = new ViestiDao(db);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        
    }
}
