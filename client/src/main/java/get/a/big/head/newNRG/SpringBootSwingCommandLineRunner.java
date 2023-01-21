package get.a.big.head.newNRG;

import get.a.big.head.newNRG.general.MainControllerFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class SpringBootSwingCommandLineRunner implements CommandLineRunner {
    private final MainControllerFrame controller;

    @Autowired
    public SpringBootSwingCommandLineRunner(MainControllerFrame controller) {
        this.controller = controller;
    }


    @Override
    public void run(String... args) {
        EventQueue.invokeLater(controller::initControllerFrame);
    }
}
