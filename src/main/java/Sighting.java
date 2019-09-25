import org.sql2o.Connection;

import java.util.List;

public class Sighting {

    private int eid;
    private String erangerName;
    private String eanimalName;
    private String ehealth;
    private String eage;
    private String elocation;

    public Sighting(String erangerName, String eanimalName, String ehealth, String eage, String elocation) {
        this.eid = eid;
        this.erangerName = erangerName;
        this.eanimalName = eanimalName;
        this.ehealth = ehealth;
        this.eage = eage;
        this.elocation = elocation;
    }

    public int getEid() {
        return eid;
    }

    public String getErangerName() {
        return erangerName;
    }

    public String getEanimalName() {
        return eanimalName;
    }

    public String getEhealth() {
        return ehealth;
    }

    public String getEage() {
        return eage;
    }

    public String getElocation() {
        return elocation;
    }

            @Override
    public boolean equals(Object anotherSighting) {
        if (!(anotherSighting instanceof Animal)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) anotherSighting;
            return this.getErangerName().equals(newSighting.getErangerName()) &&
                    this.getEanimalName() == newSighting.getEanimalName() &&
                    this.getEhealth() == newSighting.getEhealth() &&
                    this.getEage() == newSighting.getEage() &&
                    this.getElocation() == newSighting.getElocation();
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
            this.eid = (int) con.createQuery(sql, true)
                    .addParameter("ranger_name", erangerName)
                    .addParameter("animal_name", eanimalName)
                    .addParameter("health", ehealth)
                    .addParameter("age", eage)
                    .addParameter("location", elocation)
                    .executeUpdate()
                    .getKey();
        }
    }
}