package efectosProlongados;

import modelo.Personaje;

public abstract class EfectoProlongado {
	private int turnosRestantes;
	
	public EfectoProlongado(int turnosRestantes) {
		this.turnosRestantes = turnosRestantes;
	}
	
	public boolean estaActivo() {
		return turnosRestantes != 0;
	}
	
	public int getTurnosRestantes() {
		return turnosRestantes;
	}
	
	public void aplicar(Personaje objetivo) {
		if(!estaActivo())
			throw new IllegalStateException("Error: no se puede aplicar un efecto prolongado sin turnos restantes.");
		
		efectos(objetivo);
		
		if(turnosRestantes > 0)
			turnosRestantes--;
	}
	
	protected abstract void efectos(Personaje objetivo);
	
	@Override
	public boolean equals(Object o) {
	    return o.getClass() == this.getClass();
	}

	@Override
	public int hashCode() {
	    return getClass().hashCode();
	}
}
