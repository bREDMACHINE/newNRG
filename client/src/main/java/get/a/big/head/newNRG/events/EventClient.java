package get.a.big.head.newNRG.events;

import get.a.big.head.newNRG.httpclients.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
@Slf4j
public class EventClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public EventClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public EventDto addEvent(Frame frame, EventDto event, String userId) {
        log.info("Add event {}", event);
        ResponseEntity<Object> addEventResponse = post("/moderator/equipment/event",  userId, event);
        EventDto eventResponse = response(addEventResponse, frame);
        if (eventResponse != null) {
            frame.dispose();
            JOptionPane.showMessageDialog(frame,
                    "Событие " + eventResponse.getNameEvent() + " успешно добавлено");
            return eventResponse;
        }
        return null;
    }

    public void deleteEvent(Frame frame, Long eventId, String userId) {
        log.info("Add event {}", eventId);
        ResponseEntity<Object> deleteEventResponse = delete("/moderator/equipment/event/" + eventId,  userId);
        if (deleteEventResponse.getStatusCode().is2xxSuccessful() && deleteEventResponse.getBody() != null) {
            frame.dispose();
            JOptionPane.showMessageDialog(frame,"Событие удалено");
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    deleteEventResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public List<EventDto> findAllEvents(Frame frame, Long equipmentId, int from, int size, String userId) {
        log.info("Find all events for equipment {}, from {}", equipmentId, from);
        ResponseEntity<Object> eventListResponse = get("/user/equipment/" + equipmentId + "/events?from=" + from + "&size=" + size,  userId);
        if (eventListResponse.getStatusCode().is2xxSuccessful() && eventListResponse.getBody() != null) {
            return EventMapper.toEventDtos(eventListResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    eventListResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    private <T> EventDto response(ResponseEntity<Object> response, Frame frame) {
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            log.info("Result {}",  response.getBody().toString());
            return EventMapper.toEventDto(response.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    response.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }
}
