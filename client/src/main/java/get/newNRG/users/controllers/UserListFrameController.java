package get.newNRG.users.controllers;

import get.newNRG.general.ListFrame;
import get.newNRG.users.UserClient;
import get.newNRG.users.frames.UserListFrame;
import get.newNRG.users.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserListFrameController {

    private UserListFrame frame;
    private final UserClient client;
    private final UserAuthorizationFrameController authorizationFrameController;
    private int size;
    private int maxSize;
    private int from;
    private int pages;
    private int maxShow;
    private int page;
    private List<User> list;
    private List<String> labels;
    private String role;
    private String status;

    public void initUserListFrameController(int maxSize, String role, String status) {
        this.labels = List.of("Логин", "Уровень доступа", "Статус");
        this.maxSize = maxSize;
        this.role = role;
        this.status = status;
        size = 15;
        pages = maxSize / size + 1;
        maxShow = pages * size - 15;
        from = 0;
        page = 1;
        openPage();
    }

    private void openPage() {
        list = client.findAllUsers(
                frame,
                role,
                status,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (list != null) {
            if (maxSize <= size) {
                frame = new UserListFrame(list, labels, page, pages);
                functionsFrame();
            } else if (from < size) {
                frame = new UserListFrame(list, labels, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
                functionsFrame();
            } else if (from > 14 && from < maxShow) {
                frame = new UserListFrame(list, labels, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
                functionsFrame();
            } else if (from > 14 && from == maxShow) {
                frame = new UserListFrame(list, labels, page, pages);
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
    }
}
