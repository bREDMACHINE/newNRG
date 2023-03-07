package get.a.big.head.newNRG.equipment;

public interface EquipmentService {

    EquipmentShortDto addEquipment(EquipmentShortDto equipmentShortDto);
    EquipmentDto getEquipment(String text);
    EquipmentDto updateEquipment(EquipmentDto equipmentDto);
    void deleteEquipment(Long id);
}
