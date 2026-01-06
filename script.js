/* =========================
   CONTROL DE FASES
========================= */
function goToPhase(num) {
  // Ocultar todas las escenas
  document.querySelectorAll('.scene').forEach(scene => {
    scene.classList.remove('active');
  });

  // Mostrar la fase seleccionada
  const target = document.getElementById(`phase${num}`);
  if (target) target.classList.add('active');

  // Iniciar fase final
  if (num === 3) {
    startPhase3();
  }
}

/* =========================
   INICIO FASE 3
========================= */
function startPhase3() {
  darkenBackground();
  showMadavi();
  animateFlowers();
  generateStars();
}

/* =========================
   OSCURECER FONDO
========================= */
function darkenBackground() {
  const overlay = document.getElementById('darkOverlay');
  if (!overlay) return;

  setTimeout(() => {
    overlay.classList.add('active');
  }, 600);
}

/* =========================
   NOMBRE MADAVI (LETRA X LETRA)
========================= */
function showMadavi() {
  const text = "Madavi 💛";
  const container = document.getElementById("madavi");
  if (!container) return;

  container.innerHTML = "";

  [...text].forEach((char, index) => {
    const span = document.createElement("span");
    span.textContent = char;
    span.style.animationDelay = `${index * 0.25}s`;
    container.appendChild(span);
  });
}

/* =========================
   FLORES – APARICIÓN ESCALONADA
========================= */
function animateFlowers() {
  const flowers = document.querySelectorAll('.flower');

  flowers.forEach((flower, index) => {
    setTimeout(() => {
      flower.classList.add("bloom");
    }, index * 500);
  });
}

/* =========================
   ESTRELLAS / PARTÍCULAS
========================= */
function generateStars() {
  const starCount = 80;

  for (let i = 0; i < starCount; i++) {
    const star = document.createElement("div");
    star.classList.add("star");

    const size = Math.random() * 3 + 2;
    star.style.width = `${size}px`;
    star.style.height = `${size}px`;

    star.style.left = Math.random() * window.innerWidth + "px";
    star.style.top = Math.random() * window.innerHeight + "px";

    star.style.opacity = Math.random() * 0.7 + 0.3;
    star.style.animationDuration = `${Math.random() * 20 + 10}s`;

    document.body.appendChild(star);
  }
}

/* =========================
   FIX AUTOPLAY EN MÓVIL
========================= */
window.addEventListener(
  'click',
  () => {
    const music = document.getElementById("music");
    if (music && music.paused) {
      music.play().catch(() => {});
    }
  },
  { once: true }
);
