package get.newNRG.projectdocumentations;

import com.google.gson.JsonParser;
import get.newNRG.general.ClientForListWithFile;
import get.newNRG.httpclients.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.swing.*;
import java.awt.*;
import java.io.StringReader;
import java.util.List;

@Component
@Slf4j
public class ProjectDocumentationClient extends BaseClient implements ClientForListWithFile {

    @Autowired
    public ProjectDocumentationClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public void addProject(Frame frame, ProjectDocumentationDto project, String userId) {
        log.info("Add project {}",  project);
        Object object = response(post("/moderator/equipment/project", userId, project), frame);
        if (object != null) {
            ProjectDocumentationDto projectResponse = ProjectDocumentationMapper.toProjectDto(object);
            JOptionPane.showMessageDialog(frame,
                    "Проект " + projectResponse.getNameProjectDocumentation() + " успешно добавлен");
            frame.dispose();
        }
    }

    @Override
    public void delete(Frame frame, Long projectId, String userId) {
        log.info("Delete project {}",  projectId);
        Object object = response(delete("/moderator/equipment/project/" + projectId, userId), frame);
        if (object != null) {
            String name = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("name").getAsString();
            frame.dispose();
            JOptionPane.showMessageDialog(frame,"Проект " + name + " удален");
        }
    }

    @Override
    public List<ProjectDocumentationDto> findAll(Frame frame, Long equipmentId, int from, int size, String userId) {
        log.info("Find all projects for equipment {}, from {}", equipmentId, from);
        Object object = response(
                get("/user/equipment/" + equipmentId + "/projects?from=" + from + "&size=" + size, userId),
                frame
        );
        if (object != null) {
            return ProjectDocumentationMapper.toProjectDtos(object);
        }
        return null;
    }

    private <T> T response(ResponseEntity<T> response, Frame frame) {
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            log.info("Result {}",  response.getBody().toString());
            return response.getBody();
        } else {
            if (frame != null) {
                JOptionPane.showMessageDialog(
                        frame,
                        response.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        return null;
    }
}
