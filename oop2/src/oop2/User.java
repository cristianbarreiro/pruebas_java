package oop2;

public class User {

    private int id;
    private String name;

    private String username;
    private String password;

    private boolean guest;

    public User() {

    }

    public User(int id) {
        this.id = id;
    }

    public User(String username, boolean isGuest) {
        this.username = username;
        this.guest = isGuest;
    }

    public User(boolean isGuest, String username) {
        this.username = username;
        this.guest = isGuest;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String name, String username, String password, boolean guest) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.id = id;
        this.guest = guest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGuest() {
        return guest;
    }

    public void setGuest(boolean guest) {
        this.guest = guest;
    }
}
