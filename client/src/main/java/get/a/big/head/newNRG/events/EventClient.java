package get.a.big.head.newNRG.events;

import get.a.big.head.newNRG.httpclients.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
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

    public ResponseEntity<Object> addEvent(Event event, String userId) {
        return post("/moderator/equipment/event",  userId, event);
    }

    public ResponseEntity<Object> deleteEvent(Long eventId, String userId) {
        return delete("/moderator/equipment/event/" + eventId,  userId);
    }

    public ResponseEntity<Object> findAllEvents(Long typeId, String userId) {
        return get("/user/equipment/" + typeId + "/events",  userId);
    }
}
