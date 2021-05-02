package gb.cloud.server;

import gb.cloud.server.factory.Factory;
import gb.cloud.utils.PropertiesLoader;
import org.flywaydb.core.Flyway;

public class MainApp {
    private static final int PORT = Integer.parseInt(PropertiesLoader.getProperty("db.port"));
    private static final String HOST = PropertiesLoader.getProperty("db.address");
    private static final String DB = PropertiesLoader.getProperty("db.name");
    private static final String USER = PropertiesLoader.getProperty("db.user");
    private static final String PWD = PropertiesLoader.getProperty("db.password");

    public static void main(String[] args) {
        migrateDB();
        Factory.getServerService().startServer(PORT);
    }

    private static void migrateDB() {

        String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB;

        Flyway flyway = Flyway.configure()
                .mixed(true)
                .dataSource(url, USER, PWD).load();
        flyway.migrate();
    }
}