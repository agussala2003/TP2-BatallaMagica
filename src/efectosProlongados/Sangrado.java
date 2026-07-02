package efectosProlongados;

import modelo.Personaje;

public class Sangrado extends EfectoProlongado {
	private int danioSangrado;
	
	public Sangrado(int turnosRestantes, int danioSangrado) {
		super(turnosRestantes);
		this.danioSangrado = danioSangrado;
	}
	
	protected void efectos(Personaje objetivo) {
		String textoTiempoRestante = getTurnosRestantes() >= 0 ?
				"El efecto termina en " + String.valueOf(getTurnosRestantes()) + " turno" +
					(getTurnosRestantes() > 1 ? "s" : ".") :
					"El efecto durará toda la batalla.";
		
    	objetivo.quitarPuntosVida(danioSangrado);
	
        System.out.println(objetivo.getNombre() + " sufre " + danioSangrado +
                " de daño por efecto de sangrado. Vida actual: " + objetivo.getPuntosVida() + ". " + textoTiempoRestante);
	}
}
