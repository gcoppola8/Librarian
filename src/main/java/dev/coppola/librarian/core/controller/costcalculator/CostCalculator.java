package dev.coppola.librarian.core.controller.costcalculator;

import dev.coppola.librarian.core.entity.Issue;
import org.joda.money.Money;

/**
 * @author gennaro.coppola@outlook.com
 * This interface can be used to calculate the cost of a book borrow.
 * The first 3 basic implementation that I am going to provide are:
 * - a cost free implementation => always free
 * - a fixed cost implementation => always a fixed amount
 * - a time based cost implementation => more you take - more you pay
 *
 * But it could be possible to implement a calculator based on some user properties,
 * for example if it is a VIP member.
 * Or a calculator that implement some discounts for some particular event.
 */
public interface CostCalculator {
    public Money calculate(Issue issue);
}
