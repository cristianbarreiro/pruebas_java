package oop4;

public class Person {
    protected String name;
    protected int id;

    public Person() {

    }

    public Person(String nombre, int id) {
        this.name = nombre;
        this.id = id;
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

    public void setId(int dni) {
        this.id = dni;
    }

    public String greet() {
        return "Hello";
    }

    public String toString() {
        return "Person [name=" + name + ", id=" + "]";
    }
}
