package get.newNRG.equipment.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EquipmentShortDto {

    private Long equipmentId;
    private String operationalName;
    private Short installationYear;
    private Long typeId;
}
