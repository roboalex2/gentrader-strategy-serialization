package at.gentrader.strategy.serialization.model.rule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@ToString
public class LogicLinkPojo implements Serializable {

    private Operation operation;
    private RulePojo rule;

    public enum Operation implements Serializable {
        OR,
        AND,
        XOR
    }
}
