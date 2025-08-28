package parte1.src.main.java.edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread {

    private int a, b;
    private List<Integer> primes = new LinkedList<>();

    public PrimeFinderThread(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = a; i <= b; i++) {
            if (isPrime(i)) {
                primes.add(i);
                //System.out.println(i);
            }
        }
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
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
