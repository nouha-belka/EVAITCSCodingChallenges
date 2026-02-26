package com.evaitcs.multithreading;

/**
 * ============================================================================
 * MAIN APPLICATION: MultithreadingApp
 * TOPIC: Running all multithreading demos
 * ============================================================================
 */
public class MultithreadingApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("============================================");
        System.out.println("   MULTITHREADING TASK PROCESSOR");
        System.out.println("============================================\n");

        // TODO 1: Run the SimpleThreadDemo
        SimpleThreadDemo.demo();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // TODO 2: Run the SharedCounter demo (synchronization)
        SharedCounter.demo();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // TODO 3: Run the TaskProcessor demo (ExecutorService)
        // TaskProcessor.demo();

        System.out.println("\n============================================");
        System.out.println("   MULTITHREADING DEMO COMPLETE");
        System.out.println("============================================");
    }
}

