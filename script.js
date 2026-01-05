const inicio = document.getElementById("inicio");
const mensaje = document.getElementById("mensaje");
const final = document.getElementById("final");

document.getElementById("btnInicio").onclick = () => {
  inicio.classList.remove("active");
  mensaje.classList.add("active");
};

document.getElementById("btnFinal").onclick = () => {
  mensaje.classList.remove("active");
  final.classList.add("active");
  animateName();
  createFlowers();
};

// NOMBRE ANIMADO
function animateName() {
  document.querySelectorAll("#nombre span").forEach((s, i) => {
    s.style.setProperty("--i", i);
  });
}

// FLORES
function createFlowers() {
  document.querySelectorAll(".flower").forEach(flower => {
    const bloom = document.createElement("div");
    bloom.className = "bloom";

    for (let i = 0; i < 5; i++) {
      const p = document.createElement("div");
      p.className = "petal";
      p.style.transform = `rotate(${i * 72}deg) translateY(-20px)`;
      bloom.appendChild(p);
    }

    flower.appendChild(bloom);
  });
}

// ESTRELLAS
const stars = document.getElementById("stars");
for (let i = 0; i < 120; i++) {
  const s = document.createElement("div");
  s.className = "star";
  if (Math.random() > 0.6) s.classList.add("yellow");
  s.style.left = Math.random() * 100 + "vw";
  s.style.animationDuration = 10 + Math.random() * 20 + "s";
  stars.appendChild(s);
}
