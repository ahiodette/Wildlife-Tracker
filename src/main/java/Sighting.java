import org.sql2o.Connection;

import java.util.List;

public class Sighting {

    private int id;
    private String rangerName;
    private String animalName;
    private String health;
    private String age;
    private String location;

    public Sighting(int id, String rangerName, String animalName, String health, String age, String location) {
        this.id = id;
        this.rangerName = rangerName;
        this.animalName = animalName;
        this.health = health;
        this.age = age;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getRangerName() {
        return rangerName;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

        @Override
    public boolean equals(Object anotherSighting) {
        if (!(anotherSighting instanceof Animal)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) anotherSighting;
            return this.getRangerName().equals(newSighting.getRangerName()) &&
                    this.getAnimalName() == newSighting.getAnimalName() &&
                    this.getHealth() == newSighting.getHealth() &&
                    this.getAge() == newSighting.getAge() &&
                    this.getLocation() == newSighting.getLocation();
        }
    }

    public static List<Sighting> all() {
        String sql = "SELECT * FROM sighting";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (ranger_name ,animal_name, health, age, location) VALUES (:rangername, :animalname , :health , :age , :location)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("ranger_name", rangerName)
                    .addParameter("animal_name", animalName)
                    .addParameter("health", health)
                    .addParameter("age", age)
                    .addParameter("location", location)
                    .executeUpdate()
                    .getKey();
        }
    }
}