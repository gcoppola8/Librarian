package dev.coppola.librarian.core.controller;

import dev.coppola.librarian.core.controller.costcalculator.CostCalculator;
import dev.coppola.librarian.core.entity.Book;
import dev.coppola.librarian.core.entity.Issue;
import dev.coppola.librarian.core.entity.Librarian;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDateTime;

public class IssueController {

    /** logger. */
    private static Logger logger = LoggerFactory.getLogger(IssueController.class);

    /** the current librarian that is executing the operations. */
    private Librarian currentOperator;

    /** Strategy pattern for cost calculation. */
    private CostCalculator costCalculator;

    /**
     * {@link IssueController#issue(Book, String, LocalDateTime)}
     * Issue easy method with starting time = now.
     */
    public Issue issue(Book book, String to) throws Exception {
        return issue(book, to, LocalDateTime.now(Clock.systemUTC()));
    }

    /**
     * Create a new Issue
     *
     * @param book The book that we are issuing.
     * @param to   The name of the person that we are issuing to.
     * @param from {@link LocalDateTime} when issue starts.
     * @return An {@link Issue} object with the basic info.
     * @throws Exception
     */
    public Issue issue(Book book, String to, LocalDateTime from) throws Exception {

        if (from.isAfter(LocalDateTime.now(Clock.systemUTC()))) {
            logger.debug("Starting time is after the current time. This is a VIOLATION. Starting time is going to be actualized.");
            from = LocalDateTime.now(Clock.systemUTC());
        }

        return Issue.builder()
                .id(1L)
                .begin(from)
                .book(book)
                .issuedTo(to)
                .issuedStartedBy(this.currentOperator)
                .build();
    }

    /**
     * {@link IssueController#closeIssue(Issue, LocalDateTime)}
     * closeIssue easy method with closing time = now.
     */
    public Issue closeIssue(Issue issue) throws Exception {
        return closeIssue(issue, LocalDateTime.now(Clock.systemUTC()));
    }

    /**
     * Close an Issue, because the owner of it brought back the book.
     *
     * @param issue The issue that we are going to close.
     * @param closeAt {@link LocalDateTime} when issue ends.
     * @return An {@link Issue} object with complete info.
     * @throws Exception
     */
    public Issue closeIssue(Issue issue, LocalDateTime closeAt) throws Exception {
        issue.setEnd(closeAt);
        issue.setIssuedEndedBy(this.currentOperator);

        Money result = calculateCost(issue);
        issue.setTotalCost(result);
        return issue;
    }

    private Money calculateCost(Issue issue) throws NullPointerException {
        if(this.costCalculator == null) {
            throw new NullPointerException("IssueController#costCalculator needs to be set before closing an issue.");
        }
        return this.costCalculator.calculate(issue);
    }
    public void setCostCalculator(CostCalculator costCalculator) {
        this.costCalculator = costCalculator;
    }
    public void setCurrentOperator(Librarian currentOperator) {
        this.currentOperator = currentOperator;
    }
}
