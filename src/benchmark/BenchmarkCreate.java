package benchmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Benchmark class
 */
public class Benchmark {
    /**
     * Benchmark of platform threads
     * @param label Label for the benchmark
     * @param numThreads Number of platform threads to create
     */
    public static void benchmarkThreads(String label, int numThreads, Runnable task) {
        long startTime = System.nanoTime();

        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Thread t = new Thread(task, "Thread T" + (i+1));
            workers.add(t);
            t.start();
        }

        try {
            for (Thread t : workers) {
                t.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.nanoTime();
        System.out.printf("%-17s| %.4f seconds for %d threads%n",
                label, (endTime - startTime) / 1e9, numThreads);
    }

    /**
     * Benchmark of virtual threads
     * @param label Label for the benchmark
     * @param numThreads Number of virtual threads to create
     */
    public static void benchmarkVirtualThreads(String label, int numThreads, Runnable task) {
        long startTime = System.nanoTime();

        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Thread vt = Thread.ofVirtual().name( "Thread VT" + (i+1)).start(task);
            workers.add(vt);
        }

        for (Thread vt : workers) {
            try {
                vt.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long endTime = System.nanoTime();
        System.out.printf("%-17s| %.4f seconds for %d threads%n",
                label, (endTime - startTime) / 1e9, numThreads);
    }
}
