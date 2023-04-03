package get.a.big.head.newNRG.events.services;

import get.a.big.head.newNRG.events.AddEventDto;
import get.a.big.head.newNRG.events.EventDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {

    ResponseEntity<?> addEvent(AddEventDto addEventDto);
    void deleteEvent(Long id);
    List<EventDto> findAllEvents(Long id, int from, int size);
}
