package limits;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkLimits {
    public static void benchmarkPlatformThreads(String label, Runnable task, int numWorkers)
            throws InterruptedException, OutOfMemoryError {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numWorkers; i++) {
            Thread t = new Thread(task);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.printf("%s: successfully created %d platform threads\n", label, numWorkers);
    }

    public static void benchmarkVirtualThreads(String label, Runnable task, int numWorkers)
            throws InterruptedException, OutOfMemoryError {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numWorkers; i++) {
            Thread t = Thread.ofVirtual().unstarted(task);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.printf("%s: successfully created %d virtual threads\n", label, numWorkers);
    }
}
