# 🚀 Space Invaders - Practica 2 IPC1

**📚 Curso:** Introduccion a la Programacion y Computacion 1  
**👨‍🎓 Estudiante:** 202300396  


## 📋 Descripcion del Proyecto

Space Invaders es un juego clasico arcade desarrollado en Java utilizando Swing para la interfaz grafica. El jugador controla una nave espacial que debe derrotar oleadas de enemigos mientras evita colisiones y recolecta items especiales. El juego incluye funcionalidades avanzadas como guardado/carga de partidas, sistema de puntuaciones y efectos visuales.

## ⭐ Caracteristicas Principales

### 🎮 Gameplay

- **🚁 Movimiento de nave:** Controles con teclas de flecha (arriba/abajo)
- **💥 Disparo:** Barra espaciadora para disparar proyectiles
- **👾 Enemigos:** Tres tipos diferentes con distintos niveles de resistencia y puntuacion
- **⏱️ Tiempo limitado:** 90 segundos para completar el nivel
- **💀 Sistema de vidas:** Collision con enemigos termina el juego

### 👾 Tipos de Enemigos

1. **🟢 Enemigo Tipo 1** - 2 impactos requeridos, 10 puntos
2. **🟡 Enemigo Tipo 2** - 3 impactos requeridos, 20 puntos  
3. **🔴 Enemigo Tipo 3** - 4 impactos requeridos, 30 puntos

### 🎁 Items Especiales

- **⏰ Reloj Bonus:** Aumenta 10 segundos al tiempo restante
- **🪙 Moneda:** Otorga 10 puntos adicionales
- **💀 Calavera:** Reduce 10 segundos del tiempo
- **⚠️ Penalizacion:** Resta 10 puntos

### 🔧 Funcionalidades del Sistema

- **💾 Guardado de partidas:** Serialization para continuar juegos
- **🏆 Top 5 puntuaciones:** Sistema persistente de high scores
- **🖼️ Interfaz grafica completa:** Menus y transiciones animadas
- **✨ Efectos visuales:** Explosiones y GIFs animados

## 🏗️ Arquitectura del Proyecto

### 📁 Estructura de Archivos

```txt
IPC1_P2_VJ2024/
├── src/                    # Codigo fuente
│   ├── Main.java          # Punto de entrada de la aplicacion
│   ├── InterfazInicio.java # Menu principal
│   ├── Juego.java         # Motor principal del juego
│   ├── PlayerNave.java    # Control de la nave del jugador
│   ├── Enemigos.java      # Gestion de enemigos
│   ├── Item.java          # Items coleccionables
│   ├── ControlItems.java  # Generador de items
│   ├── GameOver.java      # Pantalla de fin de juego
│   ├── CargarJuego.java   # Interface para cargar partidas
│   ├── top5.java          # Pantalla de puntuaciones
│   ├── ControlScores.java # Manejo de puntuaciones
│   └── StatusGame.java    # Estado del juego para serialization
├── bin/                   # Archivos compilados
├── imgs/                  # Recursos graficos
└── README.md
```

### 🎯 Clases Principales

#### 🚀 Main.java

Punto de entrada que inicializa la aplicacion en el Event Dispatch Thread de Swing.

#### 🏠 InterfazInicio.java

Menu principal con opciones para:

- 🆕 Nuevo juego
- 📂 Cargar juego guardado
- 🏆 Ver top 5 puntuaciones
- 🚪 Salir

#### 🎮 Juego.java

Motor central que maneja:

- 🔄 Game loop principal
- ⏱️ Temporizador de juego
- 🎯 Sistema de puntuacion
- 💾 Serializacion de partidas
- 🔗 Coordination entre componentes

#### 🚁 PlayerNave.java

Controla la nave del jugador:

- ⬆️ Movimiento vertical
- 💥 Sistema de disparo
- 💥 Deteccion de colisiones
- 🚀 Gestion de proyectiles

#### 👾 Enemigos.java

Maneja las hordas de enemigos:

