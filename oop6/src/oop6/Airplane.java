package oop6;

public class Airplane extends Machine implements Flyer {

    @Override
    public String takeOff() {
        return "speed up over runway";
    }

    @Override
    public String fly() {
        return "fly forward using lift from wings";
    }

    @Override
    public String land() {
        return "touch down and taxi";
    }
}
