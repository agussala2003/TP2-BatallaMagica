package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import efectosProlongados.EfectoProlongado;
import hechizos.Hechizo;

public abstract class Personaje {
    private String nombre;
    private int nivelMagia;
    private int puntosVida;
    private int vidaMaxima;
    private int defensa;
    private List<Hechizo> hechizos;
    private Set<EfectoProlongado> efectosProlongados;

    public Personaje(String nombre, int nivelMagia, int puntosVida) {
        this.nombre = nombre;
        this.nivelMagia = nivelMagia;
        this.puntosVida = puntosVida;
        this.vidaMaxima = puntosVida;
        this.defensa = 0;
        this.hechizos = new ArrayList<>();
        this.efectosProlongados = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivelMagia() {
        return nivelMagia;
    }

    public int getPuntosVida() {
        return puntosVida;
    }
    
    public boolean agregarEfectoProlongado(EfectoProlongado efecto) {
    	return efectosProlongados.add(efecto);
    }
    
    public void quitarPuntosVida(int danio) {
    	puntosVida -= danio;
    	
    	if (puntosVida < 0) {
            puntosVida = 0;
        }
    }
    
    public void agregarPuntosVida(int puntos) {
    	puntosVida += puntos;
    	
        if (puntosVida > vidaMaxima) {
            puntosVida = vidaMaxima;
        }   	
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public boolean estaVivo() {
        return puntosVida > 0;
    }

    public void agregarHechizo(Hechizo hechizo) {
        hechizos.add(hechizo);
    }

    public List<Hechizo> getHechizos() {
        return hechizos;
    }

    public void aumentarDefensa(int cantidad) {
        defensa += cantidad;
        System.out.println(nombre + " aumenta su defensa en " + cantidad + ". Defensa actual: " + defensa);
    }

	public void procesarEfectos() {
		for (EfectoProlongado efecto : efectosProlongados) {
			efecto.aplicar(this);
		}
		
		efectosProlongados.removeIf(e -> !e.estaActivo());
	}

    public void recibirDanio(int danio) {
        int danioFinal = danio - defensa;
        
        if (danioFinal < 0) {
            danioFinal = 0;
        }

        quitarPuntosVida(danioFinal);

        defensa = Math.max(0, danioFinal);

        System.out.println(nombre + " recibe " + danioFinal + " de daño. Vida actual: " + puntosVida);
    }

    public void curar(int cantidad) {
        agregarPuntosVida(cantidad);

        System.out.println(nombre + " recupera " + cantidad + " de vida. Vida actual: " + puntosVida);
    }

    public void lanzarHechizo(Hechizo hechizo, Personaje objetivo) {
        if (!estaVivo()) {
            System.out.println(nombre + " no puede lanzar hechizos porque está eliminado.");
            return;
        }

        hechizo.ejecutar(this, objetivo);
    }

    public abstract int modificarDanioAtaque(int danioBase);

    public abstract int modificarCuracion(int curacionBase);

    public abstract int modificarDefensa(int defensaBase);
}