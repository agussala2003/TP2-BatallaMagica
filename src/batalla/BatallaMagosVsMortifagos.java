package batalla;

import java.util.Random;

import fabricas.Reclutador;
import modelo.Personaje;

public class BatallaMagosVsMortifagos {

    public static void main(String[] args) {

        Batallon batallonMagos = new Batallon();
        Batallon batallonMortifagos = new Batallon();
        Batallon ganador;

        for (int i = 0; i < 3; i++) {
            batallonMagos.agregarPersonaje(Reclutador.crearMago());
            batallonMortifagos.agregarPersonaje(Reclutador.crearMortifago());
        }

        System.out.println("=== COMIENZA LA BATALLA ===\n");
        
        System.out.println("=== Magos ===\n");
        for (Personaje personaje : batallonMagos.getPersonajes()) {
        	System.out.println(personaje.getNombre() +
        			". Clase: " + personaje.getClass().toString().substring(13) +
        			". Vida: " + personaje.getPuntosVida() +
        			". Defensa: " + personaje.getDefensa() +
        			". Magia: " + personaje.getNivelMagia());
        }
        System.out.println("");
        
        System.out.println("=== Mortifagos ===\n");
        for (Personaje personaje : batallonMortifagos.getPersonajes()) {
        	System.out.println(personaje.getNombre() +
        			". Clase: " + personaje.getClass().toString().substring(13) +
        			". Vida: " + personaje.getPuntosVida() +
        			". Defensa: " + personaje.getDefensa() +
        			". Magia: " + personaje.getNivelMagia());
        }
        System.out.println("");
        
        Random rand = new Random();

        while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {

            if (rand.nextBoolean()) {
                batallonMagos.atacar(batallonMortifagos);
                if (batallonMortifagos.tienePersonajesSaludables()) {
                    batallonMortifagos.atacar(batallonMagos);
                }
            } else {
                batallonMortifagos.atacar(batallonMagos);
                if (batallonMagos.tienePersonajesSaludables()) {
                    batallonMagos.atacar(batallonMortifagos);
                }
            }

            System.out.println("----------------------------");
        }

        System.out.println("\n=== FIN DE LA BATALLA ===\n");

        batallonMagos.mostrarHistorial();
        batallonMortifagos.mostrarHistorial();

        System.out.println();

        if (batallonMagos.tienePersonajesSaludables()) {
        	ganador = batallonMagos;
            System.out.println("¡Los magos han ganado la batalla!");
        } else {
        	ganador = batallonMortifagos;
            System.out.println("¡Los mortífagos han ganado la batalla!");
        }
        
        System.out.println("\nPersonajes sobrevivientes:");
        for(Personaje personaje : ganador.getPersonajes()) {
        	System.out.println(personaje.getNombre() + ": " + personaje.getPuntosVida() + " puntos de vida.");
        }
    }
}
