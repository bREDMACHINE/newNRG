package get.a.big.head.newNRG.equipment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class EquipmentMapper {

    public static EquipmentShortDto toEquipmentShortDto(Object object) {
        return new Gson().fromJson(object.toString(), EquipmentShortDto.class);
    }

    public static EquipmentDto toEquipmentDto(Object object) {
        return new Gson().fromJson(object.toString(), EquipmentDto.class);
    }

    public static EquipmentShortDto toEquipmentShortDto(String operationalName, Short installationYear, Long typeId) {
        return EquipmentShortDto.builder()
                .operationalName(operationalName)
                .installationYear(installationYear)
                .typeId(typeId)
                .build();
    }

    public static List<EquipmentShortDto> toEquipmentShortDtos(Object object) {
        return new Gson().fromJson(object.toString(), new TypeToken<List<EquipmentShortDto>>(){}.getType());
    }
}
