package get.newNRG.events.controllers;

import get.newNRG.events.EventClient;
import get.newNRG.general.ListFrameController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventListFrameController {

    private final ListFrameController controller;
    private final EventClient client;

    public void initEventListFrameController(int maxSize, Long parentObjectId) {
        List<String> labels = List.of("Дата события", "Событие", "Описание", "Документы", "Удалить событие");
        controller.initListFrameController(client, maxSize, labels, parentObjectId);
    }

    public Frame getFrame() {
        return controller.getFrame();
    }
}

