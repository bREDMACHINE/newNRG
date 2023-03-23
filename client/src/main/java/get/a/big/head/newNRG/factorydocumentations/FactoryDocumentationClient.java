package get.a.big.head.newNRG.factorydocumentations;

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
public class FactoryDocumentationClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public FactoryDocumentationClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addDocument(FactoryDocumentationDto document, String userId) {
        log.info("Add document {}",  document);
        return post("/moderator/equipment/type/document", userId, document);
    }

    public ResponseEntity<Object> deleteDocument(Long documentId, String userId) {
        log.info("Delete document {}",  documentId);
        return delete("/moderator/equipment/type/document/" + documentId, userId);
    }

    public ResponseEntity<Object> findAllDocuments(Long typeId, int from, int size, String userId) {
        log.info("Find all documents for typeId {}", typeId);
        return get("/user/equipment/type/" + typeId + "/documents?from=" + from + "&size=" + size, userId);
    }
}
