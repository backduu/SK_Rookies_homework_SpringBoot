package com.packt.myspringbootlab2.dto;
import com.packt.myspringbootlab2.entity.Book;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


public class BookDTO {

    @Getter @Setter
    public static class BookCreateRequest {

        @NotBlank(message = "도서 제목은 필수입니다.")
        private String title;

        @NotBlank(message = "저자 이름은 필수입니다.")
        private String author;

        @NotBlank(message = "ISBN은 필수입니다.")
        private String isbn;

        @NotNull(message = "가격은 필수입니다.")
        @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
        private Integer price;

        @NotNull(message = "출판일은 필수입니다.")
        private LocalDate publishDate;

        public Book toEntity() {
            return Book.builder()
                    .title(title)
                    .author(author)
                    .isbn(isbn)
                    .price(price)
                    .publishDate(publishDate)
                    .build();
        }
    }

    @Getter @Setter
    public static class BookUpdateRequest {

        @Size(min = 1, max = 100, message = "제목은 1~100자 사이여야 합니다.")
        private String title;

        @Size(min = 1, max = 50, message = "저자 이름은 1~50자 사이여야 합니다.")
        private String author;

        @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
        private Integer price;

        private LocalDate publishDate;
    }

    @Getter @Setter
    public static class BookResponse {

        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;

        public static BookResponse from(Book book) {
            BookResponse response = new BookResponse();
            response.setId(book.getId());
            response.setTitle(book.getTitle());
            response.setAuthor(book.getAuthor());
            response.setIsbn(book.getIsbn());
            response.setPrice(book.getPrice());
            response.setPublishDate(book.getPublishDate());
            return response;
        }
    }

}

