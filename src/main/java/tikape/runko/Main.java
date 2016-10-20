package tikape.runko;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AlueDao;
import tikape.runko.database.KeskusteluDao;
import tikape.runko.database.ViestiDao;
import tikape.runko.domain.Alue;

public class Main {

    public static void main(String[] args) throws Exception {
        String db = "jdbc:sqlite:foorumi.db";
        AlueDao alueDao = new AlueDao(db);
        KeskusteluDao keskusteluDao = new KeskusteluDao(db);
        ViestiDao viestiDao = new ViestiDao(db);
        SQLQueries sqlq = new SQLQueries(db);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Alue> alue = alueDao.findAll();
            map.put("alue", alue);
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

    }
}
