const books = [
  { title: "The Great Gatsby",       author: "F. Scott Fitzgerald", rating: 4.5, reviews: 2341,  img: "https://placehold.co/200x300/2C3038/F0F0F0?text=Book" },
  { title: "To Kill a Mockingbird",  author: "Harper Lee",          rating: 5,   reviews: 5120,  img: "https://placehold.co/200x300/880000/ffffff?text=Book" },
  { title: "1984",                   author: "George Orwell",       rating: 4,   reviews: 8764,  img: "https://placehold.co/200x300/0077cc/ffffff?text=Book" },
  { title: "Pride and Prejudice",    author: "Jane Austen",         rating: 4.5, reviews: 3210,  img: "https://placehold.co/200x300/2d6a4f/ffffff?text=Book" },
  { title: "The Catcher in the Rye", author: "J.D. Salinger",       rating: 3.5, reviews: 1987,  img: "https://placehold.co/200x300/7B2FF7/ffffff?text=Book" },
  { title: "Brave New World",        author: "Aldous Huxley",       rating: 2.5,   reviews: 4532,  img: "https://placehold.co/200x300/e76f51/ffffff?text=Book" },
  { title: "The Hobbit",             author: "J.R.R. Tolkien",      rating: 5,   reviews: 9870,  img: "https://placehold.co/200x300/457b9d/ffffff?text=Book" },
  { title: "Fahrenheit 451",         author: "Ray Bradbury",        rating: 4,   reviews: 3456,  img: "https://placehold.co/200x300/e63946/ffffff?text=Book" },
  { title: "The Alchemist",          author: "Paulo Coelho",        rating: 4.5, reviews: 7654,  img: "https://placehold.co/200x300/f4a261/1a1a2e?text=Book" },
  { title: "Don Quixote",            author: "Cervantes",           rating: 4,   reviews: 2100,  img: "https://placehold.co/200x300/264653/ffffff?text=Book" },
  { title: "Crime and Punishment",   author: "F. Dostoevsky",       rating: 5,   reviews: 6543,  img: "https://placehold.co/200x300/6d6875/ffffff?text=Book" },
  { title: "The Little Prince",      author: "A. de Saint-Exupéry", rating: 5,   reviews: 11230, img: "https://placehold.co/200x300/023e8a/ffffff?text=Book" },
];

//Constants
const PER_PAGE = 6;
const GAP_PX   = 20;

const track = document.getElementById("track");
const prevBtn = document.getElementById("book-prev");
const nextBtn = document.getElementById("book-next");

const totalPages = Math.ceil(books.length / PER_PAGE);
let currentPage = 0;

//Result function
export function initBookSlider() {
    books.forEach(book => {
        track.appendChild(createCard(book));
    });

    prevBtn.addEventListener('click', () => goTo(currentPage - 1));
    nextBtn.addEventListener('click', () => goTo(currentPage + 1));
    prevBtn.disabled = true;
}

//Function for generate correct count of start depend on rate

function renderStars(rating) {
    let stars = '';
    for (let i = 1; i <= 5; i++) {
        if (rating >= i) {
            stars += '<span class="star filled">★</span>';
        } else if (rating >= i - 0.5) {
            stars += '<span class="star half">★</span>';
        } else {
            stars += '<span class="star">★</span>';
        }
    }
    return stars;
}


//Create a card with book information

function createCard(book) {
    const card = document.createElement("div");
    card.className = 'book-card';
    card.innerHTML = `
        <img src="${book.img}" alt="${book.title}" class="book-card-img">

        <div class="book-card-body">
            <h3 class="book-card-title">${book.title}</h3>
            <p class="book-card-author">${book.author}</p>
            <div class="book-card-stars">
                ${renderStars(book.rating)}
                <span class="book-card__rating-count">(${book.reviews.toLocaleString()})</span>
            </div>
        </div>
    `;

    return card;
}

function goTo(page) {
    currentPage = page;

    const cardWidth = track.querySelector(".book-card").offsetWidth;
    const offset = page * PER_PAGE * (cardWidth + GAP_PX);

    track.style.transform = `translateX(-${offset}px)`;

    prevBtn.disabled = page === 0;
    nextBtn.disabled = page === totalPages - 1;

}