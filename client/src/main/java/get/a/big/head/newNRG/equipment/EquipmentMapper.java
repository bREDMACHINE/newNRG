package get.a.big.head.newNRG.equipment;

import com.google.gson.Gson;
import get.a.big.head.newNRG.type.Type;

public class EquipmentMapper {
    public static Equipment toEquipment(Object object) {
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), Equipment.class);
    }

    public static Equipment toEquipment(String operationalName, String installationYear, Type type) {
        return Equipment.builder()
                .operationalName(operationalName)
                .installationYear(installationYear)
                .type(type)
                .build();
    }
}
