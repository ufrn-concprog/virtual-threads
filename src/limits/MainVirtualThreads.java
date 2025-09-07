package limits;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainVirtualThreads {
    private static final int STEP = 500;
    private static final int ALIVE_TIME = 60000;

    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(ALIVE_TIME);   // Keep thread alive for 1 min
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        System.out.println("Benchmarking the limits to create virtual threads:");
        System.out.println("Started at " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");

        int maxThreads = STEP;
        while (true) {
            try {
                BenchmarkLimits.benchmarkVirtualThreads("Virtual Threads", task, maxThreads);
            } catch (InterruptedException | OutOfMemoryError e) {
                break;
            }
            maxThreads += STEP;
        }
    }
}
