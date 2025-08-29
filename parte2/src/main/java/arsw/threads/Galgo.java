package parte2.src.main.java.arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private final Pausador pausador;
	private int paso;
	private Carril carril;
	RegistroLlegada regl;

	private volatile boolean running = true; //BANDERA

	public Galgo(Carril carril, String name, RegistroLlegada reg, Pausador pausador) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
		this.pausador = pausador;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {

			pausador.esperar();

			Thread.sleep(100);

			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			
			if (paso == carril.size()) {						
				carril.finish();
				int ubicacion=regl.getAndIncrementPosicionAlcanzada();
				System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
				if (ubicacion==1){
					regl.setGanador(this.getName());
				}
				
			}
		}
	}


	@Override
	public void run() {
		
		try {
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void detener() {
		running = false;
		this.interrupt();
	}

}
