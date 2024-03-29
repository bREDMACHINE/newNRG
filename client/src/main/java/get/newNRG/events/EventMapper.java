package get.newNRG.events;

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
        EventDto eventDto = new EventDto();
        eventDto.setDateEvent(dateEvent);
        eventDto.setNameEvent(nameEvent);
        eventDto.setDescriptionEvent(descriptionEvent);
        eventDto.setEquipmentId(equipmentId);
        eventDto.setFileId(fileId);
        return eventDto;
    }

    public static List<EventDto> toEventDtos(Object object) {
        return new Gson().fromJson(object.toString(), new TypeToken<List<EventDto>>(){}.getType());
    }
}
