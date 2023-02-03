package get.a.big.head.newNRG.general;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Lazy
@Controller
@Slf4j
public class MessageFromServerFrameController {
    private MessageFromServerFrame frame;

    @Autowired
    public MessageFromServerFrameController() {
    }

    public void setMessage(String message) {
        frame = new MessageFromServerFrame(message);
        frame.getButton().addActionListener(e -> frame.getFrame().dispose());
    }
}
