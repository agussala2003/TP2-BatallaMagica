package modelo;

public class Seguidor extends Mortifago {

    public Seguidor(String nombre) {
        super(nombre, 6, 100);
    }

    @Override
    public int modificarDanioAtaque(int danioBase) {
        return super.modificarDanioAtaque(danioBase) + 5;
    }

    @Override
    public int modificarDefensa(int defensaBase) {
        return super.modificarDefensa(defensaBase) - 3;
    }
}