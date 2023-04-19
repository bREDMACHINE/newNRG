package get.a.big.head.newNRG.events.controllers;

import get.a.big.head.newNRG.events.EventClient;
import get.a.big.head.newNRG.general.ListFrameController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
@RequiredArgsConstructor
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



//package get.a.big.head.newNRG.events.controllers;
//
//import get.a.big.head.newNRG.equipment.EquipmentDto;
//import get.a.big.head.newNRG.events.EventClient;
//import get.a.big.head.newNRG.events.EventDto;
//import get.a.big.head.newNRG.files.DataFileDto;
//import get.a.big.head.newNRG.files.DataFileClient;
//import get.a.big.head.newNRG.general.ListFrame;
//import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.List;
//
//@Component
//@Getter
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//public class EventListFrameController {
//
//    private ListFrame frame;
//    private final EventClient eventClient;
//    private final UserAuthorizationFrameController authorizationFrameController;
//    private final DataFileClient dataFileClient;
//    private int size;
//    private int maxSize;
//    private int from;
//    private int pages;
//    private int maxShow;
//    private Long equipmentId;
//    private int page;
//    private List<EventDto> list;
//
//    public void initEventListFrameController(EquipmentDto equipment) {
//        equipmentId = equipment.getEquipmentId();
//        maxSize = equipment.getEvents().size();
//        size = 15;
//        pages = maxSize / size + 1;
//        maxShow = pages * size - 15;
//        from = 0;
//        page = 1;
//        openPage();
//    }
//
//    private void openPage() {
//        list = eventClient.findAllEvents(
//                frame,
//                equipmentId,
//                from,
//                size,
//                authorizationFrameController.getUser().getUserId()
//        );
//        List<String> labels = List.of("Дата события", "Событие", "Описание", "Документы", "Удалить событие");
//        if (list != null) {
//            if (maxSize <= size) {
//                frame = new ListFrame(list, labels, page, pages);
//                functionsFrame();
//            } else if (from < size) {
//                frame = new ListFrame(list, labels, page, pages);
//                frame.getPanelButtons().add(frame.getButtonNext());
//                functionsFrame();
//            } else if (from > 14 && from < maxShow) {
//                frame = new ListFrame(list, labels, page, pages);
//                frame.getPanelButtons().add(frame.getButtonPrevious());
//                frame.getPanelButtons().add(frame.getButtonNext());
//                functionsFrame();
//            } else if (from > 14 && from == maxShow) {
//                frame = new ListFrame(list, labels, page, pages);
//                frame.getPanelButtons().add(frame.getButtonPrevious());
//                functionsFrame();
//            }
//        }
//    }
//
//    private void functionsFrame() {
//        frame.getButtonNext().addActionListener(e -> {
//            if (frame.getFrame() != null) {
//                frame.getFrame().dispose();
//            }
//            from = from + 15;
//            page = page + 1;
//            openPage();
//        });
//
//        frame.getButtonPrevious().addActionListener(e -> {
//            if (frame.getFrame() != null) {
//                frame.getFrame().dispose();
//            }
//            from = from - 15;
//            page = page - 1;
//            openPage();
//        });
//
//        frame.getFrame().addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                frame = null;
//            }
//        });
//
//        for (JButton button : frame.getOpenFileButtons()) {
//            button.addActionListener(e -> {
//                EventDto eventDto = list.get(Integer.parseInt(button.getActionCommand()));
//                DataFileDto dataFile = dataFileClient.getFile(
//                        frame.getFrame(),
//                        eventDto.getFileId(),
//                        authorizationFrameController.getUser().getUserId()
//                );
//                if (Desktop.isDesktopSupported() && dataFile != null) {
//                    try {
//
//                        File file = new File(dataFile.getName());
//                        try (OutputStream os = new FileOutputStream(file)) {
//                            os.write(dataFile.getContent());
//                        }
//                        Desktop.getDesktop().open(file);
//                    } catch (IOException ex) {
//                        JOptionPane.showMessageDialog(
//                                frame.getFrame(),
//                                "Ошибка открытия файла",
//                                "Error",
//                                JOptionPane.ERROR_MESSAGE
//                        );
//                    }
//                }
//            });
//        }
//
//        for (JButton button : frame.getDeleteButtons()) {
//            button.addActionListener(e -> {
//                if (frame.getFrame() != null) {
//                    frame.getFrame().dispose();
//                }
//                eventClient.deleteEvent(frame,
//                        list.get(Integer.parseInt(button.getActionCommand())).getEventId(),
//                        authorizationFrameController.getUser().getUserId());
//                openPage();
//            });
//        }
//    }
//}
