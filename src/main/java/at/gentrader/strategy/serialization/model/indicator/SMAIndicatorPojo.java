package at.gentrader.strategy.serialization.model.indicator;

import lombok.*;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.SMAIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class SMAIndicatorPojo implements IndicatorPojo, Serializable, AdjustableIndicator {

    private Boolean fixedValues = false;
    private IndicatorPojo parent;
    private List<Integer> barCounts = new ArrayList<>(List.of(0));

    @Override
    public Indicator toTa4jIndicator(BarSeries series) {
        return new SMAIndicator(this.getParent().toTa4jIndicator(series), barCounts.get(0));
    }

    @Override
    public boolean hasFixedValues() {
        return fixedValues;
    }

    @Override
    public List<IndicatorPojo> getFlattenedIndicatorList() {
        List<IndicatorPojo> indicators = new ArrayList<>();
        indicators.add(this);
        indicators.addAll(parent.getFlattenedIndicatorList());
        return indicators;
    }
}
