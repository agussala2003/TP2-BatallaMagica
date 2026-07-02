package modelo;

public abstract class Mago extends Personaje {

    public Mago(String nombre, int nivelMagia, int puntosVida) {
        super(nombre, nivelMagia, puntosVida);
    }

    @Override
    public int modificarDanioAtaque(int danioBase) {
        return danioBase + getNivelMagia() * 2;
    }

    @Override
    public int modificarCuracion(int curacionBase) {
        return curacionBase + getNivelMagia() * 3;
    }

    @Override
    public int modificarDefensa(int defensaBase) {
        return defensaBase + getNivelMagia() * 3;
    }
}