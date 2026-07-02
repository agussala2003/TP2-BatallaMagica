package efectosProlongados;

import modelo.Personaje;

public class Regeneracion extends EfectoProlongado {
	private int puntosRegeneracion;
	
	public Regeneracion(int turnosRestantes, int puntosRegeneracion) {
		super(turnosRestantes);
		this.puntosRegeneracion = puntosRegeneracion;
	}
	
	protected void efectos(Personaje objetivo) {
		String textoTiempoRestante = getTurnosRestantes() >= 0 ?
			"El efecto termina en " + String.valueOf(getTurnosRestantes()) + " turno" +
			(getTurnosRestantes() > 1 ? "s" : ".") :
			"El efecto durará toda la batalla.";
				
    	objetivo.agregarPuntosVida(puntosRegeneracion);
	
        System.out.println(objetivo.getNombre() + " recupera " + puntosRegeneracion +
                " de vida por efecto de regeneración. Vida actual: " + objetivo.getPuntosVida() + ". " + textoTiempoRestante);
	}
}
