package batalla;

import java.util.Random;

import fabricas.Reclutador;

public class BatallaMagosVsMortifagos {

    public static void main(String[] args) {

        Batallon batallonMagos = new Batallon();
        Batallon batallonMortifagos = new Batallon();

        for (int i = 0; i < 3; i++) {
            batallonMagos.agregarPersonaje(Reclutador.crearMago());
            batallonMortifagos.agregarPersonaje(Reclutador.crearMortifago());
        }

        System.out.println("=== COMIENZA LA BATALLA ===\n");

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
            System.out.println("¡Los magos han ganado la batalla!");
        } else {
            System.out.println("¡Los mortífagos han ganado la batalla!");
        }
    }
}
