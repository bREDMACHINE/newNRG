package get.a.big.head.newNRG.events;

import com.google.gson.JsonParser;
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
import java.io.StringReader;
import java.util.List;

@Component
@Slf4j
public class EventClient extends BaseClient {

    @Autowired
    public EventClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public void addEvent(Frame frame, EventDto event, String userId) {
        log.info("Add event {}", event);
        Object object = response(post("/moderator/equipment/event",  userId, event), frame);
        if (object != null) {
            EventDto eventResponse = EventMapper.toEventDto(object);
            frame.dispose();
            JOptionPane.showMessageDialog(frame,
                    "Событие " + eventResponse.getNameEvent() + " успешно добавлено");
        }
    }

    public void deleteEvent(Frame frame, Long eventId, String userId) {
        log.info("Add event {}", eventId);
        Object object = response(delete("/moderator/equipment/event/" + eventId,  userId), frame);
        if (object != null) {
            String name = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("name").getAsString();
            frame.dispose();
            JOptionPane.showMessageDialog(frame,"Событие " + name + " удалено");
        }
    }

    public List<EventDto> findAllEvents(Frame frame, Long equipmentId, int from, int size, String userId) {
        log.info("Find all events for equipment {}, from {}", equipmentId, from);
        Object object = response(
                get("/user/equipment/" + equipmentId + "/events?from=" + from + "&size=" + size,  userId),
                frame
        );
        if (object != null) {
            return EventMapper.toEventDtos(object);
        }
        return null;
    }

    private <T> T response(ResponseEntity<T> response, Frame frame) {
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            log.info("Result {}",  response.getBody().toString());
            return response.getBody();
        } else {
            if (frame != null) {
                JOptionPane.showMessageDialog(
                        frame,
                        response.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        return null;
    }
}
