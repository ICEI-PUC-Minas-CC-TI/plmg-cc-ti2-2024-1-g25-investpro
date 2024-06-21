package model;

public class CalendarEventModel {
    private String id;
    private String user_id;
    private String date;
    private String spending_type;
    private String description;

    public CalendarEventModel() {
        this.id = null;
        this.user_id = null;
        this.date = null;
        this.spending_type = null;
        this.description = null;
    }

    public CalendarEventModel(String id, String user_id, String date, String spending_type, String description) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.spending_type = spending_type;
        this.description = description;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUser_id(){
        return user_id;
    }

    public void setUser_id(String user_id){
        this.user_id = user_id;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getSpending_type(){
        return spending_type;
    }

    public void setSpending_type(String spending_type){
        this.spending_type = spending_type;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
