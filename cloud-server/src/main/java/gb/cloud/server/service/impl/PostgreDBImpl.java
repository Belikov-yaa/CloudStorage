package gb.cloud.server.service.impl;

import gb.cloud.server.service.DB;
import gb.cloud.utils.PassEncoder;
import gb.cloud.utils.PropertiesLoader;

import java.sql.*;

public class PostgreDBImpl implements DB {

    private static PostgreDBImpl dbInstance;
    private Connection connection;

    private int dbPort;
    private String dbAddress;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    private PreparedStatement psGetUser;
    private PreparedStatement psRegistration;
    private PreparedStatement psDisableUser;
    private PreparedStatement psChangePassword;

    public static DB getInstance() {
        if (dbInstance == null) {
            dbInstance = new PostgreDBImpl();
        }
        return dbInstance;
    }

    private PostgreDBImpl() {
        initProperties();
    }

    private void initProperties() {
        dbPort = Integer.parseInt(PropertiesLoader.getProperty("db.port"));
        dbAddress = PropertiesLoader.getProperty("db.address");
        dbName = PropertiesLoader.getProperty("db.name");
        dbUser = PropertiesLoader.getProperty("db.user");
        dbPassword = PropertiesLoader.getProperty("db.password");
    }

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + dbAddress + ":" + dbPort + "/" + dbName, dbUser, dbPassword);
            initPrepStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to DB server");
        }
    }

    private void initPrepStatement() {
        try {
            psRegistration = connection.prepareStatement("INSERT INTO public.user (login, passhash, blocked) VALUES (?, ?, false)");
            psGetUser = connection.prepareStatement("SELECT blocked FROM public.user WHERE login=? AND passhash=?;");
            psDisableUser = connection.prepareStatement("UPDATE public.user SET blocked=true WHERE login=?;");
            psChangePassword = connection.prepareStatement("UPDATE public.user SET passhash=? WHERE login=?;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getUser(String login, String password) {
        String passhach = PassEncoder.sha256(password);
        try {
            psGetUser.setString(1, login);
            psGetUser.setString(2, passhach);
            ResultSet resultSet = psGetUser.executeQuery();
            if (resultSet.next()) {
                return !resultSet.getBoolean("blocked");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUser(String login, String password) {
        return false;
    }

    @Override
    public void closeConnection() {
        try {
            if (!connection.isClosed()) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
