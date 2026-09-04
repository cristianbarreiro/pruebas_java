import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 3; i++) {
            int taskId = i;

            executor.submit(() -> {

                System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());

            });
        }
        executor.shutdown();
    }
}