package model;

public class InvestmentHistoryModel {
    private String id;
    private String user_id;
    private String date;
    private Number start_value;
    private Number final_value;
    private Number monthly_income;
    private Number number_of_months;

    public InvestmentHistoryModel() {
        this.id = null;
        this.user_id = null;
        this.date = null;
        this.start_value = null;
        this.final_value = null;
        this.monthly_income = null;
        this.number_of_months = null;
    }

    public InvestmentHistoryModel(String id, String user_id, String date, Number start_value, Number final_value, Number monthly_income, Number number_of_months) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.start_value = start_value;
        this.final_value = final_value;
        this.monthly_income = monthly_income;
        this.number_of_months = number_of_months;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Number getStart_value() {
        return start_value;
    }

    public void setStart_value(Number start_value) {
        this.start_value = start_value;
    }

    public Number getFinal_value() {
        return final_value;
    }

    public void setFinal_value(Number final_value) {
        this.final_value = final_value;
    }

    public Number getMonthly_income() {
        return monthly_income;
    }

    public void setMonthly_income(Number monthly_income) {
        this.monthly_income = monthly_income;
    }

    public Number getNumber_of_months() {
        return number_of_months;
    }

    public void setNumber_of_months(Number number_of_months) {
        this.number_of_months = number_of_months;
    }

}
