const PER_PAGE = 6;
const GAP_PX = 32; // 2rem ≈ 32px

const track = document.getElementById("track");
const prevBtn = document.getElementById("book-prev");
const nextBtn = document.getElementById("book-next");

let currentPage = 0;

export function initBookSlider() {
    const cards = track.querySelectorAll(".book-card");
    if (cards.length === 0) return;

    const totalPages = Math.ceil(cards.length / PER_PAGE);
    const cardWidth = cards[0].offsetWidth;

    function goTo(page) {
        currentPage = page;
        const offset = page * (PER_PAGE * cardWidth + (PER_PAGE - 1) * GAP_PX);
        track.style.transform = `translateX(-${offset}px)`;
        prevBtn.disabled = page === 0;
        nextBtn.disabled = page === totalPages - 1;
    }

    prevBtn.addEventListener('click', () => goTo(currentPage - 1));
    nextBtn.addEventListener('click', () => goTo(currentPage + 1));
    goTo(0);
}