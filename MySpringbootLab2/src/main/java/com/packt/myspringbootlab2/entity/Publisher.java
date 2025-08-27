package com.packt.myspringbootlab2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publisher")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate establishedDate;
    private String address;
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if(!books.contains(book)) {
            books.add(book);
            book.setPublisher(this);
        }
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setPublisher(null);
    }
}
