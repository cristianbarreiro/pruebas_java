package oop6;

public class UFO extends Alien implements Flyer {

    @Override
    public String takeOff() {
        return "exit atmosphere";
    }

    @Override
    public String fly() {
        return "fly in any direction";
    }

    @Override
    public String land() {
        return "activate thrusters and touch down";
    }
}
