# Proyecto Integrador I - Juego de Cartas
**Asignatura:** Estructuras de Datos - Ciclo 2026 - 2do Cuatrimestre  
**Institución:** Facultad de Ingeniería - Universidad Nacional de Jujuy (UNJu)  
**Carreras:** Ingeniería Informática / Licenciatura en Sistemas  

---

##  Descripción General
El proyecto consiste en la simulación de un juego de cartas para 4 jugadores que compiten durante rondas tomando cartas de un mazo de naipes franceses (52 cartas) ordenadas al azar. En cada ronda, los jugadores comparan sus cartas y el que obtiene la carta de mayor valor numérico se lleva las cartas de los demás y las guarda en su **pozo acumulador (Pila)**. En caso de empate en el valor máximo, cada jugador conserva su carta. Gana el jugador que obtenga el mayor puntaje al finalizar las rondas.

---
## Mapa conceptual del juego:

```text
                         ┌─────────────────────┐
                         │       PARTIDA       │
                         └──────────┬──────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
                    ▼               ▼               ▼
             ┌────────────┐  ┌────────────┐  ┌────────────┐
             │ 4 JUGADORES│  │    MAZO    │  │ 3 RONDAS   │
             └─────┬──────┘  │ 52 CARTAS  │  └─────┬──────┘
                   │         └─────┬──────┘        │
                   │               │               │
                   ▼               ▼               │
              ┌─────────┐    ┌──────────┐          │
              │  COLA   │    │  PILA    │          │
              │  FIFO   │    │  MAZO    │          │
              └────┬────┘    └────┬─────┘          │
                   │              │                │
                   │              ▼                │
                   │       ┌──────────────┐        │
                   └──────►│ CADA JUGADOR │◄───────┘
                           │ RECIBE 1 CARTA│
                           └──────┬───────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │ COMPARAR VALORES│
                         └────────┬────────┘
                                  │
                    ┌─────────────┴─────────────┐
                    │                           │
                    ▼                           ▼
             ┌──────────────┐           ┌──────────────┐
             │ HAY GANADOR  │           │    EMPATE    │
             └──────┬───────┘           └──────┬───────┘
                    │                          │
                    ▼                          ▼
          ┌──────────────────┐       ┌──────────────────┐
          │ Gana la ronda y  │       │ Cada jugador     │
          │ recibe las 4     │       │ conserva su      │
          │ cartas           │       │ propia carta     │
          └────────┬─────────┘       └────────┬─────────┘
                   │                          │
                   └────────────┬─────────────┘
                                ▼
                       ┌──────────────────┐
                       │  POZO DEL        │
                       │  JUGADOR (PILA)  │
                       └────────┬─────────┘
                                │
                                ▼
                       ┌──────────────────┐
                       │ CALCULAR         │
                       │ PUNTAJES FINALES │
                       └────────┬─────────┘
                                │
                                ▼
                       ┌──────────────────┐
                       │ MOSTRAR GANADOR  │
                       │ DE LA PARTIDA    │
                       └──────────────────┘
```
---

## Lo Que Ya Está Hecho y Cómo Funciona

Actualmente se encuentran implementadas y documentadas las estructuras base y las clases del dominio del mazo:

### 1. `ed.tda.Arreglo<T>` (TDA Arreglo Genérico)
* **Cómo funciona:** Es una estructura contigua de memoria de tamaño fijo (`capacidadMax`). Almacena elementos genéricos utilizando un array `Object[]`. 
* **Funcionalidad clave:** Ofrece inserción ordenada al final (`insertar`), acceso directo por índice `obtener(i)` en tiempo constante $\mathcal{O}(1)$, verificación de estado (`estaVacio`, `estaLleno`) y el método `intercambiar(i, j)`, indispensable para modificar de posición dos elementos en operaciones de ordenamiento o barajado.

