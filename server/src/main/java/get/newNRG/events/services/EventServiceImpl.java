package get.newNRG.events.services;

import get.newNRG.equipment.Equipment;
import get.newNRG.equipment.EquipmentRepository;
import get.newNRG.events.Event;
import get.newNRG.events.EventMapper;
import get.newNRG.events.EventRepository;
import get.newNRG.exception.NotFoundException;
import get.newNRG.files.DataFile;
import get.newNRG.files.DataFileRepository;
import get.newNRG.events.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EquipmentRepository equipmentRepository;
    private final DataFileRepository dataFileRepository;

    @Override
    public EventDto addEvent(EventDto eventDto) {
        Equipment equipment = equipmentRepository.findById(eventDto.getEquipmentId())
                .orElseThrow(() -> new NotFoundException("Указанный equipmentId не существует"));
        DataFile dataFile = dataFileRepository.findById(eventDto.getFileId())
                .orElseThrow(() -> new NotFoundException("Указанный fileId не существует"));
        return EventMapper.toEventDto(eventRepository.save(EventMapper.toEvent(eventDto, equipment, dataFile)));
    }

    @Override
    public ResponseEntity<?> deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный eventId не существует"));
        String name = event.getNameEvent();
        eventRepository.delete(event);
        Map<String, String> response = new HashMap<>();
        response.put("name", name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public List<EventDto> findAllEvents(Long equipmentId, int from, int size) {
        return eventRepository.findAllByEquipmentEquipmentId(equipmentId, PageRequest.of(from / size, size)).stream()
                .map(EventMapper::toEventDto)
                .collect(Collectors.toList());
    }
}
