package get.newNRG.specification;

import get.newNRG.httpclients.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Component
@Slf4j
public class SpecificationClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public SpecificationClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addSpecification(SpecificationDto specification, String userId) {
        return post("/moderator/equipment/type/specification",  userId, specification);
    }

    public ResponseEntity<Object> deleteSpecification(Long specificationId, String userId) {
        return delete("/moderator/equipment/type/specification/" + specificationId,  userId);
    }

    public ResponseEntity<Object> getSpecification(Long specificationId, String userId) {
        return get("/user/equipment/type/specification/" + specificationId, userId);
    }

    public ResponseEntity<Object> findAllSpecifications(String userId) {
        return get("/user/equipment/type/specifications",  userId);
    }
}
