package get.a.big.head.newNRG.spares;

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
public class SpareClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public SpareClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addSpare(SpareDto spare, String userId) {
        return post("/moderator/equipment/type/spare",  userId, spare);
    }

    public ResponseEntity<Object> deleteSpare(Long spareId, String userId) {
        return delete("/moderator/equipment/type/spare/" + spareId,  userId);
    }

    public ResponseEntity<Object> getSpare(Long spareId, String userId) {
        return get("/moderator/equipment/type/spare/" + spareId,  userId);
    }

    public ResponseEntity<Object> findAllSpares(Long typeId, int from, int size, String userId) {
        return get("/user/equipment/type/" + typeId + "/spares?from=" + from + "&size=" + size,  userId);
    }
}
