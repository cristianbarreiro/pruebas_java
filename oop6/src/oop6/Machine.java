package oop6;

public class Machine {

    protected String type;
    protected boolean running;

    public Machine() {
        this("Generic Machine");
    }

    public Machine(String type) {
        this.type = type;
        this.running = false;
    }

    public String start() {
        running = true;
        return "Machine " + type + " started";
    }

    public String stop() {
        running = false;
        return "Machine " + type + " stopped";
    }

    public boolean isRunning() {
        return running;
    }

    public String getType() {
        return type;
    }
}
