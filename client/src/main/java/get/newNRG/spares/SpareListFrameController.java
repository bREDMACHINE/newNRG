package get.newNRG.spares;

import get.newNRG.general.ListFrameController;
import get.newNRG.general.ListFrameControllerWithOpenCard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SpareListFrameController implements ListFrameController {

    private final ListFrameControllerWithOpenCard controller;
    private final AddSpareFrameController addSpareFrameController;
    private final SpareFrameController spareFrameController;
    private final SpareClient client;

    @Override
    public void initListFrameController(int maxSize, Long parentObjectId) {
        List<String> labels = List.of("Краткое наименование", "Полное описание", "Номер материала", "Открыть карточку");
        controller.initListFrameController(client, maxSize, labels, parentObjectId, addSpareFrameController, spareFrameController);
    }

    public Frame getFrame() {
        return controller.getFrame();
    }
}
