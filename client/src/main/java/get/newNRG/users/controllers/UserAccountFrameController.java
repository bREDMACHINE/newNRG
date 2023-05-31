package get.newNRG.users.controllers;

import get.newNRG.general.UserCardFrameController;
import get.newNRG.users.UserClient;
import get.newNRG.users.UserMapper;
import get.newNRG.users.models.User;
import get.newNRG.users.dtos.UserFullDto;
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
public class UserAccountFrameController implements UserCardFrameController {

    private UserAccountFrame frame;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserClient userClient;

    @Override
    public void initUserCardFrameController(String email) {

        User user = userClient.getUser(frame, email, authorizationFrameController.getUser().getUserId());
        String userId = user.getUserId();

        frame = new UserAccountFrame(user);

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonDeleteUser().addActionListener(e -> userClient.deleteUser(
                frame,
                email,
                userId
        ));

        frame.getButtonRequestRole().addActionListener(e -> {
            UserFullDto userFullDto = UserMapper.toUserFullDto(user);
            userFullDto.setStatus("REQUESTED");
            userClient.updateUser(
                    frame,
                    userId,
                    userFullDto
            );
        });

        frame.getButtonAcceptRole().addActionListener(e -> userClient.resolutionUser(
                frame,
                "ACCEPTED",
                email,
                userId
            )
        );

        frame.getButtonRejectRole().addActionListener(e -> userClient.resolutionUser(
                frame,
                "REJECTED",
                email,
                userId
            )
        );

        frame.getButtonClose().addActionListener(e -> frame.getFrame().dispose());
    }
}
