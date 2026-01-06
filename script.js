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
  setTimeout(() => {
    overlay.classList.add('active');
  }, 600);
}

/* =========================
   NOMBRE MADAVI (LETRA X LETRA)
========================= */
function showMadavi() {
  const text = "Madavi";
  const container = document.getElementById("madavi");
  container.innerHTML = "";

  [...text].forEach((char, index) => {
    const span = document.createElement("span");
    span.textContent = char;
    span.style.animationDelay = `${index * 0.35}s`;
    container.appendChild(span);
  });
}

/* =========================
   FLORES – CONSTRUCCIÓN LIMPIA
========================= */
function animateFlowers() {
  const flowers = document.querySelectorAll('.flower');

  flowers.forEach((flower, flowerIndex) => {
    flower.style.opacity = 0;

    setTimeout(() => {
      flower.style.opacity = 1;

      const petals = flower.querySelectorAll('.petals span');

      petals.forEach((petal, petalIndex) => {
        setTimeout(() => {
          petal.style.opacity = 1;
          petal.style.transform = 'scale(1)';
        }, petalIndex * 120);
      });

    }, flowerIndex * 800);
  });
}

/* =========================
   ESTRELLAS / PARTÍCULAS
========================= */
function generateStars() {
  const starCount = 90;

  for (let i = 0; i < starCount; i++) {
    const star = document.createElement("div");
    star.classList.add("star");

    const size = Math.random() * 4 + 3;
    star.style.width = `${size}px`;
    star.style.height = `${size}px`;

    star.style.left = Math.random() * window.innerWidth + "px";
    star.style.top = Math.random() * window.innerHeight + "px";

    const duration = Math.random() * 25 + 15;
    star.style.animationDuration = `${duration}s`;

    star.style.opacity = Math.random() * 0.8 + 0.2;

    document.body.appendChild(star);
  }
}

/* =========================
   FIX AUTOPLAY MÓVIL
========================= */
window.addEventListener(
  'click',
  () => {
    const music = document.getElementById("music");
    if (music && music.paused) {
      music.play();
    }
  },
  { once: true }
);
