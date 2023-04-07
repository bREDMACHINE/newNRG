package get.a.big.head.newNRG.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Getter
@ToString
public class EventDto {

    private Long eventId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEvent;
    private String nameEvent;
    private String descriptionEvent;
    private Long equipmentId;
    private Long fileId;
}
