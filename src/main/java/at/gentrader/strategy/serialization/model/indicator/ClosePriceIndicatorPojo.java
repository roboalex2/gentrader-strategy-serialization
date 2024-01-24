package at.gentrader.strategy.serialization.model.indicator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
public class ClosePriceIndicatorPojo implements IndicatorPojo, Serializable {

    @Override
    public Indicator toTa4jIndicator(BarSeries series) {
        return new ClosePriceIndicator(series);
    }

    @Override
    public List<IndicatorPojo> getFlattenedIndicatorList() {
        return Arrays.asList(this);
    }
}
