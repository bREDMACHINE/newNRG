package get.a.big.head.newNRG.equipment.services;

import get.a.big.head.newNRG.equipment.dtos.EquipmentDto;
import get.a.big.head.newNRG.equipment.dtos.EquipmentShortDto;

public interface EquipmentService {

    EquipmentShortDto addEquipment(EquipmentShortDto equipmentShortDto);
    EquipmentDto getEquipment(String text);
    EquipmentDto updateEquipment(EquipmentDto equipmentDto);
    void deleteEquipment(Long id);
}
