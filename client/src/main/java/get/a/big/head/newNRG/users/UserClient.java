package get.a.big.head.newNRG.users;

import get.a.big.head.newNRG.httpclients.BaseClient;
import get.a.big.head.newNRG.users.dtos.UserDto;
import get.a.big.head.newNRG.users.dtos.UserFullDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@Slf4j
public class UserClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public UserClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> userRegistration(UserDto userDto) {
        log.info("Registration user {}",  userDto);
        return post("/authorization/registration", null, userDto);
    }

    public ResponseEntity<Object> userAuthorization(UserDto userDto) {
        log.info("Authorization user {}",  userDto);
        return post("/authorization", null, userDto);
    }

    public ResponseEntity<Object> logout(String userId) {
        log.info("Logout user {}",  userId);
        return post("/authorization/logout", userId);
    }

    public ResponseEntity<Object> getUser(String userName, String userId) {
        log.info("Get user {}",  userName);
        return get("/admin/user?email=" + userName, userId);
    }

    public ResponseEntity<Object> deleteUser(String userName, String userId) {
        log.info("Delete user {}",  userName);
        return delete("/admin/user?email=" + userName, userId);
    }

    public ResponseEntity<Object> findAllUsers(String role, String status, String userId) {
        log.info("Find all users with parameters role={} and status={}", role, status);
        return get("/admin/users?role=" + role + "&status=" + status, userId);
    }

    public ResponseEntity<Object> updateUser(String userId, UserFullDto userFullDto) {
        return patch("/user/update", userId, userFullDto);
    }

    public ResponseEntity<Object> resolutionUser(String resolution, String email, String userId) {
        return patch("/admin/user?resolution=" + resolution + "&email=" + email, userId);
    }
}
