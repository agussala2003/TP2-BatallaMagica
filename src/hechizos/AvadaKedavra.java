package hechizos;

import modelo.Personaje;

public class AvadaKedavra implements Hechizo {

    @Override
    public String getNombre() {
        return "Avada Kedavra";
    }

    @Override
    public void ejecutar(Personaje lanzador, Personaje objetivo) {
        int danio = lanzador.modificarDanioAtaque(45);

        System.out.println(lanzador.getNombre() + " lanza Avada Kedavra contra " + objetivo.getNombre());
        objetivo.recibirDanio(danio);
    }
}