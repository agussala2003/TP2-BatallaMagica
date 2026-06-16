package hechizos;

import modelo.Personaje;

public interface Hechizo {
    String getNombre();
    void ejecutar(Personaje lanzador, Personaje objetivo);
}