# Proyecto Integrador I - Juego de Cartas

* **Asignatura:** Estructuras de Datos - Ciclo 2026 - 2do Cuatrimestre  
* **Carreras:** Ingeniería Informática / Licenciatura en Sistemas
* **Institución:** Facultad de Ingeniería - Universidad Nacional de Jujuy (UNJu) 

---

<p align="right">
  <i>"No hay mazo que se mezcle solo, ni programa que se organice sin buenas estructuras."</i>
  <br>
  <b>— Grupo 508 - Comisión 5</b>
</p>

---

## 📝 Descripción General

El trabajo  consiste en la simulación de un juego de cartas para 4 jugadores que compiten durante **3 rondas**, tomando cartas de un mazo de naipes franceses de 52 cartas mezcladas al azar.

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
                           │RECIBE 1 CARTA│
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

## ⚙️ Estructuras y Clases Utilizadas

El proyecto se organiza en 4 paquetes, cada uno con una responsabilidad clara: las estructuras genéricas (`ed.tda`), las piezas del juego (`modelo`), el árbitro de la partida (`juego`) y el punto de entrada (`main`).

### 📦 Resumen

| Clase | Paquete | Rol en una frase |
|---|---|---|
| `Arreglo<T>` | `ed.tda` | Base de tamaño fijo sobre la que se construyen Pila y Cola |
| `Pila<T>` | `ed.tda` | LIFO — administra el mazo y el pozo de cada jugador |
| `Cola<T>` | `ed.tda` | FIFO — administra el orden de turno de los 4 jugadores |
| `Carta` | `modelo` | Un naipe: palo, valor y disponibilidad |
| `Mazo` | `modelo` | Genera, mezcla y reparte las 52 cartas |
| `Jugador` | `modelo` | Datos del jugador + su pozo de cartas ganadas |
| `ControladorJuego` | `juego` | Arbitra la partida: reparte, compara y define ganadores |
| `Principal` | `main` | Punto de entrada del programa |

---

### `➖ ed.tda.Arreglo<T>`
![paquete](https://img.shields.io/badge/paquete-ed.tda-2f81f7)

TDA Arreglo genérico de tamaño fijo. Es la base sobre la que se construyen la Pila y la Cola, y también se usa como auxiliar durante el mezclado del mazo.

**Operaciones:** `insertar()` · `obtener()` · `modificar()` · `longitud()` · `capacidad()` · `estaVacio()` · `estaLleno()` · `intercambiar()`

### `➖ ed.tda.Pila<T>`
![paquete](https://img.shields.io/badge/paquete-ed.tda-2f81f7)

Estructura **LIFO** (Last In, First Out) construida sobre `Arreglo<T>`.

**Operaciones:** `apilar()` · `desapilar()` · `verCima()` · `estaVacia()` · `estaLlena()` · `tamanio()`

**Se usa para:** administrar el mazo de cartas y el pozo de cartas acumuladas de cada jugador.

### `➖ ed.tda.Cola<T>`
![paquete](https://img.shields.io/badge/paquete-ed.tda-2f81f7)

Estructura **FIFO** (First In, First Out) construida sobre `Arreglo<T>`. Administra el orden de turno de los 4 jugadores durante las rondas.

**Operaciones:** `encolar()` · `desencolar()` · `frente()` · `estaVacia()` · `estaLlena()` · `tamanio()`

### `➖ modelo.Carta`
![paquete](https://img.shields.io/badge/paquete-modelo-fb8500)

Representa una carta individual del mazo francés.

**Atributos:** `palo` · `valor` (1 a 13) · `disponible`

### `➖ modelo.Mazo`
![paquete](https://img.shields.io/badge/paquete-modelo-fb8500)

Representa el mazo francés de 52 cartas, administrado internamente con una `Pila<Carta>`.

**Responsabilidades:** generar las 52 cartas (4 palos × valores del 1 al 13), mezclarlas con Fisher-Yates, entregarlas durante las rondas marcándolas como no disponibles, e informar cuántas quedan.

### `➖ modelo.Jugador`
![paquete](https://img.shields.io/badge/paquete-modelo-fb8500)

Representa a cada participante de la partida.

**Atributos:** `nombre` · `apellido` · `edad` · `pozo` (`Pila<Carta>`)

Puede recibir cartas (las apila en su pozo) y calcular su puntaje final sumando el valor de todo lo acumulado.

### `➖ juego.ControladorJuego`
![paquete](https://img.shields.io/badge/paquete-juego-2a9d8f)

Controla la lógica principal de la partida: ejecuta las rondas tomando jugadores de la Cola y repartiéndoles cartas del Mazo, compara valores, resuelve empates, y calcula los puntajes finales.

### `➖ main.Principal`
![paquete](https://img.shields.io/badge/paquete-main-6c757d)

Punto de entrada del programa: registra a los jugadores, crea el mazo y la Cola de turnos, e inicia la partida.

---

## 🔗 Relación entre las estructuras y el juego

| Estructura | Comportamiento | Dónde se usa en el juego |
|---|---|---|
| **Arreglo** | Cantidad fija y conocida de elementos | Los 4 jugadores de la ronda · las 4 cartas de la ronda · los puntajes finales · la mezcla del mazo |
| **Pila** | LIFO | Administrar el mazo y extraer cartas · acumular las cartas de cada jugador · desapilarlas al calcular el puntaje |
| **Cola** | FIFO | Mantener el orden de los 4 jugadores · obtener al jugador correspondiente en el reparto · reincorporarlo para la siguiente ronda |

---

## Estructura de Archivos del Proyecto

```text
juego-cartas/
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
```
---
<p align="center">
  ♠️ Proyecto Integrador I — Estructura de Datos · Comisión 5 · UNJU ♠️
</p>
