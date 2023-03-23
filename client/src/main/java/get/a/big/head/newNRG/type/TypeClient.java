package get.a.big.head.newNRG.type;

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
public class TypeClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public TypeClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addType(TypeShortDto type, String userId) {
        log.info("Add type {}",  type);
        return post("/moderator/equipment/type", userId, type);
    }

    public ResponseEntity<Object> updateType(TypeDto type, String userId) {
        log.info("Update type {}",  type);
        return patch("/moderator/equipment/type", userId, type);
    }

    public ResponseEntity<Object> deleteType(Long typeId, String userId) {
        log.info("Delete type {}",  typeId);
        return delete("/moderator/equipment/type/" + typeId, userId);
    }

    public ResponseEntity<Object> getType(Long typeId, String userId) {
        log.info("Get type {}",  typeId);
        return get("/user/equipment/type/" + typeId, userId);
    }

    public ResponseEntity<Object> findAllTypes(String userId) {
        log.info("Find all types");
        return get("/user/equipment/types", userId);
    }
}
