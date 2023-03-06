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

    private static final String API_PREFIX = "/equipment/type";

    @Autowired
    public TypeClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addType(Type type, String userId) {
        log.info("Add type {}",  type);
        return post("", userId, type);
    }

    public ResponseEntity<Object> getType(String typeName, String userId) {
        log.info("Get type {}",  typeName);
        return get("?typename=" + typeName, userId);
    }

    public ResponseEntity<Object> removeType(String typeName, String userId) {
        log.info("Delete type {}",  typeName);
        return delete("?typename=" + typeName, userId);
    }

    public ResponseEntity<Object> findAllTypes(String userId) {
        log.info("Find all types");
        return get("/all", userId);
    }
}
