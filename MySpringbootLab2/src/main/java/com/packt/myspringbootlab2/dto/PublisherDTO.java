package com.packt.myspringbootlab2.dto;

import com.packt.myspringbootlab2.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherDTO {
    private String description;
    private String language;
    private Integer pageCount;
    private String publisher;
    private String coverImageUrl;
    private String edition;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SimpleResponse {
        private Long id;
        private String name;
        private Long bookCount;
    }

    public static SimpleResponse fromEntityWithCount(Publisher publisher, Long count) {
        return SimpleResponse.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .bookCount(count)
                .build();
    }

}
