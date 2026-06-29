package efectosProlongados;

import modelo.Personaje;

public abstract class EfectoProlongado {
	private int rondasRestantes;
	
	public EfectoProlongado(int rondasRestantes) {
		this.rondasRestantes = rondasRestantes;
	}
	
	public boolean estaActivo() {
		return rondasRestantes != 0;
	}
	
	public int getRondasRestantes() {
		return rondasRestantes;
	}
	
	public void aplicar(Personaje objetivo) {
		if(!estaActivo())
			throw new IllegalStateException("Error: no se puede aplicar un efecto prolongado sin rondas restantes.");
		
		efectos(objetivo);
		
		if(rondasRestantes > 0)
			rondasRestantes--;
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
