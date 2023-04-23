package get.newNRG.events;

import get.newNRG.general.WithFile;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=true)
public class EventDto extends WithFile {

    private String dateEvent;
    private String nameEvent;
    private String descriptionEvent;
    private Long equipmentId;
}
