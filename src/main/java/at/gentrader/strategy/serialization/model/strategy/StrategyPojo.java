package at.gentrader.strategy.serialization.model.strategy;

import at.gentrader.strategy.serialization.model.config.TradeModes;
import at.gentrader.strategy.serialization.model.rule.RulePojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
public class StrategyPojo implements Serializable {
    private long baseId;
    private UUID uid;

    private long candleSizeMinutes;
    private boolean candleSizeFixed = false;
    private String indicatorSymbole;
    private boolean indicatorSymboleFixed = false;

    private String tradeSymbole;
    private boolean tradeSymboleFixed = false;
    private String tradeMode = TradeModes.FIXED_AMOUNT;
    private boolean tradeModeFixed = false;
    private double tradePercentage = 0.00;
    private boolean tradePercentageFixed = false;

    private RulePojo entryRule;
    private RulePojo exitRule;
}
