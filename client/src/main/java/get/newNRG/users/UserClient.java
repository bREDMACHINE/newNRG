package get.newNRG.users;

import com.google.gson.JsonParser;
import get.newNRG.httpclients.BaseClient;
import get.newNRG.users.dtos.UserDto;
import get.newNRG.users.dtos.UserFullDto;
import get.newNRG.users.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.swing.*;
import java.awt.*;
import java.io.StringReader;
import java.util.List;

@Component
@Slf4j
public class UserClient extends BaseClient {

    @Autowired
    public UserClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public String userRegistration(Frame frame, UserDto user) {
        log.info("Registration user {}",  user);
        Object object = response(post("/authorization/registration", null, user), frame);
        if (object != null) {
            String role = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("Role").getAsString();
            JOptionPane.showMessageDialog(
                    frame,
                    "Пользователь " + user.getEmail() + " зарегистрирован с ролью " + role
            );
            return role;
        }
        return null;
    }

    public User userAuthorization(Frame frame, UserDto userDto) {
        log.info("Authorization user {}",  userDto);
        Object object = response(post("/authorization", null, userDto), frame);
        if (object != null) {
            return UserMapper.toUser(object, userDto.getEmail());
        }
        return null;
    }

    public Boolean logout(Frame frame, String userId) {
        log.info("Logout user {}",  userId);
        Object object = response(post("/authorization/logout", userId), frame);
        if (object != null) {
            boolean isAuthenticated = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("isAuthenticated").getAsBoolean();
            JOptionPane.showMessageDialog(frame, "Вы вышли из аккаунта");
            return isAuthenticated;
        }
        return null;
    }

    public User getUser(Frame frame, String userName, String userId) {
        log.info("Get user {}",  userName);
        Object object = response(
                get("/admin/user?email=" + userName, userId),
                frame
        );
        if (object != null) {
            return UserMapper.toUser(object);
        }
        return null;
    }

    public void deleteUser(Frame frame, String userName, String userId) {
        log.info("Delete user {}",  userName);
        Object object = response(delete("/admin/user?email=" + userName, userId), frame);
        if (object != null) {
            String email = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("email").getAsString();
            JOptionPane.showMessageDialog(frame,"Пользователь " + email + " удален");
        }
    }

    public List<User> findAllUsers(Frame frame, String role, String status, int from, int size, String userId) {
        log.info("Find all users with parameters role={} and status={} from {}", role, status, from);
        Object object = response(
                get("/admin/users?role=" + role + "&status=" + status + "&from=" + from + "&size=" + size, userId),
                frame
        );
        if (object != null) {
            return UserMapper.toUsers(object);
        }
        return null;
    }

    public void updateUser(Frame frame, String userId, UserFullDto userFullDto) {
        log.info("Update user {}",  userFullDto);
        Object object = response(patch("/user/update", userId, userFullDto), frame);
        if (object != null) {
            String email = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("email").getAsString();
            JOptionPane.showMessageDialog(frame,"Запрос на роль пользователя " + email + " отправлен");
        }
    }

    public void resolutionUser(Frame frame, String resolution, String email, String userId) {
        log.info("Request user {} is {}", email, resolution);
        Object object = response(patch("/admin/user?resolution=" + resolution + "&email=" + email, userId), frame);
        if (object != null) {
            String name = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("name").getAsString();
            JOptionPane.showMessageDialog(frame,"Пользователь " + name + " " + resolution);
        }
    }

    private <T> T response(ResponseEntity<T> response, Frame frame) {
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            log.info("Result {}", response.getBody().toString());
            return response.getBody();
        } else {
            if (frame != null) {
                JOptionPane.showMessageDialog(
                        frame,
                        response.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            return null;
        }
    }
}
