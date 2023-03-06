package get.a.big.head.newNRG.events;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Event {

    private Long id;
    private String createEvent;
    private String nameEvent;
    private String descriptionEvent;
    private String documentEvent;
}
