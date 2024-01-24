package at.gentrader.strategy.serialization.model.rule;

import at.gentrader.strategy.serialization.model.indicator.IndicatorPojo;
import lombok.*;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.rules.UnderIndicatorRule;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UnderIndicatorRulePojo extends RulePojo {
    private IndicatorPojo indicator;
    private IndicatorPojo threshold;

    @Override
    public Rule toTa4jRule(BarSeries series) {
        Rule result = new UnderIndicatorRule(indicator.toTa4jIndicator(series), threshold.toTa4jIndicator(series));
        return super.linkLogicRules(result, series);
    }

    @Override
    List<IndicatorPojo> getIndicators() {
        return List.of(indicator, threshold);
    }
}
