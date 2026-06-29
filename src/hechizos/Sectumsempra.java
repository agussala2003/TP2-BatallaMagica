package hechizos;

import efectosProlongados.Sangrado;
import modelo.Personaje;

public class Sectumsempra implements Hechizo {
    @Override
    public String getNombre() {
        return "Sectumsempra";
    }

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {

        int danio = lanzador.modificarDanioAtaque(35);

        System.out.println(lanzador.getNombre() +
                " lanza Sectumsempra contra " +
                objetivo.getNombre());

        objetivo.recibirDanio(danio);

        objetivo.agregarEfectoProlongado(new Sangrado(3, 10));
    }
}