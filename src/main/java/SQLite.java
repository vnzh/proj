import WeatherResponse.Weather;
import okhttp3.Request;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


//db  schema
//CREATE TABLE  IF NOT EXISTS cities (
//        id INTEGER PRIMARY KEY AUTOINCREMENT,
//        name TEXT NOT NULL UNIQUE);
//
//        CREATE TABLE  IF NOT EXISTS forecasts (
//        id INTEGER PRIMARY KEY AUTOINCREMENT,
//        cities_id  INTEGER NOT NULL,
//        date TEXT NOT NULL,
//        info TEXT NOT NULL,
//        temper REAL NOT NULL,
//        FOREIGN KEY (cities_id) REFERENCES  cities(id));
public class SQLite {
    private Connection connection;
    private Statement statement;
    private Controller controller;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public SQLite(Controller controller) {
        this.controller = controller;
    }

    public SQLite() {

    }


    void connnection() throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:forecast.db");
        statement = connection.createStatement();
    }

    void disconnect() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void selectÑities() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("Select id, name FROM cities");
        ResultSet rs = ps.executeQuery();
//        System.out.println(String.format("%s-5 s%", "id", "name"));
        while (rs.next()) {
            String res = String.format("%-5d%-10s", rs.getInt(1), rs.getString(2));
            System.out.println(res);
        }
    }

    void insertCity(String name) throws SQLException {
        if (selectCity(name) != null) {
            System.out.println("ãîðîä  óæå  åñòü");
            return;
        }
        PreparedStatement ps = connection.prepareStatement("INSERT INTO cities  (name)" +
                " VALUES (?)");
        ps.setString(1, name);
        ps.executeUpdate();
    }

    Integer selectCity(String name) throws SQLException {
        String req = String.format("SELECT id FROM cities  WHERE name =  '%s'", name);
//        System.out.println(req);
        PreparedStatement ps = connection.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        if (rs.isClosed()) {
            System.out.println("Integer selectCity rs.isClosed");
            return null;
        }
        Integer res = ps.executeQuery().getInt("id");
//        System.out.println("res " + res);
        return res;
    }

    String selectCityById(int id) throws SQLException {
        String req = String.format("SELECT name FROM cities  WHERE id =  %d ", id);
//        System.out.println(req);
        PreparedStatement ps = connection.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        if (rs.isClosed()) {
            System.out.println("selectCityById rs.isClosed");
            return null;
        }
        String res = ps.executeQuery().getString(1);
//        System.out.println("res " + res);
        return res;
    }

    public List<Weather> selectForecasts(int sityId, String date) throws SQLException {
//        System.out.println("selectForecasts");
        String req = String.format("SELECT * FROM forecasts WHERE cities_id=%d and date='%s'", sityId, date);
        PreparedStatement ps = connection.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        List<Weather> weathers = new ArrayList<>();
        while (rs.next()) {
//            System.out.println(selectCityById(rs.getInt(2)));
            Weather weather = new Weather();
            weather.setLocation(selectCityById(rs.getInt(2)));
            weather.setDate(rs.getString(3));
            weather.setInfo(rs.getString(4));
            weather.setTemperature(rs.getDouble(5));
            weathers.add(weather);
//            System.out.println(weather.toString());
        }
        return weathers;
    }

    public List<Weather> selectForecasts() throws SQLException {
//        System.out.println("selectForecasts");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM forecasts");
        ResultSet rs = ps.executeQuery();
        List<Weather> weathers = new ArrayList<>();
        while (rs.next()) {
//            System.out.println(selectCityById(rs.getInt(2)));
            Weather weather = new Weather();
            weather.setLocation(selectCityById(rs.getInt(2)));
            weather.setDate(rs.getString(3));
            weather.setInfo(rs.getString(4));
            weather.setTemperature(rs.getDouble(5));
            weathers.add(weather);
//            System.out.println(weather.toString());
        }
        return weathers;
    }


    void insertInto(int cityId, String date, String info, Double temper) throws SQLException {


        PreparedStatement ps = connection.prepareStatement("INSERT INTO forecasts (cities_id, date, info, temper)" +
                " VALUES (?, ?, ?, ?)");
        System.out.println("cityId insertInto  " + cityId);
        ps.setInt(1, cityId);
        ps.setString(2, date);
        ps.setString(3, info);
        ps.setDouble(4, temper);
        ps.executeUpdate();

    }


    public boolean checkBySityDate(Integer cityId, String date) throws SQLException {

//        System.out.println("selectForecasts");
        String req = String.format("SELECT * FROM forecasts WHERE cities_id = %d and date = '%s' Limit 1",
                cityId, date);
        PreparedStatement ps = connection.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    public boolean hasCity(String name) throws SQLException {
;
        String req = String.format("SELECT * FROM cities WHERE name = '%s' Limit 1",
                name);
        PreparedStatement ps = connection.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public void updateOnSityDate(int cityId, String date, String info, double temper) throws SQLException {
        String req = String.format("UPDATE forecasts SET info='%s', temper=%f WHERE " +
                "date='%s' and cities_id = %d ;", info, temper, date, cityId);
//        System.out.println(req);
        PreparedStatement ps = connection.prepareStatement(req);
        ps.executeUpdate();

    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {


//        SQLite sqLite = new SQLite();
//        sqLite.connnection();
////        sqLite.selectÑities();
////        sqLite.insertCity("moscow");
////        sqLite.insertCity("per");
//        sqLite.selectÑities();
//        sqLite.selectÑities();
//        System.out.println(sqLite.selectCityById(1));
//        System.out.println(sqLite.selectCityById(1));
//        System.out.println(sqLite.selectCityById(2));
//        sqLite.selectForecasts();
//        sqLite.updateOnSityDate(1, "2022-11-17T07:00", "IOPPIOU", -3.2);
//        sqLite.selectForecasts();
//        for (Weather s:sqLite.selectForecasts() ) {
//            System.out.println(s.toString());
//        }

//        Calendar calendar = new GregorianCalendar();
//        System.out.println(calendar.getTime());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String date = dateFormat.format(calendar.getTime());
//        System.out.println(date);
//        calendar.add(Calendar.DAY_OF_MONTH,5);
//        date = dateFormat.format(calendar.getTime());
//        System.out.println(date);
//
//    }
}
