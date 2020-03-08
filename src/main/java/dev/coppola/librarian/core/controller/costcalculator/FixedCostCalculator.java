package dev.coppola.librarian.core.controller.costcalculator;

import dev.coppola.librarian.core.entity.Issue;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class FixedCostCalculator implements CostCalculator {

    private final CurrencyUnit currency;
    private final double amount;

    public FixedCostCalculator(CurrencyUnit currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public Money calculate(Issue issue) {
        return Money.of(currency, amount);
    }
}
