package modelo;

import java.util.ArrayList;
import java.util.List;
import hechizos.Hechizo;

public abstract class Personaje {
    private String nombre;
    private int nivelMagia;
    private int puntosVida;
    private int vidaMaxima;
    private int defensa;
	private boolean sangrando;
	private int danioSangrado;
    private List<Hechizo> hechizos;

    public Personaje(String nombre, int nivelMagia, int puntosVida) {
        this.nombre = nombre;
        this.nivelMagia = nivelMagia;
        this.puntosVida = puntosVida;
        this.vidaMaxima = puntosVida;
        this.defensa = 0;
        this.hechizos = new ArrayList<>();
		this.sangrando = false;
		this.danioSangrado = 0;
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

    public int getDefensa() {
        return defensa;
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
	
	public void aplicarSangrado(int danio) {
    sangrando = true;
    danioSangrado = danio;

    System.out.println(nombre + " comienza a sangrar.");
	}

	public void procesarEfectos() {
    if (sangrando && estaVivo()) {
        puntosVida -= danioSangrado;

        if (puntosVida < 0) {
            puntosVida = 0;
        }

        System.out.println(nombre + " sufre " + danioSangrado +
                " de daño por sangrado. Vida actual: " + puntosVida);
    }
	}

    public void recibirDanio(int danio) {
        int danioFinal = danio - defensa;

        if (danioFinal < 0) {
            danioFinal = 0;
        }

        puntosVida -= danioFinal;

        if (puntosVida < 0) {
            puntosVida = 0;
        }

        defensa = 0;

        System.out.println(nombre + " recibe " + danioFinal + " de daño. Vida actual: " + puntosVida);
    }

    public void curar(int cantidad) {
        puntosVida += cantidad;

        if (puntosVida > vidaMaxima) {
            puntosVida = vidaMaxima;
        }

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