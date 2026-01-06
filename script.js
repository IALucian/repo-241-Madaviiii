/* =========================
   CONTROL DE FASES
========================= */
function goToPhase(num) {
  document.querySelectorAll('.scene').forEach(scene => {
    scene.classList.remove('active');
  });

  document.getElementById(`phase${num}`).classList.add('active');

  if (num === 3) {
    startPhase3();
  }
}

/* =========================
   FASE 3 – EFECTOS
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
  setTimeout(() => {
    overlay.classList.add('active');
  }, 500);
}

/* =========================
   NOMBRE MADAVI (LETRA X LETRA)
========================= */
function showMadavi() {
  const name = "Madavi";
  const container = document.getElementById("madavi");
  container.innerHTML = "";

  [...name].forEach((letter, i) => {
    const span = document.createElement("span");
    span.textContent = letter;
    span.style.animationDelay = `${i * 0.4}s`;
    container.appendChild(span);
  });
}

/* =========================
   ANIMACIÓN FLORES PROGRESIVAS
========================= */
function animateFlowers() {
  const flowers = document.querySelectorAll('.flower');

  flowers.forEach((flower, index) => {
    flower.style.opacity = 0;

    setTimeout(() => {
      flower.style.opacity = 1;

      const petals = flower.querySelectorAll('.petals span');
      petals.forEach((petal, i) => {
        petal.style.opacity = 0;
        petal.style.transform += ' scale(0)';
        setTimeout(() => {
          petal.style.transition = 'all 0.5s ease';
          petal.style.opacity = 1;
          petal.style.transform = petal.style.transform.replace('scale(0)', 'scale(1)');
        }, i * 80);
      });

    }, index * 600);
  });
}

/* =========================
   ESTRELLAS / PARTÍCULAS
========================= */
function generateStars() {
  const starCount = 70;

  for (let i = 0; i < starCount; i++) {
    const star = document.createElement("div");
    star.classList.add("star");

    const size = Math.random() * 4 + 2;
    star.style.width = `${size}px`;
    star.style.height = `${size}px`;

    star.style.left = Math.random() * window.innerWidth + "px";
    star.style.top = Math.random() * window.innerHeight + "px";

    const duration = Math.random() * 20 + 15;
    star.style.animationDuration = `${duration}s`;

    star.style.opacity = Math.random();

    document.body.appendChild(star);
  }
}

/* =========================
   AUTO-PLAY MÚSICA (MÓVIL FIX)
========================= */
window.addEventListener('click', () => {
  const music = document.getElementById("music");
  if (music.paused) {
    music.play();
  }
}, { once: true });
