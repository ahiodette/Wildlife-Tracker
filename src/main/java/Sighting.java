import org.sql2o.Connection;

import java.util.List;

public class Sighting {

    public int eid;
    public String erangerName;
    public String eanimalName;
    public String ehealth;
    public String eage;
    public String elocation;

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

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getErangerName() {
        return erangerName;
    }

    public void setErangerName(String erangerName) {
        this.erangerName = erangerName;
    }

    public String getEanimalName() {
        return eanimalName;
    }

    public void setEanimalName(String eanimalName) {
        this.eanimalName = eanimalName;
    }

    public String getEhealth() {
        return ehealth;
    }

    public void setEhealth(String ehealth) {
        this.ehealth = ehealth;
    }

    public String getEage() {
        return eage;
    }

    public void setEage(String eage) {
        this.eage = eage;
    }

    public String getElocation() {
        return elocation;
    }

    public void setElocation(String elocation) {
        this.elocation = elocation;
    }

    @Override
    public boolean equals(Object anotherSighting) {
        if (!(anotherSighting instanceof Animal)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) anotherSighting;
            return this.erangerName.equals(newSighting.erangerName) &&
                    this.eanimalName == newSighting.eanimalName &&
                    this.ehealth == newSighting.ehealth &&
                    this.eage == newSighting.eage &&
                    this.elocation == newSighting.elocation;
        }
    }

    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (ranger_name ,animal_name, health, age, location) VALUES (:erangerName, :eanimalName , :ehealth , :eage , :elocation)";
            this.eid = (int) con.createQuery(sql, true)

                    .addParameter("erangerName", this.erangerName)
                    .addParameter("eanimalName", this.eanimalName)
                    .addParameter("ehealth", this.ehealth)
                    .addParameter("eage", this.eage)
                    .addParameter("elocation", this.elocation)
                    .executeUpdate()
                    .getKey();
        }

    }
}