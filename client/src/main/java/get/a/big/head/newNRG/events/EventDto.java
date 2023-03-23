package get.a.big.head.newNRG.events;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class EventDto {

    private Long eventId;
    private String timeEvent;
    private String nameEvent;
    private String descriptionEvent;
    private Long equipmentId;
    private String documentEvent;
}
