package get.newNRG.equipment.services;

import get.newNRG.equipment.Equipment;
import get.newNRG.equipment.dtos.EquipmentMapper;
import get.newNRG.equipment.EquipmentRepository;
import get.newNRG.equipment.dtos.EquipmentDto;
import get.newNRG.equipment.dtos.EquipmentShortDto;
import get.newNRG.events.Event;
import get.newNRG.events.EventRepository;
import get.newNRG.exception.BadRequestException;
import get.newNRG.exception.NotFoundException;
import get.newNRG.projectdocumentation.ProjectDocumentation;
import get.newNRG.projectdocumentation.ProjectDocumentationRepository;
import get.newNRG.types.Type;
import get.newNRG.types.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final TypeRepository typeRepository;
    private final ProjectDocumentationRepository projectDocumentationRepository;
    private final EventRepository eventRepository;

    @Override
    public EquipmentShortDto addEquipment(EquipmentShortDto equipmentShortDto) {
        if (equipmentRepository.findByOperationalName(equipmentShortDto.getOperationalName()).isEmpty()) {
            Type type = typeRepository.findById(equipmentShortDto.getTypeId())
                    .orElseThrow(() -> new NotFoundException("Указанный typeId не существует"));
            return EquipmentMapper.toEquipmentShortDto(equipmentRepository.save(
                    EquipmentMapper.toEquipment(equipmentShortDto, type)
            ));
        }
        throw  new BadRequestException("Указанное оперативное наименование уже используется");
    }

    @Override
    public EquipmentDto updateEquipment(EquipmentDto equipmentDto) {
        Equipment equipment = equipmentRepository.findById(equipmentDto.getEquipmentId())
                .orElseThrow(() -> new NotFoundException("Указанный equipmentId не существует"));
        if (equipmentDto.getProjectDocuments() != null) {
            List<ProjectDocumentation> projectDocuments = projectDocumentationRepository.findAllById(
                    equipmentDto.getProjectDocuments()
            );
            equipment.setProjectDocuments(projectDocuments);
        }
        if (equipmentDto.getEvents() != null) {
            List<Event> events = eventRepository.findAllById(equipmentDto.getProjectDocuments());
            equipment.setEvents(events);
        }
        return EquipmentMapper.toEquipmentDto(equipmentRepository.save(equipment));
    }

    @Override
    public void deleteEquipment(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный equipmentId не существует"));
        equipmentRepository.delete(equipment);
    }

    @Override
    public EquipmentDto getEquipmentByName(String text) {
        return EquipmentMapper.toEquipmentDto(equipmentRepository.findByOperationalName(text)
                .orElseThrow(() -> new NotFoundException("Указанное оперативное наименование не существует")));
    }

    @Override
    public EquipmentDto getEquipmentById(Long id) {
        return EquipmentMapper.toEquipmentDto(equipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный equipmentId не существует")));
    }
}
