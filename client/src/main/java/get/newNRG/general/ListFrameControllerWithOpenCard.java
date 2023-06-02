package get.newNRG.general;

import get.newNRG.equipment.EquipmentDto;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Getter
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ListFrameControllerWithOpenCard {

    private ListFrameWithOpenCard frame;
    private ClientWithOpenCard client;
    private final UserAuthorizationFrameController authorizationFrameController;
    private CardFrameController cardController;
    private AddCardFrameController addCardController;
    private int size;
    private int maxSize;
    private int from;
    private int pages;
    private int maxShow;
    private Long parentObjectId;
    private int page;
    private List<? extends WithOpenCard> list;
    private List<String> labels;

    public void initListFrameController(
            ClientWithOpenCard client,
            int maxSize,
            List<String> labels,
            Long parentObjectId,
            AddCardFrameController addCardController,
            CardFrameController cardController) {
        this.client = client;
        this.maxSize = maxSize;
        this.labels = labels;
        this.parentObjectId = parentObjectId;
        this.addCardController = addCardController;
        this.cardController = cardController;
        size = 15;
        pages = maxSize / size + 1;
        maxShow = pages * size - 15;
        from = 0;
        page = 1;
        openPage();
    }

    private void openPage() {
        list = client.findAll(
                frame,
                parentObjectId,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (list != null) {
            if (maxSize <= size) {
                frame = new ListFrameWithOpenCard(list, labels, page, pages);
                functionsFrame();
            } else if (from < size) {
                frame = new ListFrameWithOpenCard(list, labels, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
                functionsFrame();
            } else if (from > 14 && from < maxShow) {
                frame = new ListFrameWithOpenCard(list, labels, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
                functionsFrame();
            } else if (from > 14 && from == maxShow) {
                frame = new ListFrameWithOpenCard(list, labels, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                functionsFrame();
            }
        }
    }

    private void functionsFrame() {
        frame.getButtonNext().addActionListener(e -> {
            if (frame.getFrame() != null) {
                frame.getFrame().dispose();
            }
            from = from + 15;
            page = page + 1;
            openPage();
        });

        frame.getButtonPrevious().addActionListener(e -> {
            if (frame.getFrame() != null) {
                frame.getFrame().dispose();
            }
            from = from - 15;
            page = page - 1;
            openPage();
        });

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonCreateCard().addActionListener(e -> ControllerUtil.start(addCardController));

        frame.getButtonFind().addActionListener(e -> {
            Object object = client.getByName(
                    frame,
                    frame.getTextFieldFinder().getText(),
                    authorizationFrameController.getUser().getUserId()
            );
            ControllerUtil.start(cardController, parentObjectId);
        });

        for (JButton button : frame.getOpenCardButtons()) {
            button.addActionListener(e -> ControllerUtil.start(
                    cardController, list.get(Integer.parseInt(button.getActionCommand())).getId()
            ));

        }
    }
}