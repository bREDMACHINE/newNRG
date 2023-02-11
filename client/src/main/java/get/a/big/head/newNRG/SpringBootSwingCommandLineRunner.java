package get.a.big.head.newNRG;

import get.a.big.head.newNRG.general.MainFrameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class SpringBootSwingCommandLineRunner implements CommandLineRunner {
    private final MainFrameController controller;

    @Autowired
    public SpringBootSwingCommandLineRunner(MainFrameController controller) {
        this.controller = controller;
    }


    @Override
    public void run(String... args) {
        EventQueue.invokeLater(controller::initControllerFrame);
    }
}