- 🔄 Patron de movimiento
- 🎯 Diferentes tipos con resistencias variables
- 📊 Sistema de puntuacion por tipo

#### 🎁 ControlItems.java

Generador automatico de items:

- 🎲 Aparicion aleatoria cada 6 segundos
- 🔀 Tipos aleatorios de items
- ➡️ Movimiento horizontal

## 🎮 Controles del Juego

| Tecla | Accion |
|-------|--------|
| ⬆️ | Mover nave hacia arriba |
| ⬇️ | Mover nave hacia abajo |
| 🎯 Espacio | Disparar proyectil |
| 🔙 Escape | Regresar al menu principal |
| 💾 S | Guardar partida actual |

## 🏆 Sistema de Puntuacion

- **🟢 Enemigo Tipo 1:** 10 puntos
- **🟡 Enemigo Tipo 2:** 20 puntos
- **🔴 Enemigo Tipo 3:** 30 puntos
- **🪙 Item Moneda:** +10 puntos
- **⚠️ Item Penalizacion:** -10 puntos

## ⚙️ Compilacion y Ejecucion

### 📋 Requisitos

- ☕ Java Development Kit (JDK) 8 o superior
- 💻 Sistema operativo compatible con Java Swing

### 🔨 Compilacion

```bash
# Navegar al directorio del proyecto
cd IPC1_P2_VJ2024

# Compilar todos los archivos Java
javac -d bin src/*.java

# Ejecutar el juego
java -cp bin Main
```

### 🆚 Usando VS Code

1. 📂 Abrir el proyecto en VS Code
2. 🔧 Asegurarse de tener la extension "Extension Pack for Java" instalada
3. ▶️ Ejecutar desde `Main.java` con F5 o Ctrl+F5

## 📁 Archivos Generados

### 🏆 scores.txt

Almacena las 5 mejores puntuaciones en formato:

```txt
NombreJugador,Puntuacion
```

### 💾 Juegos/*.bin

Archivos de partidas guardadas con formato de timestamp:

```txt
HH_mm_dd_MM_yyyy.bin
```

## 🛠️ Tecnologias Utilizadas

- **☕ Java Swing:** Framework para interfaz grafica
- **🎨 Java AWT:** Manejo de eventos y graficos
- **📁 Java IO:** Serializacion y manejo de archivos
- **🔀 Java Threading:** Concurrencia para game loops
- **📚 Java Collections:** Estructura de datos para entidades del juego

## 🏗️ Patrones de Diseno Implementados

1. **👁️ Observer Pattern:** Para manejo de eventos de teclado
2. **🔀 Thread Management:** Para concurrencia en movimientos
3. **💾 Serialization:** Para persistencia de datos
4. **🏛️ MVC Pattern:** Separacion de logica y presentacion

## 🔧 Caracteristicas Tecnicas

### 🔀 Concurrencia

- 🖥️ Thread principal para UI
- ⏱️ Thread separado para temporizador
- 🔄 Threads individuales para enemigos, nave e items
- 🔒 Sincronizacion para evitar condiciones de carrera

### 💾 Manejo de Memoria

- 🎯 Gestion automatica de recursos graficos
- 🧹 Limpieza de objetos eliminados del juego
- ⚡ Optimization de rendering

### 💿 Persistencia

- 💾 Serializacion de estado completo del juego
- 📁 Sistema de archivos para puntuaciones
- ⚠️ Manejo de excepciones para operaciones I/O

## 📝 Notas de Desarrollo

Este proyecto fue desarrollado como practica academica para el curso IPC1, demostrando:

- 🏗️ Programacion orientada a objetos
- 🖼️ Interfaces graficas con Swing
- 🔀 Manejo de threads y concurrencia
- 💾 Serializacion y persistencia de datos
- 🎮 Arquitectura de software para juegos

## 👨‍💻 Autor

**👨‍🎓 Estudiante:** 202300396  
**📚 Curso:** IPC1 - Introduccion a la Programacion y Computacion 1  
**🏫 Universidad de San Carlos de Guatemala**  
