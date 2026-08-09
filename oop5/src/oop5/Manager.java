package oop5;

public class Manager extends Employee {

    private int bonus;

    public Manager() {

    }

    public Manager(String name, int id, int salary, int dailyHours, int totalWorkedDays, int bonus) {
        super(name, id, salary, dailyHours, totalWorkedDays);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void workLessHours(int minusHours) {
        this.dailyHours = this.dailyHours - minusHours;
    }

    public int calculateVacationPeriod() {
        return (int)(super.calculateVacationPeriod() * 1.5);
    }

    @Override
    public String toString() {
        return super.toString() + "{" + "bonus=" + bonus + "}";
    }
}
