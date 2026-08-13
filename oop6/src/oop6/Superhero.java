package oop6;

public class Superhero implements Flyer {

    @Override
    public String takeOff() {
        return "jump";
    }

    @Override
    public String fly() {
        return "fly with both arms in front, fists forward";
    }

    @Override
    public String land() {
        return "land softly without hurting anyone";
    }
}
