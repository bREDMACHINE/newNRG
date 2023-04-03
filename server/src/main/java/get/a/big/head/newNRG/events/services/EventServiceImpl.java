package get.a.big.head.newNRG.events.services;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.EquipmentRepository;
import get.a.big.head.newNRG.events.*;
import get.a.big.head.newNRG.exception.NotFoundException;
import get.a.big.head.newNRG.files.DataFile;
import get.a.big.head.newNRG.files.DataFileMapper;
import get.a.big.head.newNRG.files.DataFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EquipmentRepository equipmentRepository;
    private final DataFileRepository dataFileRepository;

    @Override
    public ResponseEntity<?> addEvent(AddEventDto addEventDto) {
        Equipment equipment = equipmentRepository.findById(addEventDto.getEquipmentId())
                .orElseThrow(() -> new NotFoundException("Указанный equipmentId не существует"));
        DataFile dataFile = dataFileRepository.save(DataFileMapper.toDataFile(addEventDto));
        Event event = eventRepository.save(EventMapper.toEvent(addEventDto, equipment, dataFile));
        HttpHeaders headers = new HttpHeaders();
        headers.add("EventName", event.getNameEvent());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный eventId не существует"));
        eventRepository.delete(event);
    }

    @Override
    public List<EventDto> findAllEvents(Long equipmentId, int from, int size) {
        return eventRepository.findAllByEquipmentEquipmentId(equipmentId, PageRequest.of(from / size, size)).stream()
                .map(EventMapper::toEventDto)
                .collect(Collectors.toList());
    }
}
