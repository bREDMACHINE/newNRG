package get.a.big.head.newNRG.events;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Event {

    String createEvent;
    String nameEvent;
    String descriptionEvent;
    String documentEvent;
}
