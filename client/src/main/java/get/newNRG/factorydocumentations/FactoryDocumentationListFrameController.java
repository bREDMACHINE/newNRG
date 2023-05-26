package get.newNRG.factorydocumentations;

import get.newNRG.general.ListFrameControllerWithFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FactoryDocumentationListFrameController {

    private final ListFrameControllerWithFile controller;
    private final FactoryDocumentationClient client;

    public void initFactoryDocumentationListFrameController(int maxSize, Long parentObjectId) {
        List<String> labels = List.of("Наименование документа", "Шифр документа", "Файлы", "Удалить документ");
        controller.initListFrameControllerWithFile(client, maxSize, labels, parentObjectId);
    }

    public Frame getFrame() {
        return controller.getFrame();
    }
}