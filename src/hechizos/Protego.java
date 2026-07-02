package hechizos;

import efectosProlongados.Regeneracion;
import modelo.Personaje;

public class Protego implements Hechizo {

    @Override
    public String getNombre() {
        return "Protego";
    }

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
        int defensa = lanzador.modificarDefensa(25);

        System.out.println(lanzador.getNombre() + " usa Protego");
        lanzador.aumentarDefensa(defensa);
        
        objetivo.agregarEfectoProlongado(new Regeneracion(-1, 10));
    }
}