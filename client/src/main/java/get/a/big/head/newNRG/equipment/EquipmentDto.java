package get.a.big.head.newNRG.equipment;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class EquipmentDto {

    private Long equipmentId;
    private String operationalName;
    private String installationYear;
    private TypeDtoForEquipmentDto type;
    private List<ProjectDtoForEquipmentDto> projectDocuments;
    private List<EventDtoForEquipmentDto> events;

    @Builder
    @Getter
    public static class TypeDtoForEquipmentDto {
        Long typeId;
        String typeName;
    }

    @Builder
    @Getter
    public static class ProjectDtoForEquipmentDto {
        Long projectId;
    }

    @Builder
    @Getter
    public static class EventDtoForEquipmentDto {
        Long eventId;
    }
}
