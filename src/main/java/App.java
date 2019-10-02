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
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "home.hbs");
        }, new HandlebarsTemplateEngine());

        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/succ", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String rangerName = request.queryParams("rangerName");
            String animalName = request.queryParams("animalName");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String location = request.queryParams("location");
            Animal record = new Animal(rangerName, animalName, health, age, location);
            model.put("ranger_name", rangerName);
            model.put("animal_name", animalName);
            model.put("health", health);
            model.put("age", age);
            model.put("location", location);
            model.put("record",record);
            record.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/eform", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "endangered-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endsucc", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String erangerName = request.queryParams("erangerName");
            String eanimalName = request.queryParams("eanimalName");
            String ehealth = request.queryParams("ehealth");
            String eage = request.queryParams("eage");
            String elocation = request.queryParams("elocation");
            Sighting danger = new Sighting(erangerName, eanimalName, ehealth, eage, elocation);
            model.put("ranger_name", erangerName);
            model.put("animal_name", eanimalName);
            model.put("health", ehealth);
            model.put("age", eage);
            model.put("location", elocation);
            model.put("danger" ,danger);
            danger.save();
            return new ModelAndView(model, "endsuccess.hbs");
        }, new HandlebarsTemplateEngine());

        get("/succ", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> animals = Animal.all();
            System.out.println(animals);
            model.put("animals", animals);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endsucc", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> endangers = Sighting.all();
            model.put("endangers", endangers);
            return new ModelAndView(model, "endsuccess.hbs");
        }, new HandlebarsTemplateEngine());


        get("/details", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            return new ModelAndView(model, "details.hbs");
        }, new HandlebarsTemplateEngine());


        post("/details", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> endangers = Sighting.all();
            List<Animal> animal = Animal.all();
            model.put("animal", animal);
            model.put("endangers", endangers);
            return new ModelAndView(model, "details.hbs");
        }, new HandlebarsTemplateEngine());



    }

}

