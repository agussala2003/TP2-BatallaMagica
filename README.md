# Trabajo Práctico 2: Batalla de Magos vs Mortífagos

## 🏛️ Estructura del Proyecto

El proyecto está modularizado en paquetes para cumplir con los principios de Responsabilidad Única y separación de conceptos solicitados en la consigna:

*   **`modelo`**: Contiene la jerarquía de dominio. La clase abstracta `Personaje` y sus derivadas (`Mago`, `Mortifago`, etc.).
*   **`hechizos`**: Implementa el patrón Strategy. Interfaz `Hechizo` y las clases concretas (`Expelliarmus`, `AvadaKedavra`). Cada hechizo encapsula su propio efecto.
*   **`fabricas`**: Implementa el patrón Factory. Clases `Reclutador` y `FabricaHechizos` para centralizar y ocultar la instanciación de objetos.
*   **`combate`**: Lógica de negocio. La clase `Batallon` maneja el flujo de la batalla utilizando colecciones (`List` para turnos, `Set` para control de duplicados, `Map` para historial).
*   **`test`**: *(Carpeta separada src/test/java)* Contiene las pruebas unitarias en JUnit 5.

---

## 🌿 Flujo de Trabajo en Git (Branching Strategy)

Para evitar conflictos y pérdida de código, utilizaremos un modelo basado en **Feature Branches**.

### Ramas Principales
*   **`main`**: Es la rama de producción. Solo contiene código estable, testeado y listo para entregar. **Nadie commitea directamente acá**.
*   **`develop`**: Es la rama de integración. Acá juntamos el código de todos. Cuando terminamos una parte del TP, la unimos a esta rama.

### Ramas de Trabajo (Feature Branches)
Cada integrante que vaya a programar algo nuevo debe crearse una rama propia que nazca a partir de `develop`.

**Nomenclatura:** `feature/nombre-de-la-tarea`
*   *Ejemplo:* `feature/clases-personaje`
*   *Ejemplo:* `feature/logica-colecciones-batallon`
*   *Ejemplo:* `feature/tests-hechizos`

### Paso a paso para trabajar:
1. Asegurate de estar en la rama `develop` y traé los últimos cambios: `git pull origin develop`.
2. Creá tu rama de trabajo: `git checkout -b feature/lo-que-voy-a-hacer`.
3. Escribí tu código y hacé commits chicos y descriptivos.
4. Subí tu rama: `git push origin feature/lo-que-voy-a-hacer`.
5. Avisá al grupo para revisar el código e integrarlo (Merge) a `develop`.