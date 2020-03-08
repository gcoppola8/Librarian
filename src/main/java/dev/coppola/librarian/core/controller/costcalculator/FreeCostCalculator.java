package dev.coppola.librarian.core.controller.costcalculator;

import dev.coppola.librarian.core.entity.Issue;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class FreeCostCalculator implements CostCalculator {

    @Override
    public Money calculate(Issue issue) {
        return Money.zero(CurrencyUnit.USD);
    }
}
