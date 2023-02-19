package get.a.big.head.newNRG.events;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
public class EventDto {

    String createEvent;
    String name;
    String description;
    File file;
}
