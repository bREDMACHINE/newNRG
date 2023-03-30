package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.models.Role;
import get.a.big.head.newNRG.users.models.User;
import get.a.big.head.newNRG.users.dtos.UserFullDto;
import get.a.big.head.newNRG.users.frames.AdminAccountFrame;
import get.a.big.head.newNRG.users.frames.UserAccountFrame;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Lazy
@Slf4j
@Controller
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

        frame.getButtonDeleteUser().addActionListener(e -> {
            log.info("Delete user {}", user);
            deleteUser(user);
        });

        frame.getButtonRequestRole().addActionListener(e -> {
            UserFullDto userFullDto = UserMapper.toUserFullDto(user);
            userFullDto.setStatus("REQUESTED");
            log.info("Update user {}", userFullDto);
            updateUser(userFullDto);
        });

        frame.getButtonAcceptRole().addActionListener(e -> {
            log.info("Accepted request user {}", user);
            resolution("ACCEPTED", user);
        });

        frame.getButtonRejectRole().addActionListener(e -> {
            log.info("Rejected request user {}", user);
            resolution("REJECTED", user);
        });

        frame.getButtonClose().addActionListener(e -> frame.getFrame().dispose());
    }

    private void updateUser(UserFullDto userFullDto) {
        ResponseEntity<Object> updateUserResponse = userClient.updateUser(
                authorizationFrameController.getUser().getUserId(),
                userFullDto
        );
        if (updateUserResponse.getStatusCode().is2xxSuccessful()) {
            JOptionPane.showMessageDialog(frame.getFrame(), "Запрос отправлен");
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    updateUserResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void deleteUser(User user) {
        ResponseEntity<Object> deleteUserResponse = userClient.deleteUser(user.getEmail(),
                authorizationFrameController.getUser().getUserId());
        if (deleteUserResponse.getStatusCode().is2xxSuccessful()) {
            JOptionPane.showMessageDialog(frame.getFrame(), "Пользователь удален");
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    deleteUserResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void resolution(String resolution, User user) {
        ResponseEntity<Object> resolutionResponse = userClient.resolutionUser(
                resolution,
                user.getEmail(),
                authorizationFrameController.getUser().getUserId()
        );
        if (resolutionResponse.getStatusCode().is2xxSuccessful()) {
            JOptionPane.showMessageDialog(frame.getFrame(), "Резолюция отправлена");
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    resolutionResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
