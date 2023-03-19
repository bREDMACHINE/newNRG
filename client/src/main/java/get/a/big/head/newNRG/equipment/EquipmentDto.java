package get.a.big.head.newNRG.equipment;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class EquipmentDto {

    private Long equipmentId;
    private String operationalName;
    private Short installationYear;
    private TypeDtoForEquipmentDto type;
    private List<Long> projectDocuments;
    private List<Long> events;

    @Builder
    @Getter
    public static class TypeDtoForEquipmentDto {
        private Long typeId;
        private String typeName;
    }
}
