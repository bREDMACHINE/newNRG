package get.newNRG.specificationvalue;

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
public class SpecificationValueClient extends BaseClient {

    @Autowired
    public SpecificationValueClient(@Value("${newnrg-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public SpecificationValueDto addSpecificationValue(Frame frame, SpecificationValueDto specificationValue, String userId) {
        log.info("Add specification value {}", specificationValue);
        Object object = response(post("/moderator/equipment/type/specification/value",  userId, specificationValue), frame);
        if (object != null) {
            return SpecificationValueMapper.toSpecificationValueDto(object);
        }
        return null;
    }

    public void deleteSpecificationValue(Frame frame, Long specificationValueId, String userId) {
        log.info("Delete specification value {}", specificationValueId);
        Object object = response(delete("/moderator/equipment/type/specification/value/" + specificationValueId,  userId), frame);
        if (object != null) {
            String name = JsonParser.parseReader(new StringReader(object.toString()))
                    .getAsJsonObject().get("name").getAsString();
            frame.dispose();
            JOptionPane.showMessageDialog(frame,"Значение " + name + " удалено");
        }
    }

    public SpecificationValueDto getSpecificationValue(Frame frame, Long specificationValueId, String userId) {
        log.info("Get specification value {}", specificationValueId);
        Object object = response(
                get("/user/equipment/type/specification/value/" + specificationValueId, userId),
                frame
        );
        if (object != null) {
            return SpecificationValueMapper.toSpecificationValueDto(object);
        }
        return null;
    }

    public List<SpecificationValueDto> findAllSpecificationValues(Frame frame, Long typeId, String userId) {
        log.info("Find all specifications values for type {}", typeId);
        Object object = response(
                get("/user/equipment/type/" + typeId + "/specification/values",  userId),
                frame
        );
        if (object != null) {
            return SpecificationValueMapper.toSpecificationValueDtos(object);
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
