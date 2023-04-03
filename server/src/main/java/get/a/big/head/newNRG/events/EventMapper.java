package get.a.big.head.newNRG.events;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.files.DataFile;

public class EventMapper {

    public static Event toEvent(AddEventDto addEventDto, Equipment equipment, DataFile dataFile) {
        Event event = new Event();
        event.setDateEvent(addEventDto.getDateEvent());
        event.setNameEvent(addEventDto.getNameEvent());
        event.setDescriptionEvent(addEventDto.getDescriptionEvent());
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
                .documentEvent(event.getDocumentEvent().getFileId())
                .equipmentId(event.getEquipment().getEquipmentId())
                .build();
    }
}
