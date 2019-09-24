import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
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

        String layout = "public/template/layout.vtl";

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/template/index.vtl");
            model.put("track", req.session().attribute("track"));
            return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

        });


        get("/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/template/endangered.vtl");
            model.put("track", request.session().attribute("track"));
            return new VelocityTemplateEngine().render(new ModelAndView(model, layout));
        });


        get("/animaldisplay", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/template/animaldisplay.vtl");
            model.put("tracks", Animal.all());
            return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

        });

        get("/endangereddisplay", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("tracks", Sighting.all());
            model.put("template", "public/template/endangereddisplay.vtl");
            return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

        });


        get("/animal", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/template/animal.vtl");
            model.put("tracks", request.session().attribute("track"));
            return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

        });


        //post//

        post("/animaldisplay", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangername = request.queryParams("rangername");
            String animalname = request.queryParams("animalname");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String location = request.queryParams("location");

            Animal newAnimal = new Animal(rangername, animalname, health, age, location);
            newAnimal.save();
            model.put("template", "public/template/animaldisplay.vtl");
            return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

        });

        post("/endangereddisplay", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangername = request.queryParams("rname");
            String animalname = request.queryParams("aname");
            String health = request.queryParams("ahealth");
            String age = request.queryParams("aage");
            String location = request.queryParams("alocation");

            Sighting newSighting = new Sighting (rangername, animalname, health, age, location);
            newSighting.save();
            model.put("template", "public/template/endangereddisplay.vtl");
            return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

        });

        post("/success", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangername = request.queryParams("rangername");
            String animalname = request.queryParams("animalname");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String location = request.queryParams("location");

            Animal newAnimal = new Animal(rangername, animalname, health, age, location);
            newAnimal.save();
            model.put("template", "public/template/success.vtl");
            return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

        });

        post("/esuccess", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangername = request.queryParams("rname");
            String animalname = request.queryParams("aname");
            String health = request.queryParams("ahealth");
            String age = request.queryParams("aage");
            String location = request.queryParams("alocation");

            Sighting newSighting = new Sighting (rangername, animalname, health, age, location);
            newSighting.save();
            model.put("template", "public/template/esuccess.vtl");
            return new VelocityTemplateEngine().render(new ModelAndView(model, layout));

        });


    }
}

