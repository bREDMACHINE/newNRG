package get.newNRG.general;

import java.awt.*;
import java.util.List;

public interface ClientWithOpenCard {

    List<? extends WithOpenCard> findAll(Frame frame, Long parentObjectId, int from, int size, String userId);
    Object get(Frame frame, Long id, String userId);
}
