package get.newNRG.general;

import java.awt.*;

public abstract class CardFrameController<T> {

    Frame getFrame() {
        return null;
    }
    public abstract void initCardFrameController(T object);
}
