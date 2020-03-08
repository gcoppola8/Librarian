package dev.coppola.librarian.core.controller;

import dev.coppola.librarian.core.controller.costcalculator.FixedCostCalculator;
import dev.coppola.librarian.core.entity.Book;
import dev.coppola.librarian.core.entity.Genre;
import dev.coppola.librarian.core.entity.Issue;
import dev.coppola.librarian.core.entity.Librarian;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class IssueControllerTest {

    @Test
    public void issue() throws Exception {
        /* Creation of a Librarian */
        Librarian librarian1 = new Librarian();
        librarian1.setId(1L);
        librarian1.setEmail("email@example.com");
        librarian1.setName("Librarian1");
        librarian1.setPassword("password123".toCharArray());

        /* Creation of a book */
        Book mobyDick = Book.builder()
                .id(1L)
                .genre(Genre.LITERATURE)
                .author("Herman Melville")
                .title("The Whale")
                .published(LocalDate.of(1851, 11, 14))
                .build();

        /* Creation of an issueController */
        IssueController issueController = new IssueController();
        issueController.setCurrentOperator(librarian1);

        /*
         *  Issuing a book
         *  Issuing time is in the year 2030 this is a clear violation.
         *  We are going to test that the time is actualized.
         */
        Issue issueToGennaro = issueController.issue(
                mobyDick,
                "Gennaro Coppola",
                LocalDateTime.of(2030, 1, 1, 13, 40, 32));

        System.err.println(issueToGennaro);
        Assert.assertEquals("Gennaro Coppola", issueToGennaro.getIssuedTo());
        Assert.assertEquals("Librarian1", issueToGennaro.getIssuedStartedBy().getName());
        Assert.assertTrue(issueToGennaro.getBegin().isBefore(LocalDateTime.now(Clock.systemUTC())));
        Assert.assertNull(issueToGennaro.getIssuedEndedBy());
        Assert.assertNull(issueToGennaro.getEnd());
        Assert.assertNull(issueToGennaro.getTotalCost());
    }

    @Test
    public void closeIssue() throws Exception {
        /* Creation of a book */
        Book mobyDick = Book.builder()
                .id(1L)
                .genre(Genre.LITERATURE)
                .author("Herman Melville")
                .title("The Whale")
                .published(LocalDate.of(1851, 11, 14))
                .build();

        /* Creation of a Librarian */
        Librarian librarian1 = new Librarian();
        librarian1.setId(1L);
        librarian1.setEmail("email@example.com");
        librarian1.setName("Librarian1");
        librarian1.setPassword("password123".toCharArray());

        Librarian librarian2 = new Librarian();
        librarian2.setId(1L);
        librarian2.setEmail("email@example.com");
        librarian2.setName("Librarian2");
        librarian2.setPassword("password123".toCharArray());

        Issue issue = Issue.builder()
                .id(1L)
                .begin(LocalDateTime.now(Clock.systemUTC()).minusMinutes(25).minusHours(1))
                .book(mobyDick)
                .issuedStartedBy(librarian1)
                .issuedTo("Dom")
                .build();

        IssueController issueController = new IssueController();
        issueController.setCurrentOperator(librarian2);
        issueController.setCostCalculator(new FixedCostCalculator(CurrencyUnit.USD, 5.00));

        LocalDateTime endOfIssue = LocalDateTime.now(Clock.systemUTC());
        issue = issueController.closeIssue(issue, endOfIssue);

        Assert.assertEquals(endOfIssue, issue.getEnd());
        Assert.assertEquals("Librarian2", issue.getIssuedEndedBy().getName());
        Assert.assertEquals("Expect a fixed cost of $5 whatever are the issue conditions.",
                Money.of(CurrencyUnit.USD, 5), issue.getTotalCost());
    }
}