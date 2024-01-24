package at.gentrader.strategy.serialization.model.rule;

import lombok.*;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
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
