package get.a.big.head.newNRG.projectdocumentations;

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
public class ProjectDocumentationClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public ProjectDocumentationClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> addProject(ProjectDocumentationDto project, String userId) {
        log.info("Add project {}",  project);
        return post("/moderator/equipment/project", userId, project);
    }

    public ResponseEntity<Object> deleteProject(Long projectId, String userId) {
        log.info("Delete project {}",  projectId);
        return delete("/moderator/equipment/project/" + projectId, userId);
    }

    public ResponseEntity<Object> findAllProjects(Long equipmentId, int from, int size, String userId) {
        log.info("Find all projects for equipmentId {}", equipmentId);
        return get("/user/equipment/" + equipmentId + "/projects?from=" + from + "&size=" + size, userId);
    }
}
