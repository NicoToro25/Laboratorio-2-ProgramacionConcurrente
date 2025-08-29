package parte2.src.main.java.arsw.threads;

public class Pausador {

    private boolean pausado = false;

    public synchronized void pausar() {
        pausado = true;
    }

    public synchronized void continuar() {
        pausado = false;
        notifyAll();
    }

    public synchronized void esperar() throws InterruptedException {
        while (pausado) {
            wait();
        }
    }
}
