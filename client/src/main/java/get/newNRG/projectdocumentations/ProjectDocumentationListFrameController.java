package get.newNRG.projectdocumentations;

import get.newNRG.general.ListFrameController;
import get.newNRG.general.ListFrameControllerWithFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProjectDocumentationListFrameController implements ListFrameController {

    private final ListFrameControllerWithFile controller;
    private final ProjectDocumentationClient client;


    @Override
    public void initListFrameController(int maxSize, Long parentObjectId) {
        List<String> labels = List.of("Наименование проекта", "Шифр проекта", "Файлы", "Удалить проект");
        controller.initListFrameControllerWithFile(client, maxSize, labels, parentObjectId);
    }

    @Override
    public Frame getFrame() {
        return controller.getFrame();
    }
}

