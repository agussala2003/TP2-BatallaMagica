package modelo;

public abstract class Mortifago extends Personaje {

    public Mortifago(String nombre, int nivelMagia, int puntosVida) {
        super(nombre, nivelMagia, puntosVida);
    }

    @Override
    public int modificarDanioAtaque(int danioBase) {
        return danioBase + getNivelMagia() * 3;
    }

    @Override
    public int modificarCuracion(int curacionBase) {
        return curacionBase;
    }

    @Override
    public int modificarDefensa(int defensaBase) {
        return defensaBase + getNivelMagia();
    }
}