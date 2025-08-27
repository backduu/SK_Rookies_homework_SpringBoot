// 전역 변수
const API_BASE_URL = "http://localhost:8080";
let editingBookId = null;

// DOM 요소 가져오기
const bookForm = document.getElementById("bookForm");
const bookTableBody = document.getElementById("bookTableBody");
const submitButton = document.querySelector("button[type='submit']");
const cancelButton = document.createElement("button");
cancelButton.textContent = "취소";
cancelButton.type = "button";
cancelButton.style.marginLeft = "10px";
submitButton.after(cancelButton);

// 도서 등록 함수
function createBook(bookData) {
    fetch(`${API_BASE_URL}/books`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookData),
    })
        .then(res => res.json())
        .then(data => {
            alert("도서 등록 완료!");
            bookForm.reset();
            loadBooks();
        })
        .catch(err => showError("도서 등록 중 오류 발생"));
}

// 도서 삭제 함수
function deleteBook(bookId) {
    fetch(`${API_BASE_URL}/books/${bookId}`, {
        method: "DELETE",
    })
        .then(() => {
            alert("도서 삭제 완료!");
            loadBooks();
        })
        .catch(err => showError("도서 삭제 중 오류 발생"));
}

// 도서 수정 전 데이터 로드
function editBook(bookId) {
    fetch(`${API_BASE_URL}/books/${bookId}`)
        .then(res => res.json())
        .then(book => {
            document.getElementById("title").value = book.title;
            document.getElementById("author").value = book.author;
            document.getElementById("price").value = book.price;
            editingBookId = bookId;
            submitButton.textContent = "수정";
        })
        .catch(err => showError("도서 정보 불러오기 실패"));
}

// 도서 수정 처리
function updateBook(bookId, bookData) {
    fetch(`${API_BASE_URL}/books/${bookId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookData),
    })
        .then(res => res.json())
        .then(data => {
            alert("도서 수정 완료!");
            bookForm.reset();
            editingBookId = null;
            submitButton.textContent = "등록";
            loadBooks();
        })
        .catch(err => showError("도서 수정 중 오류 발생"));
}

// 에러 메시지 출력 함수
function showError(message) {
    alert(message);
}

// 도서 목록 로드
function loadBooks() {
    fetch(`${API_BASE_URL}/books`)
        .then(res => res.json())
        .then(books => {
            bookTableBody.innerHTML = "";
            books.forEach(book => {
                const row = document.createElement("tr");
                row.innerHTML = `
          <td>${book.title}</td>
          <td>${book.author}</td>
          <td>${book.price}</td>
          <td>${book.publishDate || "-"}</td>
          <td>${book.publisher?.name || "-"}</td>
          <td>
            <button onclick="editBook(${book.id})">수정</button>
            <button onclick="deleteBook(${book.id})">삭제</button>
          </td>
        `;
                bookTableBody.appendChild(row);
            });
        })
        .catch(err => showError("도서 목록 불러오기 실패"));
}

// 폼 제출 이벤트 처리
bookForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const formData = new FormData(bookForm);
    const bookData = {
        title: formData.get("title").trim(),
        author: formData.get("author").trim(),
        price: parseInt(formData.get("price")),
    };

    if (editingBookId) {
        updateBook(editingBookId, bookData);
    } else {
        createBook(bookData);
    }
});

// 취소 버튼 이벤트 처리
cancelButton.addEventListener("click", function () {
    bookForm.reset();
    editingBookId = null;
    submitButton.textContent = "등록";
});

// 초기 로딩
document.addEventListener("DOMContentLoaded", loadBooks);
