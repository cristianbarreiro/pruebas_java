package oop5;

public class Person {

    protected String name;
    protected int id;

    public Person() {

    }

    public Person(String nombre, int dni) {
        this.name = nombre;
        this.id = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public int getId() {
        return id;
    }
}
