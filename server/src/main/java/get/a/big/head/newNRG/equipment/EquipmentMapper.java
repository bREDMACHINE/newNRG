package get.a.big.head.newNRG.equipment;

import get.a.big.head.newNRG.events.Event;
import get.a.big.head.newNRG.projectdocumentation.ProjectDocumentation;
import get.a.big.head.newNRG.types.Type;

import java.util.stream.Collectors;

public class EquipmentMapper {

    public static EquipmentDto toEquipmentDto(Equipment equipment) {
        return EquipmentDto.builder()
                .equipmentId(equipment.getEquipmentId())
                .operationalName(equipment.getOperationalName())
                .installationYear(equipment.getInstallationYear())
                .type(new EquipmentDto.TypeDtoForEquipmentDto(equipment.getType().getTypeId(), equipment.getType().getTypeName()))
                .projectDocuments(equipment.getProjectDocuments().stream().map(ProjectDocumentation::getProjectId).collect(Collectors.toList()))
                .events(equipment.getEvents().stream().map(Event::getEventId).collect(Collectors.toList()))
                .build();
    }

    public static Equipment toEquipment(EquipmentShortDto equipmentShortDto, Type type) {
        Equipment equipment = new Equipment();
        equipment.setOperationalName(equipmentShortDto.getOperationalName());
        equipment.setInstallationYear(equipmentShortDto.getInstallationYear());
        equipment.setType(type);
        return equipment;
    }

    public static EquipmentShortDto toEquipmentShortDto(Equipment equipment) {
        return EquipmentShortDto.builder()
                .equipmentId(equipment.getEquipmentId())
                .operationalName(equipment.getOperationalName())
                .installationYear(equipment.getInstallationYear())
                .typeId(equipment.getEquipmentId())
                .build();
    }
}
