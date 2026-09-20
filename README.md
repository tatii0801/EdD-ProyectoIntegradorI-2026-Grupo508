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
             │ 4 JUGADORES│  │    MAZO    │  │ 1-13 RONDAS│
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
| `Validaciones` | `util` | Evita errores validando la entrada de datos por consola. |
| `Principal` | `main` | Punto de entrada del programa |

---

### ![paquete](https://img.shields.io/badge/paquete-ed.tda-007bff)  ➖ Arreglo<T> ➖ 
* **Descripción:** Estructura de datos estática y genérica de tamaño fijo.
* **Características:** Define operaciones elementales como `insertar()`, `obtener()`, `eliminarUltimo()`, `eliminarPrimero()` e `intercambiar()`.
* **Rol en el juego:** Es el motor interno de la Pila y la Cola. También se usa como estructura temporal para mezclar el mazo y para guardar en la mesa a los jugadores y cartas durante cada ronda.

### ![paquete](https://img.shields.io/badge/paquete-ed.tda-007bff) ➖ Pila<T> ➖
* **Descripción:** Estructura de datos dinámica con comportamiento **LIFO** (Last In, First Out).
* **Características:** Construida internamente sobre el TDA `Arreglo<T>`. Sus operaciones principales son `apilar()` y `desapilar()`.
* **Rol en el juego:** Administra el Mazo principal (siempre se saca la carta de la cima) y el Pozo de cada jugador (acumulando una sobre otra las cartas ganadas).

### ![paquete](https://img.shields.io/badge/paquete-ed.tda-007bff) ➖ Cola<T> ➖
* **Descripción:** Estructura de datos dinámica con comportamiento **FIFO** (First In, First Out).
* **Características:** Construida internamente sobre el TDA `Arreglo<T>`. Sus operaciones principales son `encolar()` y `desencolar()`.
* **Rol en el juego:** Administra los turnos de la partida. El primer jugador en la fila juega, y luego es enviado al final de la cola para esperar su próximo turno en la siguiente ronda.

### ![paquete](https://img.shields.io/badge/paquete-modelo-28a745) ➖ Carta ➖
* **Descripción:** Entidad que representa un naipe individual francés.
* **Características:** Posee los atributos `palo` (texto), `valor` (entero de 1 a 13) y `disponible` (booleano).
* **Rol en el juego:** Es la unidad básica de información que se transfiere entre el mazo, la mesa de juego y los pozos de los jugadores.

### ![paquete](https://img.shields.io/badge/paquete-modelo-28a745) ➖ Mazo ➖
* **Descripción:** Entidad que administra los 52 naipes de la partida.
* **Características:** Utiliza un TDA `Pila<Carta>` para almacenar de forma segura los naipes.
* **Rol en el juego:** Genera las cartas al inicio, las mezcla aleatoriamente (mediante el algoritmo Fisher-Yates) y reparte la carta de la cima cuando el Controlador lo solicita.

### ![paquete](https://img.shields.io/badge/paquete-modelo-28a745) ➖ Jugador ➖
* **Descripción:** Entidad que representa a cada participante de la partida.
* **Características:** Posee atributos personales (`nombre`, `apellido`, `edad`) y un TDA `Pila<Carta>` exclusivo para su pozo personal.
* **Rol en el juego:** Acumula las cartas ganadas, calcula su puntaje final (moviendo temporalmente sus cartas a una pila auxiliar para no perderlas) y vacía su pozo al reiniciar el juego.

### ![paquete](https://img.shields.io/badge/paquete-juego-fd7e14) ➖ ControladorJuego ➖
* **Descripción:** Clase que actúa como el "árbitro" central del sistema.
* **Características:** Vincula lógicamente al `Mazo`, la `Cola` de jugadores y la cantidad de rondas establecidas.
* **Rol en el juego:** Ejecuta el ciclo de rondas, reparte las cartas de la mesa, compara los valores numéricos, resuelve los empates (devolviendo las cartas) y declara a los ganadores finales.

### ![paquete](https://img.shields.io/badge/paquete-util-6f42c1) ➖ Validaciones ➖
* **Descripción:** Clase utilitaria compuesta por métodos estáticos.
* **Características:** Agrupa funciones de control específicas como `leerTexto()`, `leerEdad()` y `leerCantidadRondas()`.
* **Rol en el juego:** Filtra y limpia los datos ingresados por el usuario para asegurar que el programa no sufra caídas (*crashes*) si se ingresan letras en lugar de números o textos vacíos.

### ![paquete](https://img.shields.io/badge/paquete-main-6c757d) ➖ Principal ➖
* **Descripción:** Clase principal del proyecto que contiene el método ejecutable `main`.
* **Características:** Interactúa directamente con el usuario mediante la consola utilizando la clase `Scanner`.
* **Rol en el juego:** Es el punto de entrada. Registra a los participantes, inicializa los componentes del juego y maneja el ciclo principal que permite jugar revanchas conservando o renovando a los jugadores.

---

## 🔗 Relación entre las estructuras y el juego

| Estructura | Comportamiento | Dónde se usa en el juego |
|---|---|---|
| **Arreglo** | Cantidad fija y conocida de elementos | Los 4 jugadores de la ronda · las 4 cartas de la ronda · los puntajes finales · la mezcla del mazo |
| **Pila** | LIFO | Administrar el mazo y extraer cartas · acumular las cartas de cada jugador · desapilarlas al calcular el puntaje |
| **Cola** | FIFO | Mantener el orden de los 4 jugadores · obtener al jugador correspondiente en el reparto · reincorporarlo para la siguiente ronda |

---

## 📂 Estructura de Archivos del Proyecto

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

## 🚀 Compilación y Ejecución

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
