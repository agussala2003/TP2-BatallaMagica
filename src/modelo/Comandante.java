package modelo;

public class Comandante extends Mortifago {

    public Comandante(String nombre) {
        super(nombre, 8, 120);
    }

    @Override
    public int modificarDanioAtaque(int danioBase) {
        return super.modificarDanioAtaque(danioBase) + 10;
    }

    @Override
    public int modificarCuracion(int curacionBase) {
        return super.modificarCuracion(curacionBase) - 5;
    }

    @Override
    public int modificarDefensa(int defensaBase) {
        return super.modificarDefensa(defensaBase) + 10;
    }
}