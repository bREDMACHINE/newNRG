package get.a.big.head.newNRG.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import get.a.big.head.newNRG.equipment.Equipment;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
public class EventDto {

    private Long eventId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createEvent;
    private String nameEvent;
    private String descriptionEvent;
    private Equipment equipment;
    private String documentEvent;
}
