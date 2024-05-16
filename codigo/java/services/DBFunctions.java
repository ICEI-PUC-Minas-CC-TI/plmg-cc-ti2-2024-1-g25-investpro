package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
public class DBFunctions {
    public Connection conect_to_db(String dbname, String username, String password) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, username, password);

            if (conn != null) {
                System.out.println("Connected to the database.");
            } else {
                System.out.println("Failed to make connection to the database.");
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return conn;
    }

    public void close_connection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void create_table(Connection conn, String table_name) {
        Statement stmt = null;
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + table_name
                    + " (id serial PRIMARY KEY, title varchar(200), author varchar(200), content varchar(200))";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Table " + table_name + " created successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    public void insert_data(Connection conn, String table_name, String title, String author, String content) {
        Statement stmt = null;
        try {
            String query = String.format(
                    "INSERT INTO %s(title, author, content) VALUES('%s', '%s', '%s');", table_name, title, author,
                    content);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Data inserted successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public List<Blog> read_all_data(Connection conn, String table_name) {
        List<Blog> blogs = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM %s;", table_name);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Data read successfully.");

            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String content = rs.getString("content");

                Blog blog = new Blog(id, title, author, content);
                blogs.add(blog);
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return blogs;
    }

    public void update_data(Connection conn, Number id, String table_name, String title, String author,
            String content) {
        Statement stmt = null;
        try {
            String query = String.format(
                    "UPDATE %s SET title='%s', author='%s', content='%s' WHERE id=%s;", table_name, title, author,
                    content, id);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Data updated successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Blog search_by_id(Connection conn, String table_name, String id) {
        Blog blog = null;
        try {
            String query = String.format("SELECT * FROM %s WHERE id='%s';", table_name, id);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Data read successfully.");
            if (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String content = rs.getString("content");

                blog = new Blog(id, title, author, content);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return blog;
    }

    public void delete_data_by_id(Connection conn, String table_name, String id) {
        Statement stmt = null;
        try {
            String query = String.format("DELETE FROM %s WHERE id='%s';", table_name, id);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Data deleted successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void delete_table(Connection conn, String table_name) {
        Statement stmt = null;
        try {
            String query = String.format("DROP TABLE %s;", table_name);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Table deleted successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
