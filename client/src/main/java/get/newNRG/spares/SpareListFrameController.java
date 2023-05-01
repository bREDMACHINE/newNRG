package get.newNRG.spares;

import get.newNRG.general.ListFrameControllerWithOpenCard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SpareListFrameController {

    private final ListFrameControllerWithOpenCard controller;
    private final SpareClient client;

    public void initSpareListFrameController(int maxSize, Long parentObjectId) {
        List<String> labels = List.of("Краткое наименование", "Полное описание", "Номер материала", "Открыть карточку");
        controller.initListFrameController(client, maxSize, labels, parentObjectId);
    }

    public Frame getFrame() {
        return controller.getFrame();
    }
}
