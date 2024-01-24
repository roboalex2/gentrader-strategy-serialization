package at.gentrader.strategy.serialization.model.indicator;

import lombok.*;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsMiddleIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsUpperIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class BollingerBandsUpperIndicatorPojo implements IndicatorPojo, Serializable {

    private IndicatorPojo divergence;
    private IndicatorPojo parent;

    @Override
    public Indicator toTa4jIndicator(BarSeries series) {
        return new BollingerBandsUpperIndicator(new BollingerBandsMiddleIndicator(this.getParent().toTa4jIndicator(series)), this.getDivergence().toTa4jIndicator(series));
    }

    @Override
    public List<IndicatorPojo> getFlattenedIndicatorList() {
        List<IndicatorPojo> indicators = new ArrayList<>();
        indicators.add(this);
        indicators.addAll(divergence.getFlattenedIndicatorList());
        indicators.addAll(parent.getFlattenedIndicatorList());
        return indicators;
    }
}
