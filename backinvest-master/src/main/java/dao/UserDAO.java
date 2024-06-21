package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.UserModel;

public class UserDAO extends DAO {
    private String table_name = "users";

    public UserDAO(){
        super();
        conect_to_db();
    }

    public void finalize(){
        close_connection();
    }

    public UserModel get_user(String id){
        UserModel user = new UserModel();
         try {
            String query = String.format("SELECT * FROM %s WHERE id='%s';", table_name, id);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Data read successfully.");
            if (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String cpf = rs.getString("cpf");
                Boolean accept = rs.getBoolean("accept");

                user = new UserModel(id, firstname, lastname, email, password, cpf, accept);;
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return user;
    }

    public List<UserModel> get_all_users() {
        List<UserModel> users = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM %s;", table_name);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Data read successfully.");

            while (rs.next()) {
                String id = rs.getString("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String cpf = rs.getString("cpf");
                Boolean accept = rs.getBoolean("accept");
                String email = rs.getString("email");
                String password = rs.getString("password");
                
                UserModel user = new UserModel(id, password, email, firstname, lastname, cpf, accept);;
                users.add(user);
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return users;
    }

    public void create_user_table() {
        Statement stmt = null;
        try {
            String query = "CREATE TABLE IF NOT EXISTS users (id serial PRIMARY KEY, firstname varchar(200), lastname varchar(200), cpf varchar(200), password varchar(200), email varchar(200), accept boolean)";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Table users created successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void create_user( String firstname, String lastname, String cpf, String password,
            String email,
            boolean accept) {
        Statement stmt = null;
        try {
            String query = String.format(
                    "INSERT INTO users(firstname, lastname, cpf, password, email, accept) VALUES('%s', '%s', '%s', '%s', '%s', %s);",
                    firstname, lastname, cpf, password, email, accept);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("User created successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public boolean check_user_exists( String email) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM users WHERE email='%s';", email);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return false;
    }

    public UserModel authenticate_user(String email, String password) {
        UserModel user = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM users WHERE email='%s' AND password='%s';", email, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                String id = rs.getString("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String cpf = rs.getString("cpf");
                Boolean accept = rs.getBoolean("accept");

                user = new UserModel(id, "", email, firstname, lastname, cpf, accept);
            }
            return user;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return user;
    }

    public void update_user(String id, String firstname, String lastname, String cpf, String email,
    String password, boolean accept) {
        Statement stmt = null;
        try {
            String query = String.format(
                    "UPDATE users SET firstname='%s', lastname='%s', cpf='%s', password='%s', email='%s', accept=%s WHERE id=%s;",
                    firstname, lastname, cpf, password, email, accept, id);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("User updated successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void delete_user(String id) {
        Statement stmt = null;
        try {
            String query = String.format("DELETE FROM users WHERE id=%s;", id);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("User deleted successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
}
