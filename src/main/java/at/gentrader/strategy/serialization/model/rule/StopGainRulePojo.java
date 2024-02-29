package at.gentrader.strategy.serialization.model.rule;

import at.gentrader.strategy.serialization.model.indicator.IndicatorPojo;
import lombok.*;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.rules.StopGainRule;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StopGainRulePojo extends RulePojo implements AdjustableRule {

    private Boolean fixedValues = false;
    private IndicatorPojo indicator;
    private List<Double> values = new ArrayList<>(List.of(0.0));

    @Override
    public Rule toTa4jRule(BarSeries series) {
        Rule result = new StopGainRule((ClosePriceIndicator) indicator.toTa4jIndicator(series), values.get(0));
        return super.linkLogicRules(result, series);
    }

    @Override
    List<IndicatorPojo> getFlattenedIndicators() {
        return indicator.getFlattenedIndicatorList();
    }

    @Override
    public boolean hasFixedValues() {
        return fixedValues;
    }
}
