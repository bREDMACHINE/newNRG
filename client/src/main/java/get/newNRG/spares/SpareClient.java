package get.newNRG.spares;

import com.google.gson.JsonParser;
import get.newNRG.general.ClientWithOpenCard;
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
public class SpareClient extends BaseClient implements ClientWithOpenCard {

    @Autowired
    public SpareClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public void addSpare(Frame frame, SpareDto spare, String userId) {
        log.info("Add spare {}", spare);
        Object object = response(post("/moderator/equipment/type/spare",  userId, spare), frame);
        if (object != null) {
            SpareDto spareResponse = SpareMapper.toSpareDto(object);
            frame.dispose();
            JOptionPane.showMessageDialog(frame,
                    "Деталь " + spareResponse.getSpareName() + " успешно добавлена");
        }
    }

    public void deleteSpare(Frame frame, Long spareId, String userId) {
        log.info("Delete spare {}", spareId);
        Object object = response(delete("/moderator/equipment/type/spare/" + spareId,  userId), frame);
        if (object != null) {
            String name = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("name").getAsString();
            frame.dispose();
            JOptionPane.showMessageDialog(frame,"Деталь " + name + " удалена");
        }
    }

    @Override
    public SpareDto get(Frame frame, Long spareId, String userId) {
        log.info("Get spare {}", spareId);
        Object object = response(
                get("/moderator/equipment/type/spare/" + spareId,  userId),
                frame
        );
        if (object != null) {
            return SpareMapper.toSpareDto(object);
        }
        return null;
    }

    @Override
    public List<SpareDto> findAll(Frame frame, Long typeId, int from, int size, String userId) {
        log.info("Find all spares for type {}", typeId);
        Object object = response(
                get("/user/equipment/type/" + typeId + "/spares?from=" + from + "&size=" + size,  userId),
                frame
        );
        if (object != null) {
            return SpareMapper.toSpareDtos(object);
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
