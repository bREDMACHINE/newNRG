package get.a.big.head.newNRG.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class AddEventDto {

    private Long eventId;
    private String dateEvent;
    private String nameEvent;
    private String descriptionEvent;
    private Long equipmentId;
    private DataFileForEventDto documentEvent;

    @AllArgsConstructor
    @Getter
    public static class DataFileForEventDto {

        private Long fileId;
        private String name;
        private String type;
        private Long size;
        private byte[] content;
    }
}
