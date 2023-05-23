package get.newNRG.general;

import java.awt.*;

public class FrameStarterController {

    protected void startFrame(Frame frame, AddCardFrameController controller) {
        if (frame == null) {
            controller.initAddCardFrameController();
        } else {
            frame.toFront();
            frame.requestFocus();
        }
    }

    protected void startFrame(Frame frame, CardFrameController controller, Long id) {
        if (frame == null) {
            controller.initCardFrameController(id);
        } else {
            frame.toFront();
            frame.requestFocus();
        }
    }
}
