export function initSlider() {
    const slides = document.querySelectorAll('.slide');
    const preArrowvButton = document.getElementById('slide-prev-arrow');
    const nextArrowButton = document.getElementById('slide-next-arrow');
    let IndexCurrent = 0;
    
    
    preArrowvButton.addEventListener('click', () => {
        slides[IndexCurrent].classList.remove('active');
        IndexCurrent = (IndexCurrent - 1 + slides.length) % slides.length;
        slides[IndexCurrent].classList.add('active');
    });
    
    nextArrowButton.addEventListener('click', () => {
        slides[IndexCurrent].classList.remove('active');
        IndexCurrent = (IndexCurrent + 1) % slides.length;
        slides[IndexCurrent].classList.add('active');
    });
}
