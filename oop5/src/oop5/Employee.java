package oop5;

public class Employee extends Person {

    protected int salary;
    protected int dailyHours;
    protected int totalDaysWorked;

    public Employee() {

    }

    public Employee (int salary) {
        //super();
        this.salary = salary;
    }

    public Employee(String name, int id, int salary, int dailyHours, int totalDaysWorked) {
        super(name, id);
        this.salary = salary;
        this.dailyHours = dailyHours;
        this.totalDaysWorked = totalDaysWorked;
    }

    public int calculateVacationPeriod() {
        return this.totalDaysWorked / 6;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int sueldo) {
        this.salary = sueldo;
    }

    public int getDailyHours() {
        return dailyHours;
    }

    public void setDailyHours(int horasDiarias) {
        this.dailyHours = horasDiarias;
    }

    public int getTotalDaysWorked() {
        return totalDaysWorked;
    }

    public void setTotalDaysWorked(int diasTrabajados) {
        this.totalDaysWorked = diasTrabajados;
    }

    public String greet() {
        return "I'm " + this.name;
    }
}
