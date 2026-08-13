package oop6;

public class Alien {

    protected String species;

    public Alien() {
        this("Unknown Species");
    }

    public Alien(String species) {
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }
}
