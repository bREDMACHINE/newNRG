package get.newNRG.general;

import java.awt.*;

public class FrameStarterController {

    protected void starter(Frame frame, ControllerInitiator controllerInitiator) {
        if (frame == null) {
            controllerInitiator.initiate();
        } else {
            frame.toFront();
            frame.requestFocus();
        }
    }
}
