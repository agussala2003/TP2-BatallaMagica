package hechizos;

import modelo.Personaje;

public class ExpectoPatronum implements Hechizo {

    @Override
    public String getNombre() {
        return "Expecto Patronum";
    }

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
        int curacion = lanzador.modificarCuracion(25);

        System.out.println(lanzador.getNombre() + " invoca Expecto Patronum");
        lanzador.curar(curacion);
    }
}