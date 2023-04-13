package get.a.big.head.newNRG.events;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EventDto {

    private Long eventId;
    private String dateEvent;
    private String nameEvent;
    private String descriptionEvent;
    private Long equipmentId;
    private Long fileId;
}
