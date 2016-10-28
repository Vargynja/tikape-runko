package tikape.runko;

import java.util.HashMap;
import java.util.List;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AlueDao;
import tikape.runko.database.KeskusteluDao;
import tikape.runko.database.ViestiDao;
import tikape.runko.domain.Alue;
import tikape.runko.domain.Keskustelu;
import tikape.runko.domain.Viesti;

public class Main {

    public static void main(String[] args) throws Exception {
        port(getHerokuAssignedPort());
        String db = "jdbc:sqlite:foorumi.db";
        AlueDao alueDao = new AlueDao(db);
        KeskusteluDao keskusteluDao = new KeskusteluDao(db);
        ViestiDao viestiDao = new ViestiDao(db);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Alue> alue = alueDao.findAll();
            map.put("alue", alue);
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/alue/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Keskustelu> kesk = keskusteluDao.findAllInAlue(Integer.parseInt(req.params("id")));
            map.put("keskustelu", kesk);
            Alue a = alueDao.findOne(Integer.parseInt(req.params("id")));
            map.put("alue", a);
            return new ModelAndView(map, "alue");
        }, new ThymeleafTemplateEngine());

        get("alue/:it/keskustelu/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            List<Viesti> viestit = viestiDao.findAllInKeskustelu(Integer.parseInt(req.params("id")));
            map.put("viestit", viestit);
            Keskustelu k = keskusteluDao.findOne(Integer.parseInt(req.params("id")));
            map.put("keskustelu", k);
            return new ModelAndView(map, "keskustelu");
        }, new ThymeleafTemplateEngine());

        post("/", (req, res) -> {
            String nimi = req.queryParams("nimi");
            alueDao.lisaaAlue(nimi);
            res.redirect("/");
            return "";
        });

        post("/alue/:id", (req, res) -> {
            String nimi = req.queryParams("nimi");
            int key = Integer.parseInt(req.params("id"));
            keskusteluDao.lisaaKeskustelu(nimi, key);
            res.redirect("/alue/" + req.params(":id"));
            return "";
        });

        post("/alue/:it/keskustelu/:id", (req, res) -> {
            String viesti = req.queryParams("viesti");
            String nimi = req.queryParams("nimimerkki");
            if (nimi.isEmpty()) {
                nimi = "Anonymous";
            }
            int key = Integer.parseInt(req.params("id"));
            viestiDao.lisaaViesti(viesti, nimi, key);
            res.redirect("/alue/" + req.params("it") + "/keskustelu/" + req.params(":id"));
            return "LoL";
        });

        get("/alue/poista/:id", (req, res) -> {
            viestiDao.deleteAlue(Integer.parseInt(req.params("id")));
            keskusteluDao.deleteAlue(Integer.parseInt(req.params("id")));
            alueDao.delete(Integer.parseInt(req.params("id")));
            res.redirect("/");
            return "";
        });

        get("/alue/:it/keskustelu/:il/viesti/poista/:id", (req, res) -> {
            viestiDao.delete(Integer.parseInt(req.params("id")));
            res.redirect("/alue/" + Integer.parseInt(req.params(":it"))
                    + "/keskustelu/" + Integer.parseInt(req.params(":il")));
            return "";
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
