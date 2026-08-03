package oop3;

public class Car {

    private int currentSpeed;

    public Car() {

    }

    public int accelerate() {
        return this.accelerate(100);
    }

    public int accelerate(int pedal) {
        this.currentSpeed += pedal;
        return this.currentSpeed;
    }

    public int accelerate(int pedal, int limit) {
        this.currentSpeed += pedal;
        if (this.currentSpeed > limit) {
            this.currentSpeed = limit;
        }
        return this.currentSpeed;
    }

    public int accelerate(int limit, int pedal, boolean ecoMode) {
        return 50;
    }

    public int accelerate(int limit, boolean ecoMode, int pedal) {
        return 50;
    }

    public int accelerateSome() {
        this.currentSpeed += 10;
        return this.currentSpeed;
    }

    public int accelerateSomeMore() {
        this.currentSpeed += 20;
        return this.currentSpeed;
    }

    public int decelerate() {
        this.currentSpeed = 0;
        return this.currentSpeed;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }



}
