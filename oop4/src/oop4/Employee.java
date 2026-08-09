package oop4;

public class Employee extends Person {

    protected int salary;

    public Employee() {

    }

    public Employee(int salary) {
        //super();
        this.salary = salary;
    }

    public Employee(String name, int id, int salary) {
        super(name, id);
        this.salary = salary;
    }

    /**
     * Lower salaries have the benefir of longer vacation periods
     *
     * @return int with the vacation period for this employee
     */

    public int getVacationPeriod() {
        if (salary < 1000) {
            return 21;
        } else {
            return 15;
        }
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int sueldo) {
        this.salary = sueldo;
    }

    public String greet() {
        return super.greet() + " I'm " + this.name;
    }

    public String toString() {
        return super.toString() + "Employee [salary= " + salary + "]";
    }

}
