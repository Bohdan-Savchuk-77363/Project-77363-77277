const slides = document.querySelectorAll('.slide');
const prevButton = document.querySelector('.arrow-prev');
const nextButton = document.querySelector('.arrow-next');
let IndexCurrent = 0;

prevButton.addEventListener('click', () => {
    slides[IndexCurrent].classList.remove('active');
    IndexCurrent = (IndexCurrent - 1 + slides.length) % slides.length;
    slides[IndexCurrent].classList.add('active');
});

nextButton.addEventListener('click', () => {
    slides[IndexCurrent].classList.remove('active');
    IndexCurrent = (IndexCurrent + 1) % slides.length;
    slides[IndexCurrent].classList.add('active');
});