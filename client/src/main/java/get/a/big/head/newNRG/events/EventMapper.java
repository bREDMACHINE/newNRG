package get.a.big.head.newNRG.events;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class EventMapper {

    public static EventDto toEventDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), EventDto.class);
    }

    public static EventDto toEventDto(Long equipmentId,
                                      String dateEvent,
                                      String nameEvent,
                                      String descriptionEvent,
                                      MultipartFile documentEvent) {
        return EventDto.builder()
                .nameEvent(nameEvent)
                .dateEvent(dateEvent)
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
