package get.newNRG.general;

import java.awt.*;
import java.util.List;

public interface ClientWithOpenCard {

    List<? extends WithOpenCard> findAll(Frame frame, Long parentObjectId, int from, int size, String userId);
    Object getById(Frame frame, Long id, String userId);
    Object getByName(Frame frame, String name, String userId);
}
