package modelo;

public class Auror extends Mago {

    public Auror(String nombre) {
        super(nombre, 8, 120);
    }

    @Override
    public int modificarDanioAtaque(int danioBase) {
        return super.modificarDanioAtaque(danioBase) + 10;
    }

    @Override
    public int modificarDefensa(int defensaBase) {
        return super.modificarDefensa(defensaBase) + 8;
    }
}