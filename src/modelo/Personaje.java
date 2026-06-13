package modelo;

import java.util.ArrayList;
import java.util.List;
import hechizos.Hechizo;

public abstract class Personaje {
    private String nombre;
    private int nivelMagia;
    private int puntosVida;
    private int vidaMaxima;
    private List<Hechizo> hechizos;

    public Personaje(String nombre, int nivelMagia, int puntosVida) {
        this.nombre = nombre;
        this.nivelMagia = nivelMagia;
        this.puntosVida = puntosVida;
        this.vidaMaxima = puntosVida;
        this.hechizos = new ArrayList<>();
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

    public boolean estaVivo() {
        return puntosVida > 0;
    }

    public void agregarHechizo(Hechizo hechizo) {
        hechizos.add(hechizo);
    }

    public List<Hechizo> getHechizos() {
        return hechizos;
    }

    public void recibirDanio(int danio) {
        puntosVida -= danio;

        if (puntosVida < 0) {
            puntosVida = 0;
        }
    }

    public void curar(int cantidad) {
        puntosVida += cantidad;

        if (puntosVida > vidaMaxima) {
            puntosVida = vidaMaxima;
        }
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
}