# 🚀 Space Shooter - Android Game

Un videojuego de naves espaciales desarrollado en **Kotlin** para **Android Studio**.

## Descripción

Space Shooter es un juego arcade donde controlas una nave espacial que debe defender la galaxia destruyendo enemigos. El juego utiliza **Canvas** de Android para renderizar gráficos en tiempo real a 60 FPS.

## Características

- **Nave del jugador** controlada por touch (toca y arrastra)
- **Disparo automático** al tocar la pantalla
- **3 tipos de enemigos** con diferentes velocidades y apariencias
- **Sistema de puntuación** basado en la dificultad del enemigo
- **3 vidas** con sistema de invencibilidad temporal al recibir daño
- **Efectos de partículas** para explosiones
- **Fondo animado** con estrellas en paralaje
- **Dificultad progresiva**: los enemigos aparecen más rápido conforme sube la puntuación
- **Pantalla de inicio** y **Game Over** con puntuación final

## Estructura del Proyecto

```
SpaceShooter/
├── app/src/main/
│   ├── java/com/spaceshooter/game/
│   │   ├── MainActivity.kt          # Pantalla de inicio
│   │   ├── GameActivity.kt          # Activity del juego
│   │   ├── GameOverActivity.kt      # Pantalla de Game Over
│   │   ├── engine/
│   │   │   ├── GameView.kt          # Motor del juego (renderizado + lógica)
│   │   │   └── GameThread.kt        # Hilo del juego (game loop a 60 FPS)
│   │   └── objects/
│   │       ├── Player.kt            # Nave del jugador
│   │       ├── Enemy.kt             # Enemigos (3 tipos)
│   │       ├── Bullet.kt            # Proyectiles
│   │       ├── Star.kt              # Estrellas del fondo
│   │       └── Explosion.kt         # Efectos de explosión (partículas)
│   └── res/
│       ├── layout/                   # Layouts XML
│       ├── values/                   # Strings, colores, temas
│       └── drawable/                 # Iconos vectoriales
```

## Cómo abrir en Android Studio

1. Abre **Android Studio**
2. Selecciona **File > Open**
3. Navega hasta la carpeta `SpaceShooter/` y selecciónala
4. Espera a que Gradle sincronice las dependencias
5. Conecta un dispositivo Android o usa el emulador
6. Presiona **Run** (▶️)

## Requisitos

- **Android Studio** Hedgehog (2023.1.1) o superior
- **JDK 8** o superior
- **Android SDK 34** (API level 34)
- **Min SDK 24** (Android 7.0 Nougat)
- **Kotlin 1.9.20**

## Controles

- **Toca y arrastra** para mover la nave
- **La nave dispara automáticamente** mientras tocas la pantalla
- **Esquiva** a los enemigos y **destruye** los que puedas

## Tecnologías

- **Kotlin** - Lenguaje de programación
- **Android Canvas** - Renderizado de gráficos 2D
- **SurfaceView** - Vista optimizada para renderizado en tiempo real
- **ConstraintLayout** - Layouts de UI
- **Material Design 3** - Componentes de interfaz
