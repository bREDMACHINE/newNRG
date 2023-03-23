package get.a.big.head.newNRG.equipment;

import com.google.gson.Gson;

public class EquipmentMapper {

    public static EquipmentShortDto toEquipmentShortDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), EquipmentShortDto.class);
    }

    public static EquipmentDto toEquipmentDto(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), EquipmentDto.class);
    }

    public static EquipmentShortDto toEquipmentShortDto(String operationalName, Short installationYear, Long typeId) {
        return EquipmentShortDto.builder()
                .operationalName(operationalName)
                .installationYear(installationYear)
                .typeId(typeId)
                .build();
    }
}
