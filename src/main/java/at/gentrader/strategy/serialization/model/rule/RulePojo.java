package at.gentrader.strategy.serialization.model.rule;

import at.gentrader.strategy.serialization.model.indicator.IndicatorPojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UnderIndicatorRulePojo.class, name = "UnderIndicatorRule"),
        @JsonSubTypes.Type(value = OverIndicatorRulePojo.class, name = "OverIndicatorRule"),
        @JsonSubTypes.Type(value = StopLossRulePojo.class, name = "StopLossRule"),
        @JsonSubTypes.Type(value = StopGainRulePojo.class, name = "StopGainRule")
})
public abstract class RulePojo implements Serializable {

    @Getter
    @Setter
    protected List<LogicLinkPojo> logicLinks = new ArrayList<>();

    public RulePojo or(RulePojo rulePojo) {
        logicLinks.add(new LogicLinkPojo(LogicLinkPojo.Operation.OR, rulePojo));
        return this;
    }

    public RulePojo and(RulePojo rulePojo) {
        logicLinks.add(new LogicLinkPojo(LogicLinkPojo.Operation.AND, rulePojo));
        return this;
    }

    public RulePojo xor(RulePojo rulePojo) {
        logicLinks.add(new LogicLinkPojo(LogicLinkPojo.Operation.XOR, rulePojo));
        return this;
    }

    protected Rule linkLogicRules(Rule ta4jRule, BarSeries series) {
        for (LogicLinkPojo link : logicLinks) {
            switch (link.getOperation()) {
                case OR:
                    ta4jRule = ta4jRule.or(link.getRule().toTa4jRule(series));
                    break;
                case AND:
                    ta4jRule = ta4jRule.and(link.getRule().toTa4jRule(series));
                    break;
                case XOR:
                    ta4jRule = ta4jRule.xor(link.getRule().toTa4jRule(series));
                    break;
            }
        }
        return ta4jRule;
    }

    public abstract Rule toTa4jRule(BarSeries series);

    @JsonIgnore
    public List<IndicatorPojo> getFlattenedIndicatorList() {
        List<IndicatorPojo> indicators = new ArrayList<>(getIndicators());
        for (LogicLinkPojo link : logicLinks) {
            indicators.addAll(link.getRule().getFlattenedIndicatorList());
        }
        return indicators;
    }

    @JsonIgnore
    abstract List<IndicatorPojo> getIndicators();

    @JsonIgnore
    public List<RulePojo> getFlattenedRuleList() {
        List<RulePojo> rules = new ArrayList<>(List.of(this));
        for (LogicLinkPojo link : logicLinks) {
            rules.addAll(link.getRule().getFlattenedRuleList());
        }
        return rules;
    }
}
