package limits;

public class Main {
    private static final int ALIVE_TIME = 60000;

    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(ALIVE_TIME);   // Keep thread alive for 1 min
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        System.out.println("Benchmarking the limits to create platform and virtual threads:\n");
        BenchmarkLimits.benchmarkPlatformThreads("Platform Threads", task);
        BenchmarkLimits.benchmarkVirtualThreads("Virtual Threads", task);
    }
}
