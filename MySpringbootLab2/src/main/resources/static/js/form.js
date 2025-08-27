const API_BASE_URL = "http://localhost:8080";

document.getElementById("bookForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const formData = new FormData(e.target);
    const book = {
        title: formData.get("title"),
        author: formData.get("author"),
        price: formData.get("price")
    };

    if (!validateBook(book)) {
        alert("모든 항목을 올바르게 입력하세요.");
        return;
    }

    fetch(`${API_BASE_URL}/books`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(book)
    })
        .then(res => res.json())
        .then(data => {
            loadBooks();
        });
});

function validateBook(book) {
    return book.title && book.author && book.price;
}

function loadBooks() {
    fetch(`${API_BASE_URL}/books`)
        .then(res => res.json())
        .then(books => renderBookTable(books));
}

function renderBookTable(books) {
    const tableContainer = document.getElementById("bookTable");
    tableContainer.innerHTML = `
    <table border="1" cellpadding="8">
      <tr><th>제목</th><th>저자</th><th>가격</th></tr>
      ${books.map(book => `
        <tr>
          <td>${book.title}</td>
          <td>${book.author}</td>
          <td>${book.price}</td>
        </tr>
      `).join("")}
    </table>
  `;
}
