package dev.coppola.librarian.core.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Book {

    private Long id;
    private String title;
    private String author;
    private LocalDate published;
    private Genre genre;
}
