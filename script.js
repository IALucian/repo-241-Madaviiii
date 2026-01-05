const intro = document.getElementById("intro");
const message = document.getElementById("message");
const finalSection = document.getElementById("final");

const startBtn = document.getElementById("startBtn");
const finalBtn = document.getElementById("finalBtn");
const bloomBtn = document.getElementById("bloomBtn");

const letters = document.querySelectorAll("#name span");
const flowers = document.querySelectorAll(".flower");
const music = document.getElementById("bgMusic");

// Paso 1: Intro → Mensaje
startBtn.addEventListener("click", () => {
    intro.classList.add("hidden");
    message.classList.remove("hidden");
});

// Paso 2: Mensaje → Final
finalBtn.addEventListener("click", () => {
    message.classList.add("hidden");
    finalSection.classList.remove("hidden");
});

// Paso 3: Animación final
bloomBtn.addEventListener("click", () => {

    // Fondo estrellas
    document.body.classList.add("stars");

    // Música
    music.volume = 0.6;
    music.play();

    // Iluminar nombre
    letters.forEach((letter, i) => {
        setTimeout(() => {
            letter.classList.add("active");
        }, i * 300);
    });

    // Flores
    setTimeout(() => {
        flowers.forEach(flower => {
            flower.style.animationPlayState = "running";
        });
    }, letters.length * 300 + 600);
});
