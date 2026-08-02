package oop1;

public class Engine {

    private String manufacturer;
    private int displacement;
    private int currentRpm;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public int increaseRpm() {
        this.injectFuel();

        if (this.currentRpm >= 3000) {
            return 3000;
        }

        this.currentRpm += 1000;
        return this.currentRpm;
    }

    public int getCurrentRpm() {
        return this.currentRpm;
    }

    private String injectFuel() {
        return "injecting more fuel into cylinders";
    }
}