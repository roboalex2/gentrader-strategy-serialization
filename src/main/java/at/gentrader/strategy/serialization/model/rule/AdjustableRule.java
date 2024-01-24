package at.gentrader.strategy.serialization.model.rule;

import java.util.List;

public interface AdjustableRule {
    boolean hasFixedValues();

    List<Double> getValues();
}
