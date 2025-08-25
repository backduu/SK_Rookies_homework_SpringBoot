package com.packt.myspringbootlab2.Service;

import com.packt.myspringbootlab2.dto.*;
import com.packt.myspringbootlab2.entity.*;
import com.packt.myspringbootlab2.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDTO.Response> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookDTO.Response::fromEntity)
                .collect(Collectors.toList());
    }

    public BookDTO.Response getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("도서를 찾지 못했습니다."));
        return BookDTO.Response.fromEntity(book);
    }


    public BookDTO.Response getBookByIsbn(String isbn) {
        Book book = (Book) bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("도서를 찾지 못했습니다."));
        return BookDTO.Response.fromEntity(book);

    }

    public List<BookDTO.Response> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author).stream()
                .map(BookDTO.Response::fromEntity)
                .collect(Collectors.toList());
    }

    public List<BookDTO.Response> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(BookDTO.Response::fromEntity)
                .collect(Collectors.toList());
    }
    @Transactional
    public BookDTO.Response createBook(BookDTO.Request request) {
        BookDetail detail = BookDetail.builder()
                .description(request.getDetailRequest().getDescription())
                .language(request.getDetailRequest().getLanguage())
                .pageCount(request.getDetailRequest().getPageCount())
                .publisher(request.getDetailRequest().getPublisher())
                .coverImageUrl(request.getDetailRequest().getCoverImageUrl())
                .edition(request.getDetailRequest().getEdition())
                .build();

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .publishDate(request.getPublishDate())
                .bookDetail(detail)
                .build();

        return BookDTO.Response.fromEntity(bookRepository.save(book));

    }

    @Transactional
    public BookDTO.Response updateBook(Long id, BookDTO.Request request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("도서를 찾지 못했습니다."));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setPublishDate(request.getPublishDate());

        if (book.getBookDetail() == null) {
            book.setBookDetail(new BookDetail());
        }

        BookDetail detail = book.getBookDetail();
        detail.setDescription(request.getDetailRequest().getDescription());
        detail.setLanguage(request.getDetailRequest().getLanguage());
        detail.setPageCount(request.getDetailRequest().getPageCount());
        detail.setPublisher(request.getDetailRequest().getPublisher());
        detail.setCoverImageUrl(request.getDetailRequest().getCoverImageUrl());
        detail.setEdition(request.getDetailRequest().getEdition());

        return BookDTO.Response.fromEntity(bookRepository.save(book));
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        bookRepository.delete(book);
    }
}

