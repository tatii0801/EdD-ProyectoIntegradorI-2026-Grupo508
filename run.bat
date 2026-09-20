@echo off
chcp 65001 > nul
set SRC_DIR=src
set BIN_DIR=bin

echo ==================================================
echo  Proyecto Integrador I - Estructuras de Datos UNJu
echo ==================================================

echo [1/3] Limpiando binarios anteriores...
if exist %BIN_DIR% rd /s /q %BIN_DIR%
mkdir %BIN_DIR%

echo [2/3] Compilando codigo fuente...
:: =============================================================================
:: MODIFICACIÓN PRINCIPAL:
:: Quitamos: "dir /s /b %SRC_DIR%\*.java > sources.txt"
:: ¿Por qué? Porque 'dir' escribía rutas largas absolutas (c:\Users\tatia\...) 
:: que contenían espacios en "Lic. en Sistemas" y rompían la compilación de javac.
:: Además, VS Code borraba las barras "\" al interpretar cosas como "\tatia" o "\tda" como tabulaciones (\t).
::
:: Solución: Escribimos las rutas relativas cortas a mano usando barras diagonales (/).
:: Al ser rutas locales, el espacio de tu usuario desaparece y la "/" es inmune a los errores de VS Code.
:: =============================================================================
if exist sources.txt del sources.txt

echo src/main/Principal.java>> sources.txt
echo src/ed/tda/Arreglo.java>> sources.txt
echo src/ed/tda/Cola.java>> sources.txt
echo src/ed/tda/Pila.java>> sources.txt
echo src/juego/ControladorJuego.java>> sources.txt
echo src/modelo/Carta.java>> sources.txt
echo src/modelo/Jugador.java>> sources.txt
echo src/modelo/Mazo.java>> sources.txt
echo src/util/Validaciones.java>> sources.txt

:: Conservamos intacto tu comando: javac lee la lista limpia que creamos arriba en sources.txt
javac -d %BIN_DIR% @sources.txt
del sources.txt

if %ERRORLEVEL% EQU 0 (
    echo [3/3] Compilacion exitosa. Ejecutando programa...
    echo --------------------------------------------------
    :: Conservamos intacto tu comando de ejecución
    java -cp %BIN_DIR% main.Principal
) else (
    echo ERROR: Fallo la compilacion.
    pause
    exit /b 1
)

pause
