package get.newNRG.events.services;

import get.newNRG.events.EventDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {

    EventDto addEvent(EventDto eventDto);
    ResponseEntity<?> deleteEvent(Long id);
    List<EventDto> findAllEvents(Long id, int from, int size);
}
