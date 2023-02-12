package get.a.big.head.newNRG.equipment;

import java.util.LinkedHashMap;
import java.util.Map;

public class EquipmentMapper {
    public static Equipment toEquipment(Object object) {
        Map<String, String> map = (LinkedHashMap<String, String>) object;
        Equipment equipment = new Equipment();
        equipment.setOperationalName(map.get("operationalName"));
        equipment.setRatedCurrent(map.get("ratedCurrent"));
        equipment.setRatedVoltage(map.get("ratedVoltage"));
        return equipment;
    }

    public static EquipmentDto toEquipmentDto(String operationalName, String ratedCurrent, String ratedVoltage) {
        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setOperationalName(operationalName);
        equipmentDto.setRatedCurrent(ratedCurrent);
        equipmentDto.setRatedVoltage(ratedVoltage);
        return equipmentDto;
    }
}
