package get.a.big.head.newNRG.events;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class EventDto {

    private Long eventId;
    private String dateEvent;
    private String nameEvent;
    private String descriptionEvent;
    private Long equipmentId;
    private Long fileId;
}
