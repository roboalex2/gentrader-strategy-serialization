package at.gentrader.strategy.serialization.model.indicator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.num.Num;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClosePriceIndicatorPojo.class, name = "ClosePriceIndicator"),
        @JsonSubTypes.Type(value = MACDIndicatorPojo.class, name = "MACDIndicator"),
        @JsonSubTypes.Type(value = RAVIIndicatorPojo.class, name = "RAVIIndicator"),
        @JsonSubTypes.Type(value = CMOIndicatorPojo.class, name = "CMOIndicator"),
        @JsonSubTypes.Type(value = CCIIndicatorPojo.class, name = "CCIIndicator"),
        @JsonSubTypes.Type(value = ADXIndicatorPojo.class, name = "ADXIndicator"),
        @JsonSubTypes.Type(value = ATRIndicatorPojo.class, name = "ATRIndicator"),
        @JsonSubTypes.Type(value = PVOIndicatorPojo.class, name = "PVOIndicator"),
        @JsonSubTypes.Type(value = StandardDeviationIndicatorPojo.class, name = "StandardDeviationIndicator"),
        @JsonSubTypes.Type(value = RSIIndicatorPojo.class, name = "RSIIndicator"),
        @JsonSubTypes.Type(value = ROCIndicatorPojo.class, name = "ROCIndicator"),
        @JsonSubTypes.Type(value = DPOIndicatorPojo.class, name = "DPOIndicator"),
        @JsonSubTypes.Type(value = PPOIndicatorPojo.class, name = "PPOIndicator"),
        @JsonSubTypes.Type(value = EMAIndicatorPojo.class, name = "EMAIndicator"),
        @JsonSubTypes.Type(value = SMAIndicatorPojo.class, name = "SMAIndicator"),
        @JsonSubTypes.Type(value = HMAIndicatorPojo.class, name = "HMAIndicator"),
        @JsonSubTypes.Type(value = BollingerBandsLowerIndicatorPojo.class, name = "BollingerBandsLowerIndicator"),
        @JsonSubTypes.Type(value = BollingerBandsUpperIndicatorPojo.class, name = "BollingerBandsUpperIndicator"),
        @JsonSubTypes.Type(value = ConstantIndicatorPojo.class, name = "ConstantIndicator")
})
public interface IndicatorPojo {
    @JsonIgnore
    Indicator<Num> toTa4jIndicator(BarSeries series);

    // Basically returns a list containing the indicator itself and its parents in a flattened format instead of tree
    @JsonIgnore
    List<IndicatorPojo> getFlattenedIndicatorList();
}
