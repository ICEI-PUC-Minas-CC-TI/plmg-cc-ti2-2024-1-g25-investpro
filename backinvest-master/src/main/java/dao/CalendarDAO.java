package dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import model.CalendarEventModel;

public class CalendarDAO extends DAO {
    private String table_name = "calendar";

    public CalendarDAO(){
        super();
        conect_to_db();
    }

    public void finalize(){
        close_connection();
    }

    public CalendarEventModel get_calendar_event(String id){
        CalendarEventModel event = new CalendarEventModel();
        try {
            String query = String.format("SELECT * FROM %s WHERE id='%s';", table_name, id);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Data read successfully.");
            if (rs.next()) {
                String user_id = rs.getString("user_id");
                String date = rs.getString("date");
                String spending_type = rs.getString("spending_type");
                String description = rs.getString("description");

                event = new CalendarEventModel(id, user_id, date, spending_type, description);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return event;
    }

    public List<CalendarEventModel> get_all_calendar_events() {
        List<CalendarEventModel> events = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM %s;", table_name);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Data read successfully.");

            while (rs.next()) {
                String id = rs.getString("id");
                String user_id = rs.getString("user_id");
                String date = rs.getString("date");
                String spending_type = rs.getString("spending_type");
                String description = rs.getString("description");

                CalendarEventModel event = new CalendarEventModel(id, user_id, date, spending_type, description);
                events.add(event);
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return events;
    }

    public List<CalendarEventModel> get_user_calendar_events(String user_id){
        List<CalendarEventModel> events = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM %s WHERE user_id='%s';", table_name, user_id);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Data read successfully.");

            while (rs.next()) {
                String id = rs.getString("id");
                String date = rs.getString("date");
                String spending_type = rs.getString("spending_type");
                String description = rs.getString("description");

                CalendarEventModel event = new CalendarEventModel(id, user_id, date, spending_type, description);
                events.add(event);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return events;
    }

    public void create_calendar_table() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS calendar (id serial PRIMARY KEY, user_id VARCHAR(255), date VARCHAR(255), spending_type VARCHAR(255), description VARCHAR(255));";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Table calendar created successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void insert_calendar_event(CalendarEventModel event) {
        try {
            String query = String.format("INSERT INTO %s ( user_id, date, spending_type, description) VALUES ('%s', '%s', '%s', '%s');", table_name, event.getUser_id(), event.getDate(), event.getSpending_type(), event.getDescription());
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void update_calendar_event(CalendarEventModel event) {
        try {
            String query = String.format("UPDATE %s SET user_id='%s', date='%s', spending_type='%s', description='%s' WHERE id='%s';", table_name, event.getUser_id(), event.getDate(), event.getSpending_type(), event.getDescription(), event.getId());
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void delete_calendar_event(String id) {
        try {
            String query = String.format("DELETE FROM %s WHERE id='%s';", table_name, id);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
