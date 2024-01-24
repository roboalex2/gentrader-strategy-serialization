package at.gentrader.strategy.serialization.model.rule;

import at.gentrader.strategy.serialization.model.indicator.IndicatorPojo;
import lombok.*;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.rules.OverIndicatorRule;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
public class OverIndicatorRulePojo extends RulePojo {

    private IndicatorPojo indicator;
    private IndicatorPojo threshold;

    @Override
    public Rule toTa4jRule(BarSeries series) {
        Rule result = new OverIndicatorRule(indicator.toTa4jIndicator(series), threshold.toTa4jIndicator(series));
        return super.linkLogicRules(result, series);
    }

    @Override
    List<IndicatorPojo> getIndicators() {
        return List.of(indicator, threshold);
    }
}
