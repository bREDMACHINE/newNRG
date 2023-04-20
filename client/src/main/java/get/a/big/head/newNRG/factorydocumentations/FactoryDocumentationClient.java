package get.a.big.head.newNRG.factorydocumentations;

import com.google.gson.JsonParser;
import get.a.big.head.newNRG.general.Client;
import get.a.big.head.newNRG.httpclients.BaseClient;
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
public class FactoryDocumentationClient extends BaseClient implements Client {

    @Autowired
    public FactoryDocumentationClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public void addDocument(Frame frame, FactoryDocumentationDto document, String userId) {
        log.info("Add document {}",  document);
        Object object = response(post("/moderator/equipment/type/document", userId, document), frame);
        if (object != null) {
            FactoryDocumentationDto documentDto = FactoryDocumentationMapper.toDocumentDto(object);
            frame.dispose();
            JOptionPane.showMessageDialog(frame,
                    "Документ " + documentDto.getNameFactoryDocumentation() + " успешно добавлен");
        }
    }

    @Override
    public void delete(Frame frame, Long documentId, String userId) {
        log.info("Delete document {}",  documentId);
        Object object = response(delete("/moderator/equipment/type/document/" + documentId, userId), frame);
        if (object != null) {
            String name = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("name").getAsString();
            frame.dispose();
            JOptionPane.showMessageDialog(frame,"Документ " + name + " удален");
        }
    }

    @Override
    public List<FactoryDocumentationDto> findAll(Frame frame, Long typeId, int from, int size, String userId) {
        log.info("Find all events for type {}, from {}", typeId, from);
        Object object = response(
                get("/user/equipment/type/" + typeId + "/documents?from=" + from + "&size=" + size, userId),
                frame
        );
        if (object != null) {
            return FactoryDocumentationMapper.toDocumentDtos(object);
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