### 2. `ed.tda.Pila<T>` (TDA Pila Genérico)
* **Cómo funciona:** Implementación propia de una estructura LIFO (Last In, First Out) **construida sobre la clase `Arreglo<T>`**.
* **Funcionalidad clave:** Permite apilar elementos en la cima (`apilar`), consultar el elemento superior (`verCima`) y desapilar (`desapilar`). En el contexto del juego, se utiliza para representar el pozo de cartas ganadas por cada jugador, permitiendo acumular los naipes y luego desapilarlos al final para calcular el puntaje total.

### 3. `modelo.Carta`
* **Cómo funciona:** Modela un naipe individual del mazo francés.
* **Atributos:** Contiene el `palo` (Trébol, Pica, Corazones, Diamantes), el `valor` (1 a 13) y el estado de la carta (`disponible` o `no disponible`).

### 4. `modelo.Mazo`
* **Cómo funciona:** Encapsula las 52 cartas francesas organizadas internamente mediante el `Pila<Carta>`.
* **Funcionalidad clave:** 
  * `inicializarMazo()`: Genera las 52 combinaciones de cartas.
  * `mezclar()`: Implementa el algoritmo de **Fisher-Yates** aprovechando el método `intercambiar` del TDA Arreglo para barajar las cartas al azar.
  * `sacarCarta()`: Entrega una carta del mazo y cambia su estado a no disponible (`disponible = false`).

--- 

# Descripción de las clases:

## `ed.tda.Arreglo<T>`

Es un TDA Arreglo genérico de tamaño fijo.

Se utiliza como estructura auxiliar para almacenar elementos y acceder a ellos mediante índices.

Entre sus operaciones se encuentran:

* `insertar()`
* `obtener()`
* `modificar()`
* `longitud()`
* `capacidad()`
* `estaVacio()`
* `estaLleno()`
* `intercambiar()`

También se utiliza durante el proceso de mezcla del mazo.

---

## `ed.tda.Pila<T>`

Implementa una estructura **LIFO (Last In, First Out)** utilizando el TDA `Arreglo`.

Sus principales operaciones son:

* `apilar()`
* `desapilar()`
* `verCima()`
* `estaVacia()`
* `estaLlena()`
* `tamanio()`

Se utiliza para:

1. Administrar el mazo de cartas.
2. Administrar el pozo de cartas de cada jugador.

---

## `ed.tda.Cola<T>`

Implementa una estructura **FIFO (First In, First Out)**.

Se utiliza para administrar el orden de los cuatro jugadores durante las rondas.

Sus principales operaciones son:

* `encolar()`
* `desencolar()`
* `verFrente()`
* `estaVacia()`
* `estaLlena()`
* `tamanio()`

---

## `modelo.Carta`

Representa una carta individual.

Contiene:

```text
palo
valor
disponible
```

Además, permite consultar y modificar el estado de disponibilidad de la carta.

---

## `modelo.Mazo`

Representa el mazo francés de 52 cartas.

Sus principales responsabilidades son:

* Crear las 52 cartas.
* Mezclarlas aleatoriamente.
* Administrarlas mediante una Pila.
* Entregar cartas durante las rondas.
* Marcar las cartas entregadas como no disponibles.
* Informar cuántas cartas quedan disponibles.

---

## `modelo.Jugador`

Representa a cada participante.

Contiene:

```text
nombre
apellido
edad
pozo
```

El `pozo` es una `Pila<Carta>` donde se almacenan las cartas que el jugador obtiene durante las rondas.

También permite:

* Consultar sus datos.
* Recibir cartas.
* Calcular su puntaje final.

---

## `juego.ControladorJuego`

Es la clase encargada de controlar la lógica principal de la partida.

Se ocupa de:

* Ejecutar las rondas.
* Obtener los jugadores de la Cola.
* Solicitar cartas al Mazo.
* Comparar las cartas.
* Determinar el ganador de cada ronda.
* Entregar las cartas correspondientes.
* Resolver empates.
* Calcular los puntajes finales.
* Mostrar los resultados.

