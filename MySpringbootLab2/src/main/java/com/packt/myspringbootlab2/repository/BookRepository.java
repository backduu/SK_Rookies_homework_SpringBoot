package com.packt.myspringbootlab2.repository;
import com.packt.myspringbootlab2.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthorContainingIgnoreCase(String title);

    List<Book> findByTitleContainingIgnoreCase(String title);

//    Optional<Book> findByIdWithBookDetail(Long id);
//
//    Optional<Book> findByIsbnWithBookDetail(String isbn);

    boolean existsByIsbn(String isbn);
}
