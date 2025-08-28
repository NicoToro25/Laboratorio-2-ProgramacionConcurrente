package parte2.src.main.java.arsw.threads;

public class RegistroLlegada {

	private int ultimaPosicionAlcanzada=1;

	private String ganador=null;

	public synchronized int getAndIncrementPosicionAlcanzada() {
		return ultimaPosicionAlcanzada++;
	}
	
	public synchronized String getGanador() {
		return ganador;
	}

	public synchronized void setGanador(String ganador) {
		if (this.ganador == null) {
			this.ganador = ganador;
		}
	}

	public synchronized int getTotalGalgos() {
		return ultimaPosicionAlcanzada - 1;
	}
}
