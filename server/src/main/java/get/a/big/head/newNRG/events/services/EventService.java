package get.a.big.head.newNRG.events.services;

import get.a.big.head.newNRG.events.EventDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {

    EventDto addEvent(EventDto eventDto);
    ResponseEntity<?> deleteEvent(Long id);
    List<EventDto> findAllEvents(Long id, int from, int size);
}
