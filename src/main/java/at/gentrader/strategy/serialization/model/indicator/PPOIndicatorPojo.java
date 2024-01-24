package at.gentrader.strategy.serialization.model.indicator;

import lombok.*;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.PPOIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class PPOIndicatorPojo implements IndicatorPojo, Serializable, AdjustableIndicator {

    private Boolean fixedValues = false;
    private IndicatorPojo parent;
    private List<Integer> barCounts = new ArrayList<>(List.of(12, 26));

    @Override
    public Indicator toTa4jIndicator(BarSeries series) {
        return new PPOIndicator(this.getParent().toTa4jIndicator(series), barCounts.get(0), barCounts.get(1));
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
