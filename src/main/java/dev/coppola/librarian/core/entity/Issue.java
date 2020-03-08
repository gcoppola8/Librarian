package dev.coppola.librarian.core.entity;

import lombok.Builder;
import lombok.Data;
import org.joda.money.Money;
import java.time.LocalDateTime;

@Data
@Builder
public class Issue {
    private Long id;
    private Book book;
    private LocalDateTime begin;
    private Librarian issuedStartedBy;
    private LocalDateTime end;
    private Librarian issuedEndedBy;
    private String issuedTo;
    private Money totalCost;
}
