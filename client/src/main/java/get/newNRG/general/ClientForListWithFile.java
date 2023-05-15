package get.newNRG.general;

import java.awt.*;
import java.util.List;

public interface ClientForListWithFile {

    List<? extends WithFile> findAll(Frame frame, Long parentObjectId, int from, int size, String userId);
    void delete(Frame frame, Long id, String userId);
}
