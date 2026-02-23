package com.evaitcs.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ============================================================================
 * CLASS: TaskProcessor
 * TOPIC: ExecutorService + Callable + Future — Modern thread management
 * ============================================================================
 *
 * EXECUTORSERVICE:
 * Instead of manually creating threads, use a thread pool managed by
 * ExecutorService. It reuses threads, manages lifecycle, and handles errors.
 *
 * CALLABLE vs RUNNABLE:
 * - Runnable.run()  → returns void, can't throw checked exceptions
 * - Callable.call() → returns a value, CAN throw exceptions
 *
 * FUTURE:
 * Represents the result of an async computation. You can:
 * - future.get()       → blocks until result is ready
 * - future.isDone()    → checks if complete
 * - future.cancel()    → cancels the task
 *
 * INTERVIEW TIP:
 * "I use ExecutorService with a fixed thread pool for concurrent tasks.
 *  Callable lets me return results from threads, and Future lets me
 *  retrieve those results when they're ready."
 * ============================================================================
 */
public class TaskProcessor {

    // TODO 1: Declare a private ExecutorService field
    // private final ExecutorService executor;


    /**
     * TODO 2: Create a constructor that initializes a fixed thread pool
     *
     * @param poolSize the number of threads in the pool
     */
    // public TaskProcessor(int poolSize) {
    //     this.executor = Executors.newFixedThreadPool(poolSize);
    //     System.out.println("TaskProcessor initialized with " + poolSize + " threads.");
    // }


    /**
     * TODO 3: Submit a Callable task that simulates a web API call
     * The task should:
     * - Print: "Fetching data from [url]... (Thread: [name])"
     * - Simulate network delay with Thread.sleep(random 1-3 seconds)
     * - Return a String result: "Data from [url]"
     *
     * @param url the simulated URL to fetch
     * @return a Future containing the result
     */
    public Future<String> fetchData(String url) {
        // TODO: Submit a Callable to the executor
        // return executor.submit(() -> {
        //     String threadName = Thread.currentThread().getName();
        //     System.out.println("Fetching: " + url + " (Thread: " + threadName + ")");
        //     Thread.sleep((long) (Math.random() * 2000 + 1000));
        //     return "Data from " + url;
        // });

        return null; // Replace this line
    }

    /**
     * TODO 4: Process multiple URLs concurrently and collect results
     *
     * @param urls list of URLs to fetch
     * @return list of results
     */
    public List<String> fetchAllData(List<String> urls) {
        List<String> results = new ArrayList<>();

        // TODO: Submit all tasks and collect Futures
        // List<Future<String>> futures = new ArrayList<>();
        // for (String url : urls) {
        //     futures.add(fetchData(url));
        // }

        // TODO: Collect results from all Futures
        // for (Future<String> future : futures) {
        //     try {
        //         results.add(future.get(5, TimeUnit.SECONDS));
        //     } catch (TimeoutException e) {
        //         results.add("TIMEOUT");
        //     } catch (InterruptedException | ExecutionException e) {
        //         results.add("ERROR: " + e.getMessage());
        //     }
        // }

        return results;
    }

    /**
     * TODO 5: Gracefully shutdown the ExecutorService
     * Steps:
     * 1. Call executor.shutdown() — stops accepting new tasks
     * 2. Wait for running tasks to finish (with timeout)
     * 3. If timeout, force shutdown with shutdownNow()
     */
    public void shutdown() {
        // TODO: Implement graceful shutdown
        // executor.shutdown();
        // try {
        //     if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
        //         executor.shutdownNow();
        //     }
        // } catch (InterruptedException e) {
        //     executor.shutdownNow();
        // }
        // System.out.println("TaskProcessor shut down.");
    }

    /**
     * TODO 6: Demo method
     */
    public static void demo() {
        System.out.println("=== ExecutorService Demo ===\n");

        // TODO: Create a TaskProcessor with 3 threads
        // TaskProcessor processor = new TaskProcessor(3);

        // TODO: Fetch multiple URLs concurrently
        // List<String> urls = List.of(
        //     "https://api.example.com/users",
        //     "https://api.example.com/products",
        //     "https://api.example.com/orders",
        //     "https://api.example.com/reviews",
        //     "https://api.example.com/inventory"
        // );
        //
        // System.out.println("Fetching " + urls.size() + " URLs with 3 threads...\n");
        // long start = System.currentTimeMillis();
        //
        // List<String> results = processor.fetchAllData(urls);
        //
        // long elapsed = System.currentTimeMillis() - start;
        // System.out.println("\nResults:");
        // for (String result : results) {
        //     System.out.println("  " + result);
        // }
        // System.out.println("Completed in " + elapsed + "ms");
        // System.out.println("(Sequential would take ~" + (urls.size() * 2000) + "ms)");
        //
        // processor.shutdown();
    }
}

