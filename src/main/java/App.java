import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
//        ProcessBuilder process = new ProcessBuilder();
//        Integer port;
//
//        if (process.environment().get("PORT") != null) {
//            port = Integer.parseInt(process.environment().get("PORT"));
//        } else {
//            port = 4567;
//        }
//        port(port);


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"home.hbs");
        }, new HandlebarsTemplateEngine());

        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/eform", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"endangered-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/succ", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endsucc", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"endsuccess.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal-healthy", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName");
            String animalName = request.queryParams("animalName");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String location = request.queryParams("location");
            Animal record = new Animal( rangerName, animalName, health, age, location);

            record.save();
            model.put("templates","/templates/animal-form.hbs");
            return new ModelAndView(model, "animals-records.hbs");
        }), new HandlebarsTemplateEngine());

        post("/animal-endanger", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String erangerName = request.queryParams("erangerName");
            String eanimalName = request.queryParams("eanimalName");
            String ehealth = request.queryParams("ehealth");
            String eage = request.queryParams("eage");
            String elocation = request.queryParams("elocation");
            Sighting danger = new Sighting( erangerName, eanimalName, ehealth, eage, elocation);

            danger.save();
            model.put("templates", "templates/endangered-form.hbs")
            return new ModelAndView(model, "endangered.hbs");
        }), new HandlebarsTemplateEngine());
    }
}

