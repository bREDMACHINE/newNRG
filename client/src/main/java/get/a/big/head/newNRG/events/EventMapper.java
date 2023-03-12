package get.a.big.head.newNRG.events;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class EventMapper {

    public static EventDto toEventDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), EventDto.class);
    }

    public static EventDto toEventDto(Long equipmentId,
                                      String timeEvent,
                                      String nameEvent,
                                      String descriptionEvent,
                                      String documentEvent) {
        return EventDto.builder()
                .nameEvent(nameEvent)
                .timeEvent(timeEvent)
                .descriptionEvent(descriptionEvent)
                .documentEvent(documentEvent)
                .equipmentId(equipmentId)
                .build();
    }

    public static List<EventDto> toEventDtos(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), new TypeToken<List<EventDto>>(){}.getType());
    }
}