---

## `main.Principal`

Es el punto de entrada del programa.

Se encarga de:

* Mostrar el menú inicial.
* Registrar a los cuatro jugadores.
* Crear el mazo.
* Crear la Cola de jugadores.
* Crear el `ControladorJuego`.
* Iniciar la partida.
* Preguntar si se desea jugar nuevamente.

---

##  Lo Que Falta Implementar (Para el Resto del Equipo) --- listo🫣?

Para completar el desarrollo del programa de acuerdo a las consignas de la cátedra, restan por implementar los siguientes módulos:

### 1. `ed.tda.Cola<T>` (TDA Cola propio)
* **Objetivo:** Implementar el TDA Cola (FIFO) para la gestión del **turno de los 4 jugadores**.
* **Métodos requeridos:** `encolar(T x)`, `desencolar()`, `frente()`, `estaVacia()`, `tamanio()`.

### 2. `modelo.Jugador`
* **Objetivo:** Representar a cada participante del juego.
* **Atributos requeridos:** `nombre`, `apellido`, `edad` (exigidos por la consigna) y una instancia de `Pila<Carta>` (su pozo de cartas ganadas).
* **Métodos requeridos:** Getters/Setters, método para recibir y apilar cartas en su pozo, y método para calcular el puntaje total desapilando las cartas de su pozo.

### 3. `juego.ControladorJuego`
* **Objetivo:** Orquestar la partida e integrar todas las estructuras de datos.
* **Lógica requerida:**
  * Controlar la ronda actual (ejecutar las rondas o limitar a 3 rondas).
  * Usar el TDA Cola para dar turno a los 4 jugadores.
  * Despachar cartas del `Mazo`, comparar los valores de la ronda actual y determinar al ganador de la ronda.
  * Enviar las cartas ganadas al pozo (`Pila`) del ganador correspondiente (o hacer que cada uno conserve la suya si hay empate).
  * Calcular los puntajes finales y determinar al o los ganadores.

### 4. `main.Principal`
* **Objetivo:** Punto de entrada ejecutable del sistema. Debe instanciar a los jugadores, el mazo, iniciar el juego y mostrar los resultados y el informe final en consola.

---

## Estructura de Archivos del Proyecto

```text
EdD-ProyectoIntegradorI-2026-Grupo508/
├── bin/                      # Binarios compilados (.class)
├── src/
│   ├── ed/
│   │   └── tda/
│   │       ├── Arreglo.java  # [HECHO] TDA Arreglo estático genérico
│   │       ├── Pila.java     # [HECHO] TDA Pila basado en TDA Arreglo
│   │       └── Cola.java     # [hecho] TDA Cola para Turnos
│   ├── modelo/
│   │   ├── Carta.java        # [HECHO] Entidad Carta con estado
│   │   ├── Mazo.java         # [HECHO] Mazo de 52 cartas y barajado
│   │   └── Jugador.java      # [hecho] Entidad Jugador con Pila propia
│   ├── juego/
│   │   └── ControladorJuego.java # [hecho] Lógica de rondas y reglas
│   └── main/
│       └── Principal.java    # [hecho] Punto de entrada del programa
├── run.sh                    # Script de compilación y ejecución en Linux
├── .gitignore
└── README.md
```

## Compilación y Ejecución

### Ejecución en Windows (`run.bat`)

El proyecto cuenta con un script ejecutable para compilar y correr todo en un solo paso sin escribir comandos largos.

#### Opción 1: Desde el Explorador de Archivos
1. Abrí la carpeta raiz del proyecto (`/EdD-ProyectoIntegradorI-2026-Grupo508`).
2. Hacé **doble clic** sobre el archivo `run.bat`.

#### Opción 2: Desde la Consola (CMD o PowerShell)
1. Abrí la terminal en la carpeta del proyecto.
2. Ejecutá el siguiente comando:

```cmd
.\run.bat
