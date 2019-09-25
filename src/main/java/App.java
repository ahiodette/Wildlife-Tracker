import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            return new ModelAndView(model, "home.hbs");
        }, new HandlebarsTemplateEngine());

        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/eform", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "endangered-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/succ", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.all());
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endsucc", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("endangers", Sighting.all());
            return new ModelAndView(model, "endsuccess.hbs");
        }, new HandlebarsTemplateEngine());

        post("/succ", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String rangerName = request.queryParams("rangerName");
            String animalName = request.queryParams("animalName");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String location = request.queryParams("location");
            Animal record = new Animal(rangerName, animalName, health, age, location);

            model.put("record", "animals");
            model.put("rangerName", "rangerName");
            model.put("animalName", "animalName");
            model.put("health", "health");
            model.put("age", "age");
            model.put("location", "location");
//            record.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


    }
}

