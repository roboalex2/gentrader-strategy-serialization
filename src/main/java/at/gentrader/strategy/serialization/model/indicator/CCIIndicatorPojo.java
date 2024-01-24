package at.gentrader.strategy.serialization.model.indicator;

import lombok.*;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.CCIIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CCIIndicatorPojo implements IndicatorPojo, Serializable, AdjustableIndicator {

    private Boolean fixedValues = false;
    private List<Integer> barCounts = new ArrayList<>(List.of(0));

    @Override
    public Indicator toTa4jIndicator(BarSeries series) {
        return new CCIIndicator(series, barCounts.get(0));
    }

    @Override
    public List<IndicatorPojo> getFlattenedIndicatorList() {
        return Arrays.asList(this);
    }

    @Override
    public boolean hasFixedValues() {
        return fixedValues;
    }
}
