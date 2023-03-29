package get.a.big.head.newNRG.events;

import get.a.big.head.newNRG.events.services.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class EventController {

    private final EventService eventService;

    @PostMapping("/moderator/equipment/event")
    public EventDto addEvent(@RequestBody EventDto eventDto) {
        log.info("Получен Post запрос к эндпоинту /moderator/equipment/event, event={}", eventDto);
        EventDto event = eventService.addEvent(eventDto);
        log.info("Результат запроса {}", event);
        return event;
    }

    @DeleteMapping("/moderator/equipment/event/{id}")
    public void deleteEvent(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/event/{}", id);
        eventService.deleteEvent(id);
    }

    @GetMapping("/user/equipment/{id}/events")
    public List<EventDto> findAllEvents(@PathVariable Long id,
                                        @RequestParam int from,
                                        @RequestParam int size) {
        log.info("Получен Get запрос к эндпоинту /equipment/{}/events с листа {}", id, from);
        List<EventDto> list = eventService.findAllEvents(id, from, size);
        log.info("Результат запроса {}", list);
        return list;
    }
}
