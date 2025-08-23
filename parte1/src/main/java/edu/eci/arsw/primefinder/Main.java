package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int startRange = 0;
        int endRange = 1000000;
        int numThreads = 16;

        System.out.println("Número de núcleos utilizados (esperados): " + numThreads);

        int range = (endRange - startRange + 1) / numThreads;
        int remainder = (endRange - startRange + 1) % numThreads;

        List<PrimeFinderThread> threads = new ArrayList<>();

        int currentStart = startRange;

        long startTime = System.currentTimeMillis();  // ⏱️ Inicia el conteo de tiempo

        for (int i = 0; i < numThreads; i++) {
            int currentEnd = currentStart + range - 1;
            if (i == numThreads - 1) {
                currentEnd += remainder;
            }

            PrimeFinderThread thread = new PrimeFinderThread(currentStart, currentEnd);
            threads.add(thread);
            thread.start();
            currentStart = currentEnd + 1;
        }

        // Esperar a que todos los hilos terminen
        for (PrimeFinderThread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();  // ⏱️ Termina el conteo de tiempo

        // Mostrar estadísticas
        int totalPrimes = 0;
        for (int i = 0; i < threads.size(); i++) {
            int count = threads.get(i).getPrimes().size();
            totalPrimes += count;
            System.out.println("Hilo " + (i + 1) + " encontró " + count + " números primos.");
        }

        System.out.println("✅ Total de primos encontrados: " + totalPrimes);
        System.out.println("⏱️ Tiempo total de ejecución: " + (endTime - startTime) + " ms");
    }
}
