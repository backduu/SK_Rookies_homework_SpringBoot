package com.packt.myspringbootlab2.repository;

import com.packt.myspringbootlab2.entity.Book;
import com.packt.myspringbootlab2.entity.BookDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface BookDetailRepository extends JpaRepository<Book,Long> {
    Optional<BookDetail> findByBookId(Long bookId);

    @EntityGraph(attributePaths = {"book"})
    Optional<BookDetail> findByIdWithBook(Long id);

    List<BookDetail> findByBook_Publisher(String publisher);
}
