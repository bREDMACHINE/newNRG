package get.a.big.head.newNRG.events;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class EventMapper {

    public static EventDto toEventDto(Object object) {
        return new Gson().fromJson(object.toString(), EventDto.class);
    }

    public static EventDto toEventDto(Long equipmentId,
                                      String dateEvent,
                                      String nameEvent,
                                      String descriptionEvent,
                                      Long fileId) {
        return EventDto.builder()
                .nameEvent(nameEvent)
                .dateEvent(dateEvent)
                .descriptionEvent(descriptionEvent)
                .fileId(fileId)
                .equipmentId(equipmentId)
                .build();
    }

    public static List<EventDto> toEventDtos(Object object) {
        return new Gson().fromJson(object.toString(), new TypeToken<List<EventDto>>(){}.getType());
    }
}
