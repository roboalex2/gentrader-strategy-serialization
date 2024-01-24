package at.gentrader.strategy.serialization.model.indicator;

import java.util.List;

public interface AdjustableIndicator {
    boolean hasFixedValues();

    List getBarCounts();
}
