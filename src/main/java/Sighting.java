import org.sql2o.Connection;

import java.util.List;

public class Sighting {

    private int id;
    private String Rangername;
    private String Animalname;
    private String Health;
    private String Age;
    private String Location;

    public Sighting(int id, String rangername, String animalname, String health, String age, String location) {
        this.id = id;
        Rangername = rangername;
        Animalname = animalname;
        Health = health;
        Age = age;
        Location = location;
    }

    public int getId() {
        return id;
    }

    public String getRangername() {
        return Rangername;
    }

    public String getAnimalname() {
        return Animalname;
    }

    public String getHealth() {
        return Health;
    }

    public String getAge() {
        return Age;
    }

    public String getLocation() {
        return Location;
    }

    @Override
    public boolean equals(Object anotherSighting) {
        if (!(anotherSighting instanceof Animal)) {
            return false;
        } else {
            Sighting newsighting = (Sighting) anotherSighting;
            return this.getRangername().equals(newsighting.getRangername()) &&
                    this.getAnimalname() == newsighting.getAnimalname() &&
                    this.getHealth() == newsighting.getHealth()&&
                    this.getAge() == newsighting.getAge() &&
                    this.getLocation() == newsighting.getLocation();
        }
    }

    public static List<Sighting> all() {
        String sql = "SELECT * FROM sighting";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }



}
