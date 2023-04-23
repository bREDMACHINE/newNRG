package get.newNRG.factories;

import get.newNRG.httpclients.BaseClient;
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
public class FactoryClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public FactoryClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addFactory(FactoryDto factory, String userId) {
        return post("/moderator/equipment/type/factory",  userId, factory);
    }

    public ResponseEntity<Object> deleteFactory(Long factoryId, String userId) {
        return delete("/moderator/equipment/type/factory/" + factoryId,  userId);
    }

    public ResponseEntity<Object> findAllFactories(String userId) {
        return get("/user/equipment/type/factories",  userId);
    }
}
