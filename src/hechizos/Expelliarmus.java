package hechizos;

import modelo.Personaje;

public class Expelliarmus implements Hechizo {

    @Override
    public String getNombre() {
        return "Expelliarmus";
    }

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
        int danio = lanzador.modificarDanioAtaque(20);

        System.out.println(lanzador.getNombre() + " lanza Expelliarmus contra " + objetivo.getNombre());
        objetivo.recibirDanio(danio);
    }
}