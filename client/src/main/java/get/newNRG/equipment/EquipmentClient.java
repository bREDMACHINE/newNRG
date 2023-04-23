package get.newNRG.equipment;

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
public class EquipmentClient extends BaseClient {

    @Autowired
    public EquipmentClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public void addEquipment(Frame frame, EquipmentShortDto equipment, String userId) {
        log.info("Add equipment {}",  equipment);
        Object object = response(post("/moderator/equipment", userId, equipment), frame);
        if (object != null) {
            EquipmentShortDto equipmentResponse = EquipmentMapper.toEquipmentShortDto(object);
            frame.dispose();
            JOptionPane.showMessageDialog(frame,
                    "Оборудование " + equipmentResponse.getOperationalName() + " успешно добавлено");
        }
    }

    public void updateEquipment(Frame frame, EquipmentDto equipment, String userId) {
        log.info("Update equipment {}",  equipment);
        Object object = response(patch("/moderator/equipment", userId, equipment), frame);
        if (object != null) {
            EquipmentShortDto equipmentResponse = EquipmentMapper.toEquipmentShortDto(object);
            frame.dispose();
            JOptionPane.showMessageDialog(frame,
                    "Оборудование " + equipmentResponse.getOperationalName() + " успешно обновлено");
        }
    }

    public void deleteEquipment(Frame frame, Long equipmentId, String userId) {
        log.info("Delete equipment {}",  equipmentId);
        Object object = response(delete("/moderator/equipment/" + equipmentId, userId), frame);
        if (object != null) {
            String name = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("name").getAsString();
            frame.dispose();
            JOptionPane.showMessageDialog(frame,"Оборудование " + name + " удалено");
        }
    }

    public EquipmentDto getEquipment(Frame frame, String text, String userId) {
        log.info("Get equipment {}",  text);
        Object object = response(get("/user/equipment?text=" + text, userId), frame);
        if (object != null) {
            return EquipmentMapper.toEquipmentDto(object);
        }
        return null;
    }

    public List<EquipmentShortDto> findAllEquipment(Frame frame, Long equipmentId, int from, int size, String userId) {
        log.info("Find all projects for equipment {}, from {}", equipmentId, from);
        Object object = response(
                get("/user/equipment/" + equipmentId + "/projects?from=" + from + "&size=" + size, userId),
                frame
        );
        if (object != null) {
            return EquipmentMapper.toEquipmentShortDtos(object);
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
