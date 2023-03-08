package get.a.big.head.newNRG.events;

import get.a.big.head.newNRG.equipment.Equipment;

import java.time.LocalDateTime;

public class EventMapper {

    public static Event toEvent(EventDto eventDto, Equipment equipment) {
        Event event = new Event();
        event.setCreateEvent(LocalDateTime.now());
        event.setNameEvent(eventDto.getNameEvent());
        event.setDescriptionEvent(eventDto.getDescriptionEvent());
        event.setDocumentEvent(event.getDocumentEvent());
        event.setEquipment(equipment);
        return event;
    }

    public static EventDto toEventDto(Event event) {
        return EventDto.builder()
                .eventId(event.getEventId())
                .createEvent(event.getCreateEvent())
                .nameEvent(event.getNameEvent())
                .descriptionEvent(event.getDescriptionEvent())
                .documentEvent(event.getDocumentEvent())
                .equipmentId(event.getEquipment().getEquipmentId())
                .build();
    }
}
