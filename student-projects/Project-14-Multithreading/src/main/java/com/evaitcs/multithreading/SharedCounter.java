package com.evaitcs.multithreading;

/**
 * ============================================================================
 * CLASS: SharedCounter
 * TOPIC: Synchronization — Preventing race conditions
 * ============================================================================
 *
 * RACE CONDITION:
 * When multiple threads access shared data simultaneously, the result can
 * be unpredictable. This is called a "race condition."
 *
 * Example: Two threads both try to increment a counter from 0:
 *   Thread A reads 0, Thread B reads 0
 *   Thread A writes 1, Thread B writes 1
 *   Expected: 2, Actual: 1 — DATA LOST!
 *
 * SOLUTIONS:
 * 1. synchronized keyword — locks access to a method or block
 * 2. volatile keyword — ensures visibility across threads
 * 3. Atomic classes — thread-safe operations without locks
 *
 * INTERVIEW TIP:
 * "I prevent race conditions using synchronized blocks for critical sections
 *  and volatile for visibility guarantees. For simple counters, I prefer
 *  AtomicInteger for lock-free thread safety."
 * ============================================================================
 */
public class SharedCounter {

    // =========================================================================
    // UNSAFE VERSION — Has race condition!
    // =========================================================================

    private int unsafeCount = 0;

    /**
     * TODO 1: Create an UNSAFE increment method (NO synchronization)
     * Just do: unsafeCount++
     * This will demonstrate the race condition!
     */
    public void unsafeIncrement() {
        // TODO: unsafeCount++
    }

    public int getUnsafeCount() {
        return unsafeCount;
    }

    // =========================================================================
    // SAFE VERSION — Using synchronized keyword
    // =========================================================================

    private int safeCount = 0;

    /**
     * TODO 2: Create a SAFE increment method WITH synchronized
     * Adding 'synchronized' means only ONE thread can execute this at a time.
     *
     * public synchronized void safeIncrement() {
     *     safeCount++;
     * }
     */
    public synchronized void safeIncrement() {
        // TODO: safeCount++
    }

    public int getSafeCount() {
        return safeCount;
    }

    // =========================================================================
    // DEMO — Show the difference between unsafe and safe
    // =========================================================================

    /**
     * TODO 3: Create a demo method
     * 1. Create multiple threads that all increment the UNSAFE counter
     * 2. Create multiple threads that all increment the SAFE counter
     * 3. Compare the results — unsafe will likely be WRONG!
     */
    public static void demo() throws InterruptedException {
        System.out.println("=== Synchronization Demo ===\n");

        SharedCounter counter = new SharedCounter();
        int numThreads = 10;
        int incrementsPerThread = 1000;

        // TODO 4: Create threads for UNSAFE counting
        // Thread[] unsafeThreads = new Thread[numThreads];
        // for (int i = 0; i < numThreads; i++) {
        //     unsafeThreads[i] = new Thread(() -> {
        //         for (int j = 0; j < incrementsPerThread; j++) {
        //             counter.unsafeIncrement();
        //         }
        //     });
        //     unsafeThreads[i].start();
        // }
        // for (Thread t : unsafeThreads) t.join();
        //
        // System.out.println("Expected: " + (numThreads * incrementsPerThread));
        // System.out.println("Unsafe count: " + counter.getUnsafeCount() + " (likely WRONG!)");

        // TODO 5: Create threads for SAFE counting
        // Thread[] safeThreads = new Thread[numThreads];
        // for (int i = 0; i < numThreads; i++) {
        //     safeThreads[i] = new Thread(() -> {
        //         for (int j = 0; j < incrementsPerThread; j++) {
        //             counter.safeIncrement();
        //         }
        //     });
        //     safeThreads[i].start();
        // }
        // for (Thread t : safeThreads) t.join();
        //
        // System.out.println("Safe count: " + counter.getSafeCount() + " (always CORRECT!)");
    }
}

