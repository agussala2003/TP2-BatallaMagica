package batalla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import hechizos.Hechizo;
import modelo.Personaje;

public class Batallon {
    private List<Personaje> personajes;
    private Map<String, List<String>> historialHechizos;
    private Set<String> hechizosUsadosEnRonda;
    private Random random;

    public Batallon() {
        personajes = new ArrayList<>();
        historialHechizos = new HashMap<>();
        hechizosUsadosEnRonda = new HashSet<>();
        random = new Random();
    }

    public void agregarPersonaje(Personaje personaje) {
        personajes.add(personaje);
        historialHechizos.put(personaje.getNombre(), new ArrayList<>());
    }

    public boolean tienePersonajesVivos() {
        for (Personaje personaje : personajes) {
            if (personaje.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    public Personaje obtenerPersonajeVivoAleatorio() {
        List<Personaje> vivos = new ArrayList<>();

        for (Personaje personaje : personajes) {
            if (personaje.estaVivo()) {
                vivos.add(personaje);
            }
        }

        if (vivos.isEmpty()) {
            return null;
        }

        return vivos.get(random.nextInt(vivos.size()));
    }

    public void atacar(Batallon enemigo) {
        Personaje atacante = obtenerPersonajeVivoAleatorio();
        Personaje objetivo = enemigo.obtenerPersonajeVivoAleatorio();

        if (atacante == null || objetivo == null) {
            return;
        }

        atacante.procesarEfectos();

        if (!atacante.estaVivo()) {
            System.out.println(atacante.getNombre() + " cayó por los efectos antes de atacar.");
            return;
        }

        Hechizo hechizo = elegirHechizo(atacante);

        if (hechizo == null) {
            System.out.println(atacante.getNombre() + " no tiene hechizos disponibles.");
            return;
        }

        atacante.lanzarHechizo(hechizo, objetivo);

        historialHechizos.get(atacante.getNombre()).add(hechizo.getNombre());
        hechizosUsadosEnRonda.add(atacante.getNombre() + "-" + hechizo.getNombre());
    }

    private Hechizo elegirHechizo(Personaje personaje) {
        List<Hechizo> hechizosDisponibles = new ArrayList<>();

        for (Hechizo hechizo : personaje.getHechizos()) {
            String clave = personaje.getNombre() + "-" + hechizo.getNombre();

            if (!hechizosUsadosEnRonda.contains(clave)) {
                hechizosDisponibles.add(hechizo);
            }
        }

        if (hechizosDisponibles.isEmpty()) {
            hechizosUsadosEnRonda.clear();
            hechizosDisponibles.addAll(personaje.getHechizos());
        }

        if (hechizosDisponibles.isEmpty()) {
            return null;
        }

        return hechizosDisponibles.get(random.nextInt(hechizosDisponibles.size()));
    }

    public void nuevaRonda() {
        hechizosUsadosEnRonda.clear();
    }

    public void mostrarHistorial() {
        System.out.println("Historial de hechizos:");

        for (String personaje : historialHechizos.keySet()) {
            System.out.println(personaje + ": " + historialHechizos.get(personaje));
        }
    }
}