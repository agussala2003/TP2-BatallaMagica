package modelo;

public class Profesor extends Mago {

    public Profesor(String nombre) {
        super(nombre, 10, 110);
    }

    @Override
    public int modificarCuracion(int curacionBase) {
        return super.modificarCuracion(curacionBase) + 15;
    }

    @Override
    public int modificarDefensa(int defensaBase) {
        return super.modificarDefensa(defensaBase) + 10;
    }
}