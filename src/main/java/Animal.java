import org.sql2o.Connection;

import java.util.List;

public class Animal {

    public int id;
    public String rangerName;
    public String animalName;
    public String health;
    public String age;
    public String location;

    public Animal(String rangerName, String animalName, String health, String age, String location) {

        this.rangerName = rangerName;
        this.animalName = animalName;
        this.health = health;
        this.age = age;
        this.location = location;


    }

//    public int getId() {
//        return id;
//    }
//
//    public String getRangerName() {
//        return rangerName;
//    }
//
//    public String getAnimalName() {
//        return animalName;
//    }
//
//    public String getHealth() {
//        return health;
//    }
//
//    public String getAge() {
//        return age;
//    }
//
//    public String getLocation() {
//        return location;
//    }

            @Override
    public boolean equals(Object anotheranimal) {
        if (!(anotheranimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) anotheranimal;
            return this.rangerName.equals(newAnimal.rangerName) &&
                    this.animalName == newAnimal.animalName &&
                    this.health == newAnimal.health&&
                    this.age == newAnimal.age &&
                    this.location == newAnimal.location;



        }
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals(ranger_name ,animal_name, health, age, location) VALUES (:rangerName, :animalName , :health , :age , :location)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("animalName", this.animalName)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .addParameter("location", this.location)
                    .executeUpdate()
                    .getKey();
        }
    }
}


