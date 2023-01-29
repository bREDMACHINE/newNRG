package get.a.big.head.newNRG.users;

import get.a.big.head.newNRG.httpclients.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@Slf4j
public class UserClient extends BaseClient {

    private static final String API_PREFIX = "/users";

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
        return post("/registration", userDto);
    }

    public ResponseEntity<Object> updateUser(long userId, UserDto userDto) {
        return patch("/" + userId, userDto);
    }

    public ResponseEntity<Object> userAuthorization(UserDto userDto) {
        log.info("Authorization user {}",  userDto);
        return post("/authorization", userDto);
    }

    public ResponseEntity<Object> findAllUsers() {
        return get("");
    }

    public ResponseEntity<Object> deleteUser(long userId) {
        return delete("/" + userId);
    }
}
