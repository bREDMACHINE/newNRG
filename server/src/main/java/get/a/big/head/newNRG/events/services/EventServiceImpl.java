package get.a.big.head.newNRG.events.services;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.EquipmentRepository;
import get.a.big.head.newNRG.events.Event;
import get.a.big.head.newNRG.events.EventDto;
import get.a.big.head.newNRG.events.EventMapper;
import get.a.big.head.newNRG.events.EventRepository;
import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public EventDto addEvent(EventDto eventDto) {
        Equipment equipment = equipmentRepository.findById(eventDto.getEquipmentId())
                .orElseThrow(() -> new NotFoundException("Указанный equipmentId не существует"));
        return EventMapper.toEventDto(eventRepository.save(
                EventMapper.toEvent(eventDto, equipment)
        ));
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный eventId не существует"));
        eventRepository.delete(event);
    }

    @Override
    public List<EventDto> findAllEvents(Long id) {
        return eventRepository.findAllByEquipmentEquipmentId(id).stream().map(EventMapper::toEventDto).collect(Collectors.toList());
    }
}
