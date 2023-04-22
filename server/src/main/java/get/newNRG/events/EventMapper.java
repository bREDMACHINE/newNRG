package get.newNRG.events;

import get.newNRG.equipment.Equipment;
import get.newNRG.files.DataFile;

public class EventMapper {

    public static Event toEvent(EventDto eventDto, Equipment equipment, DataFile dataFile) {
        Event event = new Event();
        event.setDateEvent(eventDto.getDateEvent());
        event.setNameEvent(eventDto.getNameEvent());
        event.setDescriptionEvent(eventDto.getDescriptionEvent());
        event.setDocumentEvent(dataFile);
        event.setEquipment(equipment);
        return event;
    }

    public static EventDto toEventDto(Event event) {
        return EventDto.builder()
                .eventId(event.getEventId())
                .dateEvent(event.getDateEvent())
                .nameEvent(event.getNameEvent())
                .descriptionEvent(event.getDescriptionEvent())
                .fileId(event.getDocumentEvent() != null ? event.getDocumentEvent().getFileId() : null)
                .equipmentId(event.getEquipment().getEquipmentId())
                .build();
    }
}
