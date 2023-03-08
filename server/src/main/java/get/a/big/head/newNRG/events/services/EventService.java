package get.a.big.head.newNRG.events.services;

import get.a.big.head.newNRG.events.EventDto;

import java.util.List;

public interface EventService {

    EventDto addEvent(EventDto eventDto);
    void deleteEvent(Long id);
    List<EventDto> findAllEvents(Long id);
}
