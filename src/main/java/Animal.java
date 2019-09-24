import org.sql2o.Connection;

import java.util.List;

public class Animal {

    private int id;
    private String rangername;
    private String animalname;
    private String health;
    private String age;
    private String location;

    public Animal(int id, String rangername, String animalname, String health, String age, String location) {
        this.id = id;
        this.rangername = rangername;
        this.animalname = animalname;
        this.health = health;
        this.age = age;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getRangername() {
        return rangername;
    }

    public String getAnimalname() {
        return animalname;
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
                    .addParameter("rangername", rangername)
                    .addParameter("animalname", animalname)
                    .addParameter("health",health)
                    .addParameter("age", age)
                    .addParameter("location",location)
                    .executeUpdate()
                    .getKey();
        }
    }
}


