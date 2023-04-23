package get.newNRG.users.controllers;

import get.newNRG.users.UserClient;
import get.newNRG.users.UserMapper;
import get.newNRG.users.models.Role;
import get.newNRG.users.models.User;
import get.newNRG.users.dtos.UserFullDto;
import get.newNRG.users.frames.AdminAccountFrame;
import get.newNRG.users.frames.UserAccountFrame;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAccountFrameController {

    private UserAccountFrame frame;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserClient userClient;

    public void initUserAccountFrameController(final User user) {
        if (authorizationFrameController.getUser().getRole().equals(Role.ADMIN.name())) {
            frame = new AdminAccountFrame(user);
        } else {
            frame = new UserAccountFrame(user);
        }

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonDeleteUser().addActionListener(e -> userClient.deleteUser(
                frame,
                user.getEmail(),
                authorizationFrameController.getUser().getUserId()
                )
        );

        frame.getButtonRequestRole().addActionListener(e -> {
            UserFullDto userFullDto = UserMapper.toUserFullDto(user);
            userFullDto.setStatus("REQUESTED");
            userClient.updateUser(
                    frame,
                    authorizationFrameController.getUser().getUserId(),
                    userFullDto
            );
        });

        frame.getButtonAcceptRole().addActionListener(e -> userClient.resolutionUser(
                    frame,
                    "ACCEPTED",
                    user.getEmail(),
                    authorizationFrameController.getUser().getUserId()
            )
        );

        frame.getButtonRejectRole().addActionListener(e -> userClient.resolutionUser(
                    frame,
                    "REJECTED",
                    user.getEmail(),
                    authorizationFrameController.getUser().getUserId()
            )
        );

        frame.getButtonClose().addActionListener(e -> frame.getFrame().dispose());
    }
}
