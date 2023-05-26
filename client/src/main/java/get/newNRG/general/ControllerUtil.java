package get.newNRG.general;

import java.awt.*;

public class ControllerUtil {

    public static void start(AddCardFrameController controller) {
        Frame frame = controller.getFrame();
        if (frame == null) {
            controller.initAddCardFrameController();
        } else {
            focus(frame);
        }
    }

    public static void start(AddCardFromCardFrameController controller, Long id) {
        Frame frame = controller.getFrame();
        if (frame == null) {
            controller.initAddCardFromCardFrameController(id);
        } else {
            focus(frame);
        }
    }

    public static void start(ManagerFrameController controller) {
        Frame frame = controller.getFrame();
        if (frame == null) {
            controller.initManagerFrameController();
        } else {
            focus(frame);
        }
    }

    public static void start(CardFrameController controller, Long id) {
        Frame frame = controller.getFrame();
        if (frame == null) {
            controller.initCardFrameController(id);
        } else {
            focus(frame);
        }
    }

    public static void start(ListFrameController controller, int maxSize, Long id) {
        Frame frame = controller.getFrame();
        if (frame == null) {
            controller.initListFrameController(maxSize, id);
        } else {
            focus(frame);
        }
    }

    private static void focus(Frame frame) {
        frame.toFront();
        frame.requestFocus();
    }

    public static void stop(FrameController controller) {
    }
}
