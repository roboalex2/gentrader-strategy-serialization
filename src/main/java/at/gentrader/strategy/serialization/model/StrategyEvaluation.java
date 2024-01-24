package at.gentrader.strategy.serialization.model;

import at.gentrader.strategy.serialization.model.strategy.StrategyPojo;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEvaluation implements Serializable {
    private StrategyPojo strategy;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private long score;
}
