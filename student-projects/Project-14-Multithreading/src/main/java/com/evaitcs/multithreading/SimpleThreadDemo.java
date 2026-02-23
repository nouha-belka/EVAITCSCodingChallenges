package com.evaitcs.multithreading;

/**
 * ============================================================================
 * CLASS: SimpleThreadDemo
 * TOPIC: Creating threads — Thread class, Runnable, and Lambda
 * ============================================================================
 *
 * THREE WAYS TO CREATE THREADS:
 * 1. Extend Thread class (simple but limits inheritance)
 * 2. Implement Runnable interface (preferred — keeps inheritance open)
 * 3. Lambda expression with Runnable (modern, concise)
 *
 * CRITICAL RULES:
 * - Call .start() NOT .run() — start() creates a new thread, run() doesn't!
 * - Thread execution order is NOT guaranteed
 * - Always name your threads for debugging
 *
 * INTERVIEW TIP:
 * "I prefer implementing Runnable over extending Thread because Java
 *  supports single inheritance only. With Runnable, the class can still
 *  extend another class."
 * ============================================================================
 */
public class SimpleThreadDemo {

    // =========================================================================
    // APPROACH 1: Extend Thread class
    // =========================================================================

    /**
     * TODO 1: Create a static inner class that extends Thread
     * Name it: DownloadThread
     *
     * Override the run() method to:
     * - Print: "Downloading file: [fileName]... (Thread: [threadName])"
     * - Simulate download with Thread.sleep(2000)
     * - Print: "Download complete: [fileName]"
     *
     * Add a constructor that takes a String fileName and stores it.
     * Set a meaningful thread name: setName("Download-" + fileName);
     */
    // static class DownloadThread extends Thread {
    //     private final String fileName;
    //
    //     public DownloadThread(String fileName) {
    //         this.fileName = fileName;
    //         setName("Download-" + fileName);
    //     }
    //
    //     @Override
    //     public void run() {
    //         try {
    //             System.out.println("Downloading: " + fileName + " (Thread: " + getName() + ")");
    //             Thread.sleep(2000);
    //             System.out.println("Download complete: " + fileName);
    //         } catch (InterruptedException e) {
    //             Thread.currentThread().interrupt();
    //             System.err.println("Download interrupted: " + fileName);
    //         }
    //     }
    // }


    // =========================================================================
    // APPROACH 2: Implement Runnable interface
    // =========================================================================

    /**
     * TODO 2: Create a static inner class that implements Runnable
     * Name it: ProcessingTask
     *
     * Override run() to:
     * - Print: "Processing task: [taskName]... (Thread: [threadName])"
     * - Simulate work with Thread.sleep(1500)
     * - Print: "Task complete: [taskName]"
     */
    // static class ProcessingTask implements Runnable {
    //     private final String taskName;
    //
    //     public ProcessingTask(String taskName) {
    //         this.taskName = taskName;
    //     }
    //
    //     @Override
    //     public void run() {
    //         // TODO: Implement
    //     }
    // }


    // =========================================================================
    // DEMO METHOD
    // =========================================================================

    /**
     * TODO 3: Create a demo method that shows all three approaches
     */
    public static void demo() {
        System.out.println("=== Thread Creation Demo ===\n");

        // TODO 4: Create and start threads using Approach 1 (extend Thread)
        // DownloadThread download1 = new DownloadThread("report.pdf");
        // DownloadThread download2 = new DownloadThread("image.png");
        // download1.start();  // NOT .run()!
        // download2.start();

        // TODO 5: Create and start threads using Approach 2 (implement Runnable)
        // Thread task1 = new Thread(new ProcessingTask("Data Analysis"));
        // Thread task2 = new Thread(new ProcessingTask("Report Generation"));
        // task1.start();
        // task2.start();

        // TODO 6: Create a thread using Approach 3 (Lambda)
        // Thread lambdaThread = new Thread(() -> {
        //     System.out.println("Lambda thread running! (Thread: "
        //         + Thread.currentThread().getName() + ")");
        //     try {
        //         Thread.sleep(1000);
        //     } catch (InterruptedException e) {
        //         Thread.currentThread().interrupt();
        //     }
        //     System.out.println("Lambda thread complete!");
        // }, "Lambda-Worker");
        // lambdaThread.start();

        // TODO 7: Wait for all threads to finish using .join()
        // try {
        //     download1.join();
        //     download2.join();
        //     task1.join();
        //     task2.join();
        //     lambdaThread.join();
        // } catch (InterruptedException e) {
        //     Thread.currentThread().interrupt();
        // }

        // System.out.println("\nAll threads completed!");
    }
}

