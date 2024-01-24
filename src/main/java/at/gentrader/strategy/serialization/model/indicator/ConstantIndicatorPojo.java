package at.gentrader.strategy.serialization.model.indicator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.helpers.ConstantIndicator;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConstantIndicatorPojo implements IndicatorPojo, Serializable, AdjustableIndicator {

    private Boolean fixedValues = false;
    private List<Double> values = new ArrayList<>(List.of(0d));

    @Override
    public Indicator toTa4jIndicator(BarSeries series) {
        return new ConstantIndicator<Num>(series, DecimalNum.valueOf(values.get(0)));
    }

    public boolean hasFixedValues() {
        return fixedValues;
    }

    @JsonIgnore
    @Override
    public List<Double> getBarCounts() {
        return values;
    }

    @Override
    public List<IndicatorPojo> getFlattenedIndicatorList() {
        return Arrays.asList(this);
    }
}
