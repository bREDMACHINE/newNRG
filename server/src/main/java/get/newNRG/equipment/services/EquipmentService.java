package get.newNRG.equipment.services;

import get.newNRG.equipment.dtos.EquipmentDto;
import get.newNRG.equipment.dtos.EquipmentShortDto;

public interface EquipmentService {

    EquipmentShortDto addEquipment(EquipmentShortDto equipmentShortDto);
    EquipmentDto getEquipmentByName(String text);
    EquipmentDto getEquipmentById(Long id);
    EquipmentDto updateEquipment(EquipmentDto equipmentDto);
    void deleteEquipment(Long id);
}
