import org.sql2o.Connection;

import java.util.List;

public class Animal {

    private int id;
    private String rangerName;
    private String animalName;
    private String health;
    private String age;
    private String location;

    public Animal(int id, String rangerName, String animalName, String health, String age, String location) {
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
    public boolean equals(Object anotheranimal) {
        if (!(anotheranimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) anotheranimal;
            return this.getRangerName().equals(newAnimal.getRangerName()) &&
                    this.getAnimalName() == newAnimal.getAnimalName() &&
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
            String sql = "INSERT INTO animal (ranger_name ,animal_name, health, age, location) VALUES (:rangerName, :animalName , :health , :age , :location)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("range_name", rangerName)
                    .addParameter("animal_name", animalName)
                    .addParameter("health",health)
                    .addParameter("age", age)
                    .addParameter("location",location)
                    .executeUpdate()
                    .getKey();
        }
    }
}


