package get.newNRG.type;

import com.google.gson.JsonParser;
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
public class TypeClient extends BaseClient {

    @Autowired
    public TypeClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public void addType(Frame frame, TypeShortDto type, String userId) {
        log.info("Add type {}",  type);
        Object object = response(post("/moderator/equipment/type", userId, type), frame);
        if (object != null) {
            TypeShortDto typeResponse = TypeMapper.toTypeShortDto(object);
            JOptionPane.showMessageDialog(frame,
                    "Тип " + typeResponse.getTypeName() + " успешно добавлен");
        }
    }

    public void updateType(Frame frame, TypeDto type, String userId) {
        log.info("Update type {}",  type);
        Object object = response(patch("/moderator/equipment/type", userId, type), frame);
        if (object != null) {
            TypeDto typeResponse = TypeMapper.toTypeDto(object);
            frame.dispose();
            JOptionPane.showMessageDialog(frame,
                    "Тип " + typeResponse.getTypeName() + " успешно обновлен");
        }
    }

    public void deleteType(Frame frame, Long typeId, String userId) {
        log.info("Delete type {}",  typeId);
        Object object = response(delete("/moderator/equipment/type/" + typeId, userId), frame);
        if (object != null) {
            String name = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("name").getAsString();
            frame.dispose();
            JOptionPane.showMessageDialog(frame,"Тип " + name + " удален");
        }
    }

    public TypeDto getType(Frame frame, Long typeId, String userId) {
        log.info("Get type {}",  typeId);
        Object object = response(
                get("/user/equipment/type/" + typeId, userId),
                frame
        );
        if (object != null) {
            return TypeMapper.toTypeDto(object);
        }
        return null;
    }

    public List<TypeShortDto> findAllTypes(Frame frame, String userId) {
        log.info("Find all types");
        Object object = response(
                get("/user/equipment/types", userId),
                frame
        );
        if (object != null) {
            return TypeMapper.toTypeShortDtos(object);
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
