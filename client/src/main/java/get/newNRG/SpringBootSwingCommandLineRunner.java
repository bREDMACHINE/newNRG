package get.newNRG;

import get.newNRG.general.controllers.FrameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class SpringBootSwingCommandLineRunner implements CommandLineRunner {
    private final FrameController controller;

    @Autowired
    public SpringBootSwingCommandLineRunner(FrameController controller) {
        this.controller = controller;
    }


    @Override
    public void run(String... args) {
        EventQueue.invokeLater(controller::initControllerFrame);
    }
}
