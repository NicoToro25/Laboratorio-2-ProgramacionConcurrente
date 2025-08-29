package parte1.src.main.java.edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread {
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    private int a, b;
    private List<Integer> primes = new LinkedList<>();

    public PrimeFinderThread(int a, int b) {
        super();
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = a; i <= b; i++) {
            synchronized (pauseLock) {
                while (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            if (isPrime(i)) {
                primes.add(i);
                System.out.println(i);
            }
        }
    }

    public void pauseThread() {
        paused = true;
    }

    public void resumeThread() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    private boolean isPrime(int n) {
        if (n < 2)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public List<Integer> getPrimes() {
        return primes;
    }
}
