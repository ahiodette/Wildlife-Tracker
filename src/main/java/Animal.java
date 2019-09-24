import org.sql2o.Connection;

import java.util.List;

public class Animal {

    private int id;
    private String Rangername;
    private String Animalname;
    private String Health;
    private String Age;
    private String Location;

    public Animal(int id, String rangername, String animalname, String health, String age, String location) {
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
    public boolean equals(Object anotheranimal) {
        if (!(anotheranimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) anotheranimal;
            return this.getRangername().equals(newAnimal.getRangername()) &&
                    this.getAnimalname() == newAnimal.getAnimalname() &&
                    this.getHealth() == newAnimal.getHealth()&&
                    this.getAge() == newAnimal.getAge() &&
                    this.getLocation() == newAnimal.getLocation();



        }
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animal";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animal (rangername ,animalname, health, age, location) VALUES (:rangername, :animalname , :health , :age , :location)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangername", Rangername)
                    .addParameter("animalname", Animalname)
                    .addParameter("health",Health)
                    .addParameter("age", Age)
                    .addParameter("location",Location)
                    .executeUpdate()
                    .getKey();
        }
    }
}


