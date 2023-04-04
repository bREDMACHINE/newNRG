package get.a.big.head.newNRG.events;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@ToString
public class AddEventDto {

    private Long eventId;
    private String dateEvent;
    private String nameEvent;
    private String descriptionEvent;
    private Long equipmentId;
    private MultipartFile documentEvent;
}
