package get.a.big.head.newNRG.events;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventDto {

    LocalDateTime createEvent;
    String name;
    String description;
    File file;
}
