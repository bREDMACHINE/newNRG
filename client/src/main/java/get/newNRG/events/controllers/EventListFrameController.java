package get.newNRG.events.controllers;

import get.newNRG.events.EventClient;
import get.newNRG.general.ListFrameController;
import get.newNRG.general.ListFrameControllerWithFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventListFrameController implements ListFrameController {

    private final ListFrameControllerWithFile controller;
    private final EventClient client;
    private final AddEventFrameController addEventFrameController;

    @Override
    public void initListFrameController(int maxSize, Long parentObjectId) {
        List<String> labels = List.of("Дата события", "Событие", "Описание", "Документы", "Удалить событие");
        controller.initListFrameControllerWithFile(client, maxSize, labels, parentObjectId, addEventFrameController);
    }

    @Override
    public Frame getFrame() {
        return controller.getFrame();
    }
}

