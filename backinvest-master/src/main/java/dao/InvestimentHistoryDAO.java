package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.InvestmentHistoryModel;

public class InvestimentHistoryDAO extends DAO{
    private String table_name = "investiment_history";

    public InvestimentHistoryDAO(){
        super();
        conect_to_db();
    }

    public void finalize(){
        close_connection();
    }

    public void create_investiment_history_table() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS investiment_history (id serial PRIMARY KEY, user_id TEXT, date TEXT, start_value REAL, final_value REAL, monthly_income REAL, number_of_months INTEGER);";
            stmt.executeUpdate(query);
            System.out.println("Table investiment_history created successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public List<InvestmentHistoryModel> get_all_investiment_history() {
        List<InvestmentHistoryModel> investiment_history = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM %s;", table_name);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            System.out.println("Data read successfully.");

            while (rs.next()) {
                String id = rs.getString("id");
                String user_id = rs.getString("user_id");
                String date = rs.getString("date");
                Number start_value = rs.getDouble("start_value");
                Number final_value = rs.getDouble("final_value");
                Number monthly_income = rs.getDouble("monthly_income");
                Number number_of_months = rs.getInt("number_of_months");
                
                InvestmentHistoryModel user = new InvestmentHistoryModel(id, user_id, date, start_value, final_value, monthly_income, number_of_months);
                investiment_history.add(user);
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return investiment_history;
    }

    public List<InvestmentHistoryModel> get_users_investiment_history(String user_id){
        List<InvestmentHistoryModel> investiment_history = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM %s WHERE user_id='%s';", table_name, user_id);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Data read successfully.");
            
            while (rs.next()) {
                String id = rs.getString("id");
                String date = rs.getString("date");
                Number start_value = rs.getDouble("start_value");
                Number final_value = rs.getDouble("final_value");
                Number monthly_income = rs.getDouble("monthly_income");
                Number number_of_months = rs.getInt("number_of_months");
                
                InvestmentHistoryModel investiment = new InvestmentHistoryModel(id, user_id, date, start_value, final_value, monthly_income, number_of_months);
                investiment_history.add(investiment);
            };

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return investiment_history;
    }

    public InvestmentHistoryModel get_investiment_history(String id) {
        InvestmentHistoryModel investiment_history = new InvestmentHistoryModel();
        try {
            String query = String.format("SELECT * FROM %s WHERE id='%s';", table_name, id);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            System.out.println("Data read successfully.");
            if (rs.next()) {
                String user_id = rs.getString("user_id");
                String date = rs.getString("date");
                Number start_value = rs.getFloat("start_value");
                Number final_value = rs.getFloat("final_value");
                Number monthly_income = rs.getFloat("monthly_income");
                Number number_of_months = rs.getFloat("number_of_months");
                
                investiment_history = new InvestmentHistoryModel(id, user_id, date, start_value, final_value, monthly_income, number_of_months);;
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return investiment_history;
    }

    public void insert_investiment_history(InvestmentHistoryModel investiment_history) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String query = String.format("INSERT INTO %s (user_id, date, start_value, final_value, monthly_income, number_of_months) VALUES ('%s', '%s', %s, %s, %s, %s);", table_name, investiment_history.getUser_id(), investiment_history.getDate(), investiment_history.getStart_value(), investiment_history.getFinal_value(), investiment_history.getMonthly_income(), investiment_history.getNumber_of_months());
            stmt.executeUpdate(query);
            System.out.println("Investiment History inserted successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void update_investiment_history(InvestmentHistoryModel investiment_history) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String query = String.format("UPDATE %s SET user_id='%s', date='%s', start_value=%s, final_value=%s, monthly_income=%s, number_of_months=%s WHERE id='%s';", table_name, investiment_history.getUser_id(), investiment_history.getDate(), investiment_history.getStart_value(), investiment_history.getFinal_value(), investiment_history.getMonthly_income(), investiment_history.getNumber_of_months(), investiment_history.getId());
            stmt.executeUpdate(query);
            System.out.println("Investiment History updated successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void delete_investiment_history(String id) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String query = String.format("DELETE FROM %s WHERE id=%s;", table_name, id);
            stmt.executeUpdate(query);
            System.out.println("Investiment History deleted successfully.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
