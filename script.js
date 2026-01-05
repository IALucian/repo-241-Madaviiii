// ==============================
// REFERENCIAS A ELEMENTOS
// ==============================
const fase1 = document.getElementById("fase1");
const fase2 = document.getElementById("fase2");
const fase3 = document.getElementById("fase3");

const btnIniciar = document.getElementById("btnIniciar");
const btnFinalizar = document.getElementById("btnFinalizar");

const musica = document.getElementById("musica");
const letrasNombre = document.querySelectorAll("#nombre span");

// ==============================
// EVENTOS
// ==============================

// FASE 1 → FASE 2
btnIniciar.addEventListener("click", () => {
  fase1.classList.remove("activa");
  fase2.classList.add("activa");

  // Música (NO se toca, tal como pediste)
  musica.play();
});

// FASE 2 → FASE 3
btnFinalizar.addEventListener("click", () => {
  fase2.classList.remove("activa");
  fase3.classList.add("activa");

  animarNombre();
});

// ==============================
// FUNCIONES
// ==============================

// Animación del nombre letra por letra
function animarNombre() {
  letrasNombre.forEach((letra, index) => {
    letra.style.animationDelay = `${index * 0.5}s`;
  });
}

