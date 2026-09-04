class Turno {
    private boolean turnoMain = true;

    public synchronized void imprimir(boolean esMain, String mensaje) {
        while (esMain != turnoMain) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(mensaje);
        turnoMain = !turnoMain;
        notify();
    }
}

class MyRunnable implements Runnable {
    private final Turno turno;

    public MyRunnable(Turno turno) {
        this.turno = turno;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            turno.imprimir(false, "Runnable Thread: " + i);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Turno turno = new Turno();
        MyRunnable task = new MyRunnable(turno);

        Thread t1 = new Thread(task);
        t1.start();

        for (int i = 1; i <= 5; i++) {
            turno.imprimir(true, "Main Thread: " + i);
        }
    }
}
