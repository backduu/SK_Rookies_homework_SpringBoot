package com.packt.myspringbootlab2.repository;

import com.packt.myspringbootlab2.entity.Book;
import com.packt.myspringbootlab2.entity.BookDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail,Long> {
    Optional<BookDetail> findByBook_Id(Long id);

    List<BookDetail> findByPublisher(String publisher);
}
