package modelo;

public class Estudiante extends Mago {

    public Estudiante(String nombre) {
        super(nombre, 4, 90);
    }

    @Override
    public int modificarDanioAtaque(int danioBase) {
        return super.modificarDanioAtaque(danioBase) - 5;
    }

    @Override
    public int modificarCuracion(int curacionBase) {
        return super.modificarCuracion(curacionBase) + 5;
    }

    @Override
    public int modificarDefensa(int defensaBase) {
        return super.modificarDefensa(defensaBase) - 5;
    }
}