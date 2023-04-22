package get.newNRG.specificationvalue;

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
public class SpecificationValueClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public SpecificationValueClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addSpecificationValue(SpecificationValueDto specificationValue, String userId) {
        return post("/moderator/equipment/type/specification/value",  userId, specificationValue);
    }

    public ResponseEntity<Object> deleteSpecificationValue(Long specificationValueId, String userId) {
        return delete("/moderator/equipment/type/specification/value/" + specificationValueId,  userId);
    }

    public ResponseEntity<Object> getSpecificationValue(Long specificationValueId, String userId) {
        return get("/user/equipment/type/specification/value/" + specificationValueId, userId);
    }

    public ResponseEntity<Object> findAllSpecificationValues(Long typeId, int from, int size, String userId) {
        return get("/user/equipment/type/" + typeId + "/specification/values?from=" + from + "&size=" + size,  userId);
    }
}
