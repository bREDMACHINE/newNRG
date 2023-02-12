package get.a.big.head.newNRG.equipment;

public class EquipmentMapper {
    public static EquipmentDto toEquipmentDto(Equipment equipment) {
        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setOperationalName(equipment.getOperationalName());
        equipmentDto.setRatedCurrent(equipment.getRatedCurrent());
        equipmentDto.setRatedVoltage(equipment.getRatedVoltage());
        return equipmentDto;
    }
}
