import org.sql2o.Sql2o;

public class DatabaseRule {
    //@Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "wecode", "12345");  //Those with linux or windows use two strings for username and password
    }
}
