package at.gentrader.strategy.serialization.model.rule;

import at.gentrader.strategy.serialization.model.indicator.IndicatorPojo;
import lombok.*;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.rules.OverIndicatorRule;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
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
    List<IndicatorPojo> getFlattenedIndicators() {
        return Stream.of(indicator.getFlattenedIndicatorList(), threshold.getFlattenedIndicatorList())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
