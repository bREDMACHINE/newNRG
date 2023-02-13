package get.a.big.head.newNRG.events;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventMapper {

    public static Event toEvent(Object object) {
        Map<String, String> map = (LinkedHashMap<String, String>) object;
        Event event = new Event();
        event.setCreateEvent(map.get("createEvent"));
        event.setName(map.get("name"));
        event.setDescription(map.get("description"));
        event.setFile(new File(map.get("file")));
        return event;
    }
}
