package fabricas;

import java.util.Random;

import hechizos.AvadaKedavra;
import hechizos.ExpectoPatronum;
import hechizos.Expelliarmus;
import hechizos.Protego;
import hechizos.Sectumsempra;
import modelo.Auror;
import modelo.Comandante;
import modelo.Estudiante;
import modelo.Mago;
import modelo.Mortifago;
import modelo.Personaje;
import modelo.Profesor;
import modelo.Seguidor;

public class Reclutador {

    private static final Random random = new Random();
    private static int contadorMago = 1;
    private static int contadorMortifago = 1;

    private static final String[] NOMBRES_MAGOS = {
        "Harry", "Hermione", "Ron", "Dumbledore", "McGonagall",
        "Neville", "Luna", "Ginny", "Lupin", "Sirius"
    };

    private static final String[] NOMBRES_MORTIFAGOS = {
        "Voldemort", "Bellatrix", "Draco", "Lucius", "Nagini",
        "Fenrir", "Barty", "Quirrell", "Greyback", "Wormtail"
    };

    public static Mago crearMago() {
        String nombre = NOMBRES_MAGOS[contadorMago++ % NOMBRES_MAGOS.length];
        int tipo = random.nextInt(3);

        Mago mago = switch (tipo) {
            case 0 -> new Auror(nombre);
            case 1 -> new Profesor(nombre);
            default -> new Estudiante(nombre);
        };

        asignarHechizosBase(mago);
        return mago;
    }

    public static Mortifago crearMortifago() {
        String nombre = NOMBRES_MORTIFAGOS[contadorMortifago++ % NOMBRES_MORTIFAGOS.length];
        int tipo = random.nextInt(2);

        Mortifago mortifago = switch (tipo) {
            case 0 -> new Comandante(nombre);
            default -> new Seguidor(nombre);
        };

        asignarHechizosOscuros(mortifago);
        return mortifago;
    }

    private static void asignarHechizosBase(Personaje personaje) {
        personaje.agregarHechizo(new Expelliarmus());
        personaje.agregarHechizo(new Protego());
        personaje.agregarHechizo(new ExpectoPatronum());
    }

    private static void asignarHechizosOscuros(Personaje personaje) {
        personaje.agregarHechizo(new AvadaKedavra());
        personaje.agregarHechizo(new Sectumsempra());
        personaje.agregarHechizo(new Expelliarmus());
    }
}
