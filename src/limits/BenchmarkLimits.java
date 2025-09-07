package limits;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkLimits {
    private static final int STEP = 500;

    public static void benchmarkPlatformThreads(String label, Runnable task) {
        int maxThreads = 0;
        while (true) {
            try {
                int target = maxThreads + STEP;
                List<Thread> threads = new ArrayList<>(target);

                for (int i = 0; i < target; i++) {
                    Thread t = new Thread(task);
                    threads.add(t);
                    t.start();
                }

                for (Thread t : threads) {
                    t.join();
                }

                maxThreads = target;    // if the program has not crashed, keep going
                System.out.printf("%s: successfully created %d platform threads\n",
                        label, maxThreads);
            } catch (OutOfMemoryError | InterruptedException e) {
                System.out.printf("%s: FAILED at ~%d platform threads\n", label,
                        maxThreads + STEP);
                break;
            }
        }
    }

    public static void benchmarkVirtualThreads(String label, Runnable task) {
        int maxThreads = 0;
        while (true) {
            try {
                int target = maxThreads + STEP;
                List<Thread> threads = new ArrayList<>(target);

                for (int i = 0; i < target; i++) {
                    Thread t = Thread.ofVirtual().unstarted(task);
                    threads.add(t);
                    t.start();
                }

                for (Thread t : threads) {
                    t.join();
                }

                maxThreads = target;    // if the program has not crashed, keep going
                System.out.printf("%s: successfully created %d virtual threads\n",
                        label, maxThreads);
            } catch (OutOfMemoryError | InterruptedException e) {
                System.out.printf("%s: FAILED at ~%d virtual threads\n", label,
                        maxThreads + STEP);
                break;
            }
        }
    }
}
