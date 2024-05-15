package model;

public class User {
    private String id;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String cpf;
    private boolean acept;

    public User(String id, String password, String email, String firstname, String lastname,
            String cpf,
            boolean acept) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cpf = cpf;
        this.acept = acept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isAcept() {
        return acept;
    }

    public void setAcept(boolean acept) {
        this.acept = acept;
    }
}
