package benchmark;

public class Main {
    // Number of work units to create
    private static final int NUM_WORKERS = 1000;

    /**
     * Main method
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Runnable task = () -> {
            // Does nothing
        };

        System.out.println("Benchmarking creation platform and virtual threads:\n");
        BenchmarkCreate.benchmarkThreads("Platform Threads", NUM_WORKERS, task);
        BenchmarkCreate.benchmarkVirtualThreads("Virtual Threads", NUM_WORKERS, task);
    }
}