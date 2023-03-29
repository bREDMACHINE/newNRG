package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.models.User;
import get.a.big.head.newNRG.users.frames.UserListFrame;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Controller
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserListFrameController {

    private UserListFrame frame;
    private final UserClient userClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final int size = 15;
    private int maxSize;
    private int from;
    private int pages = maxSize / size + 1;
    private int maxShow = pages * size - 15;
    private int page;

    public void initUserListFrameController(String role, String status) {
        from = 0;
        page = 1;
        openPage(role, status);

        frame.getButtonNext().addActionListener(e -> {
            from = from + 15;
            page = page + 1;
            openPage(role, status);
        });

        frame.getButtonPrevious().addActionListener(e -> {
            from = from - 15;
            page = page - 1;
            openPage(role, status);
        });

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });
    }

    public List<User> findAllUsers(String role, String status, int from) {
        ResponseEntity<Object> findAllUsersAnswer = userClient.findAllUsers(
                role,
                status,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (findAllUsersAnswer.getStatusCode().is2xxSuccessful() && findAllUsersAnswer.getBody() != null) {
            return UserMapper.toUsers(findAllUsersAnswer.getBody());
        } else {
            JOptionPane.showMessageDialog(frame.getFrame(),
                    findAllUsersAnswer.getStatusCode(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private void openPage(String role, String status) {
        List<User> list = findAllUsers(role, status, from);
        if (frame != null) {
            frame.getFrame().dispose();
        }
        if (list != null) {
            if (maxSize <= size) {
                frame = new UserListFrame(list, page, pages);
            } else if (from < size) {
                frame = new UserListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from < maxShow) {
                frame = new UserListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from == maxShow) {
                frame = new UserListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
            }
        }
    }
}
