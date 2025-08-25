package com.packt.myspringbootlab2.controller;

import com.packt.myspringbootlab2.Exception.BusinessException;
import com.packt.myspringbootlab2.Service.BookService;
import com.packt.myspringbootlab2.dto.BookDTO;
import com.packt.myspringbootlab2.dto.BookDTO.*;
import com.packt.myspringbootlab2.entity.Book;
import com.packt.myspringbootlab2.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 전체 도서 조회
    @GetMapping
    public ResponseEntity<List<BookDTO.Response>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // ID로 도서 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO.Response> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // ISBN으로 도서 조회
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO.Response> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    // 저자명으로 도서 목록 조회
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDTO.Response>> getBooksByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    // 도서 등록
    @PostMapping
    public ResponseEntity<BookDTO.Response> createBook(@RequestBody BookDTO.Request request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }

    // 도서 수정
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO.Response> updateBook(@PathVariable Long id, @RequestBody BookDTO.Request request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    // 도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

