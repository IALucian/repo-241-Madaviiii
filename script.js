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

function animateName() {
  document.querySelectorAll("#nombre span").forEach((s, i) => {
    s.style.animationDelay = `${i * 0.4}s`;
  });
}

function createFlowers() {
  document.querySelectorAll(".flower").forEach(flower => {
    const bloom = document.createElement("div");
    bloom.className = "bloom";

    const core = document.createElement("div");
    core.className = "core";
    bloom.appendChild(core);

    for (let i = 0; i < 8; i++) {
      const p = document.createElement("div");
      p.className = "petal";
      p.style.transform =
        `rotate(${i * 45}deg) translateY(-30px)`;
      bloom.appendChild(p);
    }

    flower.appendChild(bloom);
  });
}

/* ESTRELLAS */
const stars = document.getElementById("stars");

for (let i = 0; i < 150; i++) {
  const s = document.createElement("div");
  s.classList.add("star");
  s.classList.add(Math.random() > 0.5 ? "blue" : "yellow");
  s.style.left = Math.random() * 100 + "vw";
  s.style.animationDuration = 12 + Math.random() * 20 + "s";
  stars.appendChild(s);
}
